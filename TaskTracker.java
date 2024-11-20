import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskTracker {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Action nout found");
            return;
        }
        String action = args[0];
        TaskRepository repository = new TaskRepository();
        if ("add".equals(action)) {
            if (args.length < 2) {
                System.out.println("Task description not found");
                return;
            }
            int taskId = repository.add(args[1]);
            System.out.println("Created task with id: " + taskId);
        }

        if ("update".equals(action)) {
            if (args.length < 3) {
                System.out.println("Task desription or id not found");
                return;
            }
            int id = Integer.parseInt(args[1]);
            String description = args[2];
            boolean isUpdated = repository.setDescription(id, description);
            System.out.println(isUpdated ? "Task updated" : "Task not found");
        }

        if ("delete".equals(action)) {
            if (args.length < 2) {
                System.out.println("missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isDeleted = repository.delete(id);

            System.out.println(isDeleted ? "Task deleted" : "Task not found");
        }

        if ("list".equals(action)) {
            String format = "id: %d, description: %s, status: %s, created at: %s, updated at: %s";
            List<Task> tasks = repository.findAll();

            if (args.length == 2) {
                Optional<Status> status = Status.fromName(args[1]);
                if (status.isEmpty()) {
                    System.out.println("Invalid status");
                    return;
                }

                tasks = tasks.stream()
                        .filter(t -> t.getStatus().equals(status.get()))
                        .collect(Collectors.toList());
            }

            if (tasks.isEmpty()) {
                System.out.println("No tasks found");
            } else {
                for (Task task : tasks) {
                    System.out.println(String.format(format, task.getId(), task.getDescription(), task.getStatus().name,
                            task.getCreatedAt(), task.getUpdatedAt()));
                }
            }
        }

        if ("mark-done".equals(action)) {
            if (args.length < 2) {
                System.out.println("missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isUpdated = repository.setStatus(id, Status.DONE);
            System.out.println(isUpdated ? "Task updated" : "Task not found");
        }

        if ("mark-in-progress".equals(action)) {
            if (args.length < 2) {
                System.out.println("Missing task id");
                return;
            }
            int id = Integer.parseInt(args[1]);
            boolean isUpdated = repository.setStatus(id, Status.IN_PROGRESS);
            System.out.println(isUpdated ? "Task updated" : "Task not found");
        }
    }
}