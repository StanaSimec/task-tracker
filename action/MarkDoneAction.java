package action;

import repository.Status;

class MarkDoneAction extends MarkStatusAction {

    @Override
    Status getStatus() {
        return Status.DONE;
    }
}
