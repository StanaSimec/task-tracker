class Task {
    private final int id;
    private final String name;
    private final Status status;

    Task(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Status getStatus() {
        return status;
    }
}
