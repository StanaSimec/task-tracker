package action;

import model.TaskService;
import validation.IdValidator;

public final class DeleteAction implements Action {

    @Override
    public void execute(String[] args) {
        IdValidator.validate(args);
        int id = Integer.parseInt(args[1]);
        boolean isDeleted = TaskService.delete(id);
        System.out.println(isDeleted ? "Task deleted" : "Task not found");
    }
}
