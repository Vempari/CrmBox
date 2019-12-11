package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;

import java.util.List;

public interface TaskDao {
    public TaskEntity findById(Long id);
    public List<TaskEntity> findAll();
    public void save(TaskEntity task);
    public void update(TaskEntity task);
    public void delete(TaskEntity task);
}
