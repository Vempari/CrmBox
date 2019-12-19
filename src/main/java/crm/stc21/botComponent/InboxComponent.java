package crm.stc21.botComponent;

import crm.stc21.entity.IncomingBoxEntity;
import crm.stc21.entity.UserEntity;
import crm.stc21.service.InboxService;

import java.util.List;

public class InboxComponent {
    InboxService inboxService = new InboxService();

    public StringBuilder showAllInbox(StringBuilder result) {
        for (IncomingBoxEntity entity : inboxService.findAllInbox()) {
            result.append(entity.toString()).append("\n");
        }
        return result;
    }
}
