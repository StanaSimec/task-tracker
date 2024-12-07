package model;

import java.util.ArrayList;
import java.util.List;

public class JSONMapper {

    private JSONMapper() {
    }

    private static String toJson(Task task) {
        String jsonFormat = """
                            {
                                "id": %d,
                                "description": "%s",
                                "status": "%s",
                                "createdAt": "%s",
                                "updatedAt": "%s"
                            }
                            """;
        return String.format(jsonFormat, task.getId(), task.getDescription(), task.getStatus().value,
                task.getCreatedAt(),
                task.getUpdatedAt());
    }

    static String toJson(List<Task> tasks) {
        List<String> jsons = new ArrayList<>();
        for (Task task : tasks) {
            jsons.add(toJson(task));
        }
        String tasksJson = """
                           {
                            "tasks": [
                                %s
                            ]
                           }
                           """;
        return String.format(tasksJson, String.join(",", jsons));
    }
}
