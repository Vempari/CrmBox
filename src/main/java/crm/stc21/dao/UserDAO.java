package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    public UserEntity findById(Long id);
    public void save(UserEntity user);
    public void update(UserEntity user);
    public void delete(UserEntity user);
    public List<UserEntity> findAll();
}
