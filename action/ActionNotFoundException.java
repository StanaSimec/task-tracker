package action;

public class ActionNotFoundException extends RuntimeException {
    public ActionNotFoundException(String message) {
        super(message);
    }
}
