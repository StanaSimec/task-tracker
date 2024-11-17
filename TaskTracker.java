class TaskTracker {

    private TaskRepository repository;

    TaskTracker(String[] args) {
        repository = new TaskRepository();
        execute(args);
    }

    void execute(String[] args) {
        if (args.length == 0) {
            System.out.println("action not found");
            return;
        }

        if ("add".equals(args[0])) {
            if (args.length < 2) {
                System.out.println("task name not found");
                return;
            }
            repository.add(args[1]);
        }
    }
}