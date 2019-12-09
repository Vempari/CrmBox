package crm.stc21.service;

import crm.stc21.dao.UserDAO;
import crm.stc21.dao.UserDAOImpl;
import crm.stc21.entity.UserEntity;

import java.util.List;

public class UserService {
    UserDAO usersDao = new UserDAOImpl();

    public UserService() {
    }

    public UserEntity findUser(Long id) {
        return usersDao.findById(id);
    }

    public void saveUser(UserEntity user) {
        usersDao.save(user);
    }

    public void deleteUser(UserEntity user) {
        usersDao.delete(user);
    }

    public void updateUser(UserEntity user) {
        usersDao.update(user);
    }

    public List<UserEntity> findAllUsers() {
        return usersDao.findAll();
    }

}
