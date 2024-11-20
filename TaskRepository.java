import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

class TaskRepository {

    private final TaskStorage storage;

    TaskRepository() {
        this.storage = new TaskStorage();
    }

    int add(String description) {
        List<Task> tasks = storage.getAllTasks();
        int maxId = tasks.stream()
                .mapToInt(t -> t.getId())
                .max()
                .orElse(0);
        int id = maxId + 1;
        LocalDateTime now = LocalDateTime.now();
        tasks.add(new Task(id, description, Status.TODO, now, now));
        storage.save(tasks);
        return id;
    }

    List<Task> findAll() {
        return storage.getAllTasks();
    }

    boolean delete(int id) {
        List<Task> tasks = storage.getAllTasks();
        int index = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return false;
        }

        tasks.remove(index);
        storage.save(tasks);
        return true;
    }

    boolean setStatus(int id, Status status) {
        List<Task> tasks = storage.getAllTasks();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (task.isEmpty()) {
            return false;
        }

        task.get().setStatus(status);
        storage.save(tasks);
        return true;
    }

    boolean setDescription(int id, String description) {
        List<Task> tasks = storage.getAllTasks();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();

        if (task.isEmpty()) {
            return false;
        }

        task.get().setDescription(description);
        storage.save(tasks);
        return true;
    }
}
