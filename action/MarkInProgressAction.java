package action;

import repository.Status;

class MarkInProgressAction extends MarkStatusAction {

    @Override
    Status getStatus() {
        return Status.IN_PROGRESS;
    }
}
