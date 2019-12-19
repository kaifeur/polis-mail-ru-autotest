package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    public static final String BASE_URL = "http://ok.ru";
    private static final SelenideElement logo = $("a#toolbar_logo_id");

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage() {
        check();
    }

    public static MainPage openMainPage() {
        logo.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new MainPage();
    }

    protected abstract void check();
}
