package action;

import repository.TaskRepository;
import validation.IdValidator;
import validation.ValidationException;

public final class UpdateAction implements Action {

    @Override
    public void execute(String[] args) {
        IdValidator.validate(args);
        if (args.length < 3) {
            throw new ValidationException("Task desription is not provided");
        }

        int id = Integer.parseInt(args[1]);
        String description = args[2];
        boolean isUpdated = TaskRepository.setDescription(id, description);
        System.out.println(isUpdated ? "Task updated" : "Task not found");
    }
}
