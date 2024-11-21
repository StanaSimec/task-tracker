package action;

import repository.Status;

abstract class MarkStatusAction extends Action {

    abstract Status getStatus();

    @Override
    boolean validateArguments(String[] args) {
        if (args.length < 2) {
            System.out.println("missing task id");
            return false;
        }
        return true;
    }

    @Override
    void handleAction(String[] args) {
        int id = Integer.parseInt(args[1]);
        boolean isUpdated = repository.setStatus(id, getStatus());
        System.out.println(isUpdated ? "Task updated" : "Task not found");
    }
}
