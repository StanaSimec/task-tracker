package repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class TaskRepository {

    private TaskRepository(){}

    public static int add(String description) {
        List<Task> tasks = TaskStorage.getAllTasks();
        int maxId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0);
        int id = maxId + 1;
        LocalDateTime now = LocalDateTime.now();
        tasks.add(new Task(id, description, Status.TODO, now, now));
        TaskStorage.save(tasks);
        return id;
    }

    public static List<Task> findAll() {
        return TaskStorage.getAllTasks();
    }

    public static boolean delete(int id) {
        List<Task> tasks = TaskStorage.getAllTasks();
        int index = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return false;
        }

        tasks.remove(index);
        TaskStorage.save(tasks);
        return true;
    }

    public static boolean setStatus(int id, Status status) {
        List<Task> tasks = TaskStorage.getAllTasks();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (task.isEmpty()) {
            return false;
        }

        task.get().setStatus(status);
        TaskStorage.save(tasks);
        return true;
    }

    public static boolean setDescription(int id, String description) {
        List<Task> tasks = TaskStorage.getAllTasks();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (task.isEmpty()) {
            return false;
        }

        task.get().setDescription(description);
        TaskStorage.save(tasks);
        return true;
    }

    public static boolean existsById(int id) {
        Optional<Task> task = findAll().stream()
        .filter(t -> t.getId() == id)
        .findFirst();
        return task.isPresent();
    }
}
