import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

class TaskRepository {

    private final TaskStore store;

    TaskRepository() {
        this.store = new TaskStore();
    }

    int add(String description) {
        List<Task> tasks = store.getAllTasks();
        int id = tasks.size() + 1;
        LocalDateTime now = LocalDateTime.now();
        tasks.add(new Task(id, description, Status.TODO, now, now));
        store.save(tasks);
        return id;
    }

    List<Task> findAll() {
        return store.getAllTasks();
    }

    boolean delete(int id) {
        List<Task> tasks = store.getAllTasks();

        int index = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return false;
        }

        tasks.remove(index);
        store.save(tasks);
        return true;
    }

    boolean setStatus(int id, Status status) {
        List<Task> tasks = store.getAllTasks();

        int index = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return false;
        }

        tasks.get(index).setStatus(status);
        tasks.get(index).setUpdatedAt(LocalDateTime.now());
        store.save(tasks);
        return true;
    }

    boolean setDescription(int id, String description) {
        List<Task> tasks = store.getAllTasks();

        int index = IntStream.range(0, tasks.size())
                .filter(i -> tasks.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            return false;
        }

        tasks.get(index).setDescription(description);
        store.save(tasks);
        return true;
    }
}
