import java.util.List;

class TaskRepository {

    private final DataStore store;

    TaskRepository() {
        this.store = new DataStore();
    }

    int add(String name) {
        List<Task> tasks = store.getAllTasks();
        int id = tasks.size() + 1;
        tasks.add(new Task(id, name, Status.TODO));
        store.save(tasks);
        return id;
    }
}
