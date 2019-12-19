package crm.stc21.botComponent;

import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;
import crm.stc21.service.UserService;

import java.util.List;

public class UserComponent {
    UserService userService = new UserService();


    public StringBuilder showInformationAboutUser(StringBuilder result, String username) {
        for (UserEntity entity : userService.findAllUsers()) {
            if (entity.getTelegramUsername() != null &&
                    entity.getTelegramUsername().equals("@" + username)) {
                result.append(entity);
            }
        }
        return result;
    }

    public StringBuilder showUserTasks (StringBuilder result, String username) {
        for (UserEntity entity : userService.findAllUsers()) {
            if (entity.getTelegramUsername() != null &&
                    entity.getTelegramUsername().equals("@" + username)) {
                    for (TaskEntity task : entity.getTasks()) {
                        if (!task.getTaskStatus().getId().equals(1L))  {
                            result.append(task).append("\n");
                        }
                    }
            }
        }
        if (result.toString().equals("")) result.append("У вас нет задач");
        return result;
    }
    public List<UserEntity> findAllUsers() {
        return userService.findAllUsers();
    }
}
