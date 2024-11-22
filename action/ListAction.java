package action;

import java.util.List;
import java.util.Optional;

import repository.Status;
import repository.Task;
import repository.TaskRepository;
import validation.ValidationException;

public final class ListAction implements Action {

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new ValidationException("Status is not provided");
        }
        Optional<Status> status = Status.fromName(args[1]);
        if (status.isEmpty()) {
            throw new ValidationException("Invalid status");
        }

        String format = "id: %d, description: %s, status: %s, created at: %s, updated at: %s";
        List<Task> tasks = TaskRepository.findAll();

        if (args.length == 2) {
            tasks = tasks.stream()
                    .filter(t -> t.getStatus().equals(status.get()))
                    .toList();
        }

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            for (Task task : tasks) {
                System.out.println(String.format(format, task.getId(), task.getDescription(), task.getStatus().value,
                        task.getCreatedAt(), task.getUpdatedAt()));
            }
        }
    }

}
