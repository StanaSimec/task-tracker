import java.time.LocalDateTime;
import java.util.List;

class TaskRepository {

    private final TaskStore store;

    TaskRepository() {
        this.store = new TaskStore();
    }

    int add(String name) {
        List<Task> tasks = store.getAllTasks();
        int id = tasks.size() + 1;
        LocalDateTime now = LocalDateTime.now();
        tasks.add(new Task(id, name, Status.TODO, now, now));
        store.save(tasks);
        return id;
    }
}
