package crm.stc21.botComponent;

import crm.stc21.entity.TaskEntity;
import crm.stc21.service.TaskService;

public class TaskComponent {
    TaskService taskService = new TaskService();


    public StringBuilder showAllTasks(StringBuilder result) {
        result.append("Задачи: \n");
        for (TaskEntity task : taskService.findAllTasks()) {
            result.append(task.toString()).append("\n");
        }
        return result;
    }

    public StringBuilder addTask(StringBuilder result) {

        return result;
    }

    public  StringBuilder deleateTask(StringBuilder result) {
        result.append("Введите id задачи");
        return result;
    }
}
