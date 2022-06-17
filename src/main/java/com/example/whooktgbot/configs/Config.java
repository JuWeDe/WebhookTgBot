package com.example.whooktgbot.configs;

import com.example.whooktgbot.controllers.WhookTgBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class Config {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;



    @Bean
    public WhookTgBot whookTgBot() {
        DefaultBotOptions defaultBotOptions = ApiContext
                .getInstance(DefaultBotOptions.class);
        defaultBotOptions.setProxyHost(proxyHost);
        defaultBotOptions.setProxyPort(proxyPort);
        defaultBotOptions.setProxyType(proxyType);

        WhookTgBot whookTgBot = new WhookTgBot(defaultBotOptions);
        whookTgBot.setBotToken(botToken);
        whookTgBot.setBotUserName(botUserName);
        whookTgBot.setWebHookPath(webHookPath);

        return whookTgBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


}
