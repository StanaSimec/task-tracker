import action.Action;
import action.ActionFactory;
import action.ActionNotFoundException;

public class TaskTracker {

    public static void main(String[] args) {
        try {
            Action action = ActionFactory.findAction(args[0]);
            action.execute(args);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Action not provided.");
        } catch (ActionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}