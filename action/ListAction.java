package action;

import model.Status;
import model.Task;
import model.TaskService;
import validation.ValidationException;

import java.util.List;
import java.util.Optional;

public final class ListAction implements Action {

    @Override
    public void execute(String[] args) {
        String format = "id: %d, description: %s, status: %s, created at: %s, updated at: %s";
        List<Task> tasks = TaskService.findAll();

        if (args.length >= 2) {
            Optional<Status> status = Status.fromName(args[1]);

            if (status.isEmpty()) {
                throw new ValidationException("Invalid status");
            }
            tasks = tasks.stream()
                    .filter(t -> t.getStatus().equals(status.get()))
                    .toList();
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            for (Task task : tasks) {
                System.out.printf((format) + "%n", task.getId(), task.getDescription(), task.getStatus().value,
                        task.getCreatedAt(), task.getUpdatedAt());
            }
        }
    }

}
