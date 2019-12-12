package crm.stc21.dao;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;
import crm.stc21.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public UserEntity findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    @Override
    public void save(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(UserEntity user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = (List<UserEntity>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From UserEntity").list();
        return users;
    }
}
