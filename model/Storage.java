package model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class Storage {

    private static final String FILE_NAME = "tasks.json";

    private Storage() {
    }

    static List<Task> getAllTasks() {
        String file = getFile();
        return TaskMapper.toTasks(file);
    }

    static boolean save(List<Task> tasks) {
        String json = JSONMapper.toJson(tasks);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getFile() {
        StringBuilder builder = new StringBuilder();
        try (Scanner scanner = new Scanner(Paths.get(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        } catch (InvalidPathException | IOException e) {
            return "";
        }
        return builder.toString();
    }
}
