package action;

import java.util.Map;
import java.util.function.Supplier;

public final class ActionFactory {

    private ActionFactory(){}

    private static final Map<String, Supplier<Action>> actions = Map.of(
            "add", AddAction::new,
            "update", UpdateAction::new,
            "delete", DeleteAction::new,
            "list", ListAction::new,
            "mark-done", MarkDoneAction::new,
            "mark-in-progress", MarkInProgressAction::new);

    public static Action findAction(String actionType) {
        Supplier<Action> actionSupplier = actions.get(actionType);
        if (actionSupplier == null) {
            throw new ActionNotFoundException();
        }
        return actionSupplier.get();
    }
}
