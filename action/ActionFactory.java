package action;

import java.util.Map;
import java.util.function.Supplier;

public final class ActionFactory {

    private static final Map<String, Supplier<Action>> actions = Map.of(
            "add", AddAction::new,
            "update", UpdateAction::new,
            "delete", DeleteAction::new,
            "list", ListAction::new,
            "mark-done", MarkDoneAction::new,
            "mark-in-progress", MarkInProgressAction::new);

    private ActionFactory() {
    }

    public static Action findAction(String actionType) {
        Supplier<Action> actionSupplier = actions.get(actionType);
        if (actionSupplier == null) {
            throw new ActionNotFoundException("Action not found");
        }
        return actionSupplier.get();
    }
}
