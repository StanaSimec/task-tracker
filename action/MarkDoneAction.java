package action;

import model.Status;
import model.TaskService;
import validation.IdValidator;

public final class MarkDoneAction implements Action {

    @Override
    public void execute(String[] args) {
        IdValidator.validate(args);
        int id = Integer.parseInt(args[1]);
        boolean isUpdated = TaskService.setStatus(id, Status.DONE);
        System.out.println(isUpdated ? "Task updated" : "Task not found");
    }
}
