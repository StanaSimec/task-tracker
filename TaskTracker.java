import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class TaskTracker {

    private TaskRepository repository;

    TaskTracker(String[] args) {
        repository = new TaskRepository();
        execute(args);
    }

    void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("action not found");
            return;
        }

        if ("add".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("task description not found");
                return;
            }
            int taskId = repository.add(args[1]);
            System.out.println("Created task with id: " + taskId);
        }

        if ("update".equals(args[0])) {
            if (args.length < 3) {
                System.out.println("Task desription or id not found");
                return;
            }
            int id = Integer.parseInt(args[1]);
            String description = args[2];
            boolean isUpdated = repository.setDescription(id, description);
            System.out.println(isUpdated ? "Task updated" : "Task not found");
        }

        if ("delete".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isDeleted = repository.delete(id);

            System.out.println(isDeleted ? "Task deleted" : "Task not found");
            return;
        }

        if ("list".equals(args[0])) {
            String format = "id: %d, description: %s, status: %s, created at: %s, updated at: %s";
            List<Task> tasks = repository.findAll();

            if (args.length == 2) {
                Optional<Status> status = Status.fromName(args[1]);
                if (status.isEmpty()) {
                    System.out.println("Invalid task status");
                    return;
                }

                tasks = tasks.stream()
                        .filter(t -> t.getStatus().equals(status.get()))
                        .collect(Collectors.toList());
            }

            for (Task task : tasks) {
                System.out.println(String.format(format, task.getId(), task.getDescription(), task.getStatus().name,
                        task.getCreatedAt(), task.getUpdatedAt()));
            }
        }

        if ("mark-done".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isUpdated = repository.setStatus(id, Status.DONE);
            System.out.println(isUpdated ? "Task updated" : "Task not found.");
            return;
        }

        if ("mark-in-progress".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("Missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isUpdated = repository.setStatus(id, Status.IN_PROGRESS);
            System.out.println(isUpdated ? "Task updated" : "Task not found.");
            return;
        }
    }
}