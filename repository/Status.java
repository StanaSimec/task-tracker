package repository;

import java.util.Optional;

public enum Status {
    TODO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done");

    public final String value;

    Status(String name) {
        this.value = name;
    }

    public static Optional<Status> fromName(String name) {
        for (Status status : values()) {
            if (status.value.equals(name)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
