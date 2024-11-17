import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TaskMapper {

    private  String toJson(Task task) {
        String jsonFormat = "{\"id\": %d, \"name\": \"%s\", \"status\": \"%s\"}";
        return String.format(jsonFormat, task.getId(), task.getName(), task.getStatus().name);
    }

    String toJson(List<Task> tasks) {
        List<String> jsons = new ArrayList<>();
        for (Task task : tasks) {
            jsons.add(toJson(task));
        }
        String tasksJson = "{\"tasks\":[%s]}";
        return String.format(tasksJson, String.join(", ", jsons));
    }

    private Optional<Task> toTask(String json) {
        Pattern pattern = Pattern.compile("\"id\": (.+), \"name\": \"(.+)\", \"status\": \"(.+)\"");
        Matcher matcher = pattern.matcher(json);

        if (!matcher.find() || matcher.groupCount() != 3) {
            return Optional.empty();
        }

        int id = Integer.parseInt(matcher.group(1));
        String name = matcher.group(2);
        //TODO: handle status not found
        Status status = Status.fromName(matcher.group(3)).orElse(Status.TODO);
        return Optional.of(new Task(id, name, status));
    }

    List<Task> toTasks(String json) {
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
            if (task.isPresent()) {
                tasks.add(task.get());
            }
        }
        return tasks;
    }
}
