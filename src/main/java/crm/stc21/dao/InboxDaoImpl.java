package crm.stc21.dao;

import crm.stc21.entity.IncomingBoxEntity;
import crm.stc21.entity.TaskEntity;
import crm.stc21.util.HibernateSessionFactoryUtil;

import java.util.List;

public class InboxDaoImpl implements InboxDao {
    @Override
    public List<IncomingBoxEntity> findAll() {
        List<IncomingBoxEntity> inbox = (List<IncomingBoxEntity>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From IncomingBoxEntity").list();
        return inbox;
    }
}
