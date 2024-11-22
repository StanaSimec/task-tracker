package action;

import repository.TaskRepository;
import validation.IdValidator;

public final class DeleteAction implements Action {

    @Override
    public void execute(String[] args) {
        IdValidator.validate(args);
        int id = Integer.parseInt(args[1]);
        boolean isDeleted = TaskRepository.delete(id);
        System.out.println(isDeleted ? "Task deleted" : "Task not found");
    }
}
