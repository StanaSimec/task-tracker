package action;

import model.TaskService;
import validation.ValidationException;

import java.util.Optional;

public final class AddAction implements Action {

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new ValidationException("Task description is not provided");
        }
        Optional<Integer> taskId = TaskService.add(args[1]);
        System.out.println(taskId.map(integer -> "Created task with id: " + integer)
                .orElse("Task was not created."));
    }
}
