package properties;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class BotProperties {
    private static final String CONFIG_BOT_DEFAULT = "bot.properties";
    private static Properties botProperties = new Properties();

    private BotProperties() {
    }

    public static Properties getBotProperties() {
        try {
            botProperties.load(Objects.requireNonNull(
                    ClassLoader.getSystemResourceAsStream(CONFIG_BOT_DEFAULT)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botProperties;
    }
}
