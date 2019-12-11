package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDaoImpl implements TaskDao{

    @Override
    public List<TaskEntity> findAll() {
        List<TaskEntity> tasks = (List<TaskEntity>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From TaskEntity ").list();
        return tasks;
    }

    @Override
    public TaskEntity findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(TaskEntity.class, id);
    }

    @Override
    public void save(TaskEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(TaskEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(TaskEntity task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
    }
}
