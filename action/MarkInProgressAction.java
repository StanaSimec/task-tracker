package action;

import repository.Status;
import repository.TaskRepository;
import validation.IdValidator;

public final class MarkInProgressAction implements Action {

    @Override
    public void execute(String[] args) {
        IdValidator.validate(args);
        int id = Integer.parseInt(args[1]);
        boolean isUpdated = TaskRepository.setStatus(id, Status.IN_PROGRESS);
        System.out.println(isUpdated ? "Task updated" : "Task not found");
    }
}
