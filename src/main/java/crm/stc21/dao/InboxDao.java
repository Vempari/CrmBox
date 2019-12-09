package crm.stc21.dao;

import crm.stc21.entity.IncomingBoxEntity;
import crm.stc21.entity.TaskEntity;

import java.util.List;

public interface InboxDao {
    public List<IncomingBoxEntity> findAll();
}
