package core;

import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;

public class TestBase {
    public static final String BASE_URL = "https://ok.ru/";

    @Before
    public void setUp() {

    }

    @After
    public void cleanUp() {
        Selenide.closeWindow();
    }
}
