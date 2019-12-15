package model;

import properties.BotProperties;
import properties.PropsList;

public class TestBot {
    private final String login;
    private final String password;

    public TestBot(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static TestBot bot1() {
        return new TestBot(BotProperties.getBotProperties().getProperty(PropsList.BOT1_LOGIN),
                BotProperties.getBotProperties().getProperty(PropsList.BOT1_PASSWORD));
    }

    public static TestBot bot2() {
        return new TestBot(BotProperties.getBotProperties().getProperty(PropsList.BOT2_LOGIN),
                BotProperties.getBotProperties().getProperty(PropsList.BOT2_PASSWORD));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
