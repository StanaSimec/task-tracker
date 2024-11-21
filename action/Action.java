package action;

import repository.TaskRepository;

public abstract class Action {

    protected final TaskRepository repository;

    Action() {
        repository = new TaskRepository();
    }

    abstract boolean validateArguments(String[] args);

    abstract void handleAction(String[] args);

    public void execute(String[] args) {
        validateArguments(args);
        handleAction(args);
    }
}
