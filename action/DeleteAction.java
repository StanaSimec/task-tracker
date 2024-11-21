package action;

class DeleteAction extends Action {

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
        boolean isDeleted = repository.delete(id);
        System.out.println(isDeleted ? "Task deleted" : "Task not found");
    }
}
