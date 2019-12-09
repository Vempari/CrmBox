package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;

import java.util.List;

public interface TaskDao {
    public List<TaskEntity> findAll();
}
