package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StatusesPage extends BasePage {

    private final String postFeedHeadSlr = ".pf-head_itx";
    private final String feedCardSlr = ".feed";
    private final String postingFormSlr = ".posting";

    public StatusesPage() {
        super();
    }

    public void clickOnCreatePost() {
        $(postFeedHeadSlr).click();
    }

    public ElementsCollection getFeedCards() {
        return $$(feedCardSlr);
    }

    public SelenideElement getPostingFormElm() {
        return $(postingFormSlr);
    }

    @Override
    protected void check() {
        $(postFeedHeadSlr).shouldBe(Condition.visible.because("If it isn't visible then we can't create a post"));
    }
}
