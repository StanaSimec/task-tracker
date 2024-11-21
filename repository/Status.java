package repository;

import java.util.Optional;

public enum Status {
    TODO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    public final String name;

    private Status(String name) {
        this.name = name;
    }

    public static Optional<Status> fromName(String name) {
        for (Status status : values()) {
            if (status.name.equals(name)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
