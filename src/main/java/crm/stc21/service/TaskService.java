package crm.stc21.service;

import crm.stc21.dao.TaskDao;
import crm.stc21.dao.TaskDaoImpl;
import crm.stc21.entity.TaskEntity;

import java.util.List;

public class TaskService {
    TaskDao taskDao = new TaskDaoImpl();

    public TaskService() {

    }

    public List<TaskEntity> findAllTasks() {
        return taskDao.findAll();
    }
}
