package repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class TaskStorage {

    private static final String FILE_NAME = "tasks.json";

    private TaskStorage(){}

    static List<Task> getAllTasks() {
        String file = getFile();
        return TaskMapper.toTasks(file);
    }

    static void save(List<Task> tasks) {
        String json = TaskMapper.toJson(tasks);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getFile() {
        StringBuilder builder = new StringBuilder();
        try {
            Path path = Paths.get(FILE_NAME);
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            scanner.close();
        } catch (InvalidPathException | IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
