package action;

import model.TaskService;
import validation.ValidationException;

public final class AddAction implements Action {

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new ValidationException("Task description is not provided");
        }
        int taskId = TaskService.add(args[1]);
        System.out.println("Created task with id: " + taskId);
    }
}
