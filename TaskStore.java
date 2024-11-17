import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

class TaskStore {

    private static final String FILE_NAME = "tasks.json";

    private TaskMapper mapper;

    TaskStore() {
        mapper = new TaskMapper();
    }

    List<Task> getAllTasks() {
        String file = getFile();
        return mapper.toTasks(file);
    }

    void save(List<Task> tasks) {
        String json = mapper.toJson(tasks);
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFile() {
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
