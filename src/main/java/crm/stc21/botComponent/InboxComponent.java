package crm.stc21.botComponent;

import crm.stc21.entity.UserEntity;
import crm.stc21.service.InboxService;

import java.util.List;

public class InboxComponent {
    InboxService inboxService = new InboxService();

    public StringBuilder showAllInbox(StringBuilder result, List<UserEntity> userList, String username) {
        for (UserEntity entity : userList) {
            if (entity.getTelegramUsername() != null &&
                    entity.getTelegramUsername().equals("@" + username)) {
                result.append(entity);
            }
        }
        return result;
    }
}
