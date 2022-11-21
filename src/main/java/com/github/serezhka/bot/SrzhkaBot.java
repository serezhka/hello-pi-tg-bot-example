package com.github.serezhka.bot;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log
@Component
public class SrzhkaBot extends TelegramLongPollingBot {

    @Value("${username}")
    private String username;

    @Value("${token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.toString());

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().startsWith("/sayhello")) {
                try {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
                    sendMessage.setText("Bot says hello! Hosted on Raspberry Pi");
                    execute(sendMessage);
                } catch (Exception e) {
                    log.severe(e.getMessage());
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
