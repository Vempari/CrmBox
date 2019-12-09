package crm.stc21.service;

import crm.stc21.dao.InboxDao;
import crm.stc21.dao.InboxDaoImpl;
import crm.stc21.entity.IncomingBoxEntity;

import java.util.List;

public class InboxService {
    InboxDao inboxDao = new InboxDaoImpl();

    public InboxService(){

    }

    public List<IncomingBoxEntity> findAllInbox() {
        return inboxDao.findAll();
    }
}
