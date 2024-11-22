package validation;

import repository.TaskRepository;

public class IdValidator {

    private IdValidator(){}

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new ValidationException("Id is not provided");
        }
        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid task id");
        }
        if (!TaskRepository.existsById(id)) {
            throw new ValidationException("Task not found");
        }
    }
}
