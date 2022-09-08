package uz.jl.springbootfeatures.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.jl.springbootfeatures.SpringBootFeaturesApplication;
import uz.jl.springbootfeatures.services.WorkspaceService;


import javax.annotation.PostConstruct;

@Component
public class ExampleBot extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(SpringBootFeaturesApplication.class);

    private final String token;
    private final String username;

    public ExampleBot(@Value("${bot.token}")String token, @Value("${bot.username}") String username) {
        this.token = token;
        this.username = username;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && !update.getMessage().getText().equals("")) {
            Message message = update.getMessage();
            SendMessage response = new SendMessage();
            response.setChatId("973544840");
            System.out.println("response.getChatId() = " + response.getChatId());
            String chatId = response.getChatId();
            String text = message.getText();
            response.setText(text);
//            if(text.equals("/start")){
//
//            }
            try {
                execute(response);
                logger.info("Sent message \"{}\" to {}", text, chatId);
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", text, chatId, e.getMessage());
            }
        }

    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", username, token);
    }

    public String sendInfo(String info){
        Update update = new Update();
        Message message = new Message();
        message.setText(info);
        update.setMessage(message);
        onUpdateReceived(update);
        return info;
    }

}
