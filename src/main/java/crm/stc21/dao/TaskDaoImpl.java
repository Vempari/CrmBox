package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;
import crm.stc21.util.HibernateSessionFactoryUtil;

import java.util.List;

public class TaskDaoImpl implements TaskDao{

    @Override
    public List<TaskEntity> findAll() {
        List<TaskEntity> tasks = (List<TaskEntity>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From TaskEntity ").list();
        return tasks;
    }
}
