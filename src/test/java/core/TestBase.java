package core;

import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    public static final String BASE_URL = "https://ok.ru/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() {

    }

    @After
    public void cleanUp() {
        Selenide.closeWindow();
    }
}
