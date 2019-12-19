package crm.stc21;

import crm.stc21.botComponent.InboxComponent;
import crm.stc21.botComponent.TaskComponent;
import crm.stc21.botComponent.UserComponent;
import crm.stc21.entity.UserEntity;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    UserComponent userComponent = new UserComponent();
    InboxComponent inboxComponent = new InboxComponent();
    TaskComponent taskComponent = new TaskComponent();
    UserEntity currentUser;
    String username = "";


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
        StringBuilder stringToOutput = new StringBuilder();

        switch (text) {
            //MAIN MENU
            case "Посмотреть все задачи":
                if (currentUser.getRole().getId().equals(1L) || currentUser.getRole().getId().equals(2L)){
                    mainMenu(keyboard, leftRow, rightRow);
                    stringToOutput = taskComponent.showAllTasks(stringToOutput);
                    return stringToOutput.toString();
                } else return "Пожалуйста, войдите в меню и нажмите на кнопку";
            case "Посмотреть входящую канцелярию":
                if (currentUser.getRole().getId().equals(1L) || currentUser.getRole().getId().equals(2L)) {
                    mainMenu(keyboard, leftRow, rightRow);
                    stringToOutput = inboxComponent.showAllInbox(stringToOutput);
                    return stringToOutput.toString();
                } else return "Пожалуйста, войдите в меню и нажмите на кнопку";
            case "Посмотреть мои задачи":
                mainMenu(keyboard, leftRow, rightRow);
                stringToOutput = userComponent.showUserTasks(stringToOutput, username);
                return stringToOutput.toString();
            case "Посмотреть информацию обо мне":
                mainMenu(keyboard, leftRow, rightRow);
                stringToOutput = userComponent.showInformationAboutUser(stringToOutput, username);
                return stringToOutput.toString();
        }
        return "Пожалуйста, войдите в меню и нажмите на кнопку";
    }


    public void mainMenu(ArrayList<KeyboardRow> keyboard, KeyboardRow leftRow, KeyboardRow rightRow) {
        keyboard.clear();
        leftRow.clear();
        rightRow.clear();
        if (currentUser.getRole().getId().equals(1L) || currentUser.getRole().getId().equals(2L)) {
            rightRow.add("Посмотреть все задачи");
            leftRow.add("Посмотреть входящую канцелярию");
        }
        leftRow.add("Посмотреть мои задачи");
        rightRow.add("Посмотреть информацию обо мне");
        keyboard.add(leftRow);
        keyboard.add(rightRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public void onUpdateReceived(Update e) {
        e.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(e.getMessage().getChatId());
        username = e.getMessage().getFrom().getUserName();
        long chat_id = e.getMessage().getChatId();
        String text = e.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        boolean validation = false;
        for (UserEntity user : userComponent.findAllUsers()) {
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
