package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import model.TestBot;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final String LOGIN_FORM_SELECTOR = "form[action='https://www.ok.ru/https']";

    public LoginPage() {
        super();
    }

    public void doLogin(TestBot testBot) {
        typeInInputField($("input[data-l$=login]"), testBot.getLogin());
        typeInInputField($("input[data-l$=password]"), testBot.getPassword());
        $(LOGIN_FORM_SELECTOR).submit();
//        $("[data-l$=sign_in]").click();
    }

    private void typeInInputField(final SelenideElement inputFieldElement, final String value) {
        inputFieldElement.sendKeys(value);
    }

    @Override
    protected void check() {
        $(LOGIN_FORM_SELECTOR).shouldBe(Condition.visible.because("Login form should be visible to be able to log in"));
    }
}
