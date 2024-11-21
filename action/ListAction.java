package action;

import java.util.List;
import java.util.Optional;

import repository.Status;
import repository.Task;

class ListAction extends Action {

    @Override
    boolean validateArguments(String[] args) {
        if (args.length == 2) {
            Optional<Status> status = Status.fromName(args[1]);
            if (status.isEmpty()) {
                System.out.println("Invalid status");
                return false;
            }
        }
        return true;
    }

    @Override
    void handleAction(String[] args) {
        String format = "id: %d, description: %s, status: %s, created at: %s, updated at: %s";
        List<Task> tasks = repository.findAll();

        if (args.length == 2) {
            Optional<Status> status = Status.fromName(args[1]);
            tasks = tasks.stream()
                    .filter(t -> t.getStatus().equals(status.get()))
                    .toList();
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

}
