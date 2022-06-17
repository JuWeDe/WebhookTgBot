package com.example.whooktgbot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WhookTgBot extends TelegramWebhookBot {

    String webHookPath;
    String botUserName;
    String botToken;



    public WhookTgBot(DefaultBotOptions botOptions) {
        super(botOptions);

    }

    /**
     * This method is called when receiving updates via webhook
     *
     * @param update Update received
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            try {
                execute(new SendMessage(chat_id, " hi test" + update.getMessage().getText()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * Gets bot username of this bot
     *
     * @return Bot username
     */
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    /**
     * Returns the token of the bot to be able to perform Telegram Api Requests
     *
     * @return Token of the bot
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * Gets in the url for the webhook
     *
     * @return path in the url
     */
    @Override
    public String getBotPath() {
        return webHookPath;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
