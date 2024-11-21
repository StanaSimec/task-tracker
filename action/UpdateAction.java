package action;

class UpdateAction extends Action {

    @Override
    boolean validateArguments(String[] args) {
        if (args.length < 3) {
            System.out.println("Task desription or id not found");
            return false;
        }
        return true;
    }

    @Override
    void handleAction(String[] args) {
        int id = Integer.parseInt(args[1]);
        String description = args[2];
        boolean isUpdated = repository.setDescription(id, description);
        System.out.println(isUpdated ? "Task updated" : "Task not found");
    }
}
