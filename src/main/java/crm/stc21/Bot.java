package crm.stc21;

import crm.stc21.entity.IncomingBoxEntity;
import crm.stc21.entity.TaskEntity;
import crm.stc21.entity.UserEntity;
import crm.stc21.service.InboxService;
import crm.stc21.service.TaskService;
import crm.stc21.service.UserService;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    UserService userService = new UserService();
    TaskService taskService = new TaskService();
    InboxService inboxService = new InboxService();
    UserEntity currentUser;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getMessage(String text, Message message) {
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow leftRow = new  KeyboardRow();
        KeyboardRow rightRow = new KeyboardRow();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        mainMenu(keyboard, leftRow, rightRow);
        StringBuilder result = new StringBuilder();
        switch (text) {
            case "Вернуться обратно":
                mainMenu(keyboard, leftRow, rightRow);
                return "Добро пожаловать в меню!";
            case "Посмотреть мои задачи":
                result.append("Задачи: \n");
                for (TaskEntity task : taskService.findAllTasks()) {
                        result.append(task.toString()).append("\n");
                }
                mainMenu(keyboard, leftRow, rightRow);
                return result.toString();
            case "Посмотреть информацию обо мне":
                mainMenu(keyboard, leftRow, rightRow);
                for (UserEntity entity : userService.findAllUsers()) {
                    if (entity.getTelegramUsername() != null &&
                            entity.getTelegramUsername().equals("@" + message.getFrom().getUserName())) {
                        result.append(entity);
                    }
                }
                return result.toString();
            case "Посмотреть канцелярию":
                result.append("Входящая канцелярия: \n");
                for (IncomingBoxEntity entity : inboxService.findAllInbox()) {
                    result.append("\n").append(entity.toString());
                }
                mainMenu(keyboard, leftRow, rightRow);
                return result.toString();

        }
        return "";
    }

    public void mainMenu(ArrayList<KeyboardRow> keyboard, KeyboardRow leftRow, KeyboardRow rightRow) {
        keyboard.clear();
        leftRow.clear();
        rightRow.clear();
        leftRow.add("Посмотреть мои задачи");
        leftRow.add("Посмотреть информацию обо мне");
        rightRow.add("Посмотреть канцелярию");
        keyboard.add(leftRow);
        keyboard.add(rightRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public void onUpdateReceived(Update e) {
        e.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(e.getMessage().getChatId());
        String username = e.getMessage().getFrom().getUserName();
        long chat_id = e.getMessage().getChatId();
        String text = e.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        boolean validation = false;
        for (UserEntity user : userService.findAllUsers()) {
            if (user.getTelegramUsername()!= null && user.getTelegramUsername().equals("@" + username)) {
                validation = true;
                currentUser = user;
            }
        }

        if (text.equals("/start") && validation) {
            try {
                sendMessage.setText(
                        "Привет " + currentUser.getFirstName() + ", добро пожаловать в CRM!\n" +
                        "Выберите один из предложенных вариантов"
                );
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        } else if (validation){
            try {
                sendMessage.setText(getMessage(text, e.getMessage()));
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                sendMessage.setText("Извините, но вашего аккаунта нет в системе.");
                execute(sendMessage);
            } catch (TelegramApiException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return "USER";
    }
    @Override
    public String getBotToken() {
        return "929177711:AAHkNyt0rKX40DroaC6zwa7xbe1Lx2FB6nY";
        //Токен бота
    }


}
