package action;

class AddAction extends Action {

    @Override
    boolean validateArguments(String[] args) {
        if (args.length < 2) {
            System.out.println("Task description not found");
            return false;
        }
        return true;
    }

    @Override
    void handleAction(String[] args) {
        int taskId = repository.add(args[1]);
        System.out.println("Created task with id: " + taskId);
    }

}
