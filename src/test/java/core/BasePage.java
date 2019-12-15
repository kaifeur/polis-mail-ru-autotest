package core;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public abstract class BasePage {
    public static final String BASE_URL = "http://ok.ru";
    private static final SelenideElement logo = $("a#toolbar_logo_id");

    public BasePage() {
        check();
    }

    public static void openMainPage() {
        logo.click();
    }

    protected abstract void check();
}
