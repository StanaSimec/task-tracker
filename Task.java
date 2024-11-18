import java.time.LocalDateTime;

class Task {
    private final int id;
    private final String name;
    private final Status status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    Task(int id, String name, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    LocalDateTime getCreatedAt() {
        return createdAt;
    }

    LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
