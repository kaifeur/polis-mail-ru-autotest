package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StatusesPage extends BasePage {
    public StatusesPage() {
        super();
    }

    public void clickOnCreatePost() {
        $(".pf-head_itx").click();
    }

    public ElementsCollection getFeedCards() {
        return $$(".feed");
    }

    public SelenideElement getPostingFormElm() {
        return $(".posting");
    }

    @Override
    protected void check() {
        $(".pf-head_itx").shouldBe(Condition.visible.because("If it isn't visible then we can't create a post"));
    }
}
