package repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TaskMapper {

    private TaskMapper(){}

    private static String toJson(Task task) {
        String jsonFormat = "{\"id\": %d, \"description\": \"%s\", \"status\": \"%s\", \"createdAt\": \"%s\", \"updatedAt\": \"%s\"}";
        return String.format(jsonFormat, task.getId(), task.getDescription(), task.getStatus().value,
                task.getCreatedAt(),
                task.getUpdatedAt());
    }

    static String toJson(List<Task> tasks) {
        List<String> jsons = new ArrayList<>();
        for (Task task : tasks) {
            jsons.add(toJson(task));
        }
        String tasksJson = "{\"tasks\":[%s]}";
        return String.format(tasksJson, String.join(", ", jsons));
    }

    private static Optional<Task> toTask(String json) {
        Pattern pattern = Pattern.compile(
                "\"id\": (.+), \"description\": \"(.+)\", \"status\": \"(.+)\", \"createdAt\": \"(.+)\", \"updatedAt\": \"(.+)\"");
        Matcher matcher = pattern.matcher(json);

        if (!matcher.find() || matcher.groupCount() != 5) {
            return Optional.empty();
        }

        int id = Integer.parseInt(matcher.group(1));
        String description = matcher.group(2);
        Status status = Status.fromName(matcher.group(3)).orElse(null);
        LocalDateTime createdAt = LocalDateTime.parse(matcher.group(4), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime updatedAt = LocalDateTime.parse(matcher.group(5), DateTimeFormatter.ISO_DATE_TIME);
        return Optional.of(new Task(id, description, status, createdAt, updatedAt));
    }

    static List<Task> toTasks(String json) {
        Pattern allTasksPattern = Pattern.compile(".+\\[(.*)\\].+");
        Matcher allTasksMatcher = allTasksPattern.matcher(json);

        if (!allTasksMatcher.find() || allTasksMatcher.groupCount() != 1) {
            return List.of();
        }

        String jsons = allTasksMatcher.group(1);
        String tasksDelimiter = "\\}, \\{";
        String[] tasksData = jsons.split(tasksDelimiter);
        List<Task> tasks = new ArrayList<>();
        for (String taskData : tasksData) {
            Optional<Task> task = toTask(taskData);
            task.ifPresent(tasks::add);
        }
        return tasks;
    }
}