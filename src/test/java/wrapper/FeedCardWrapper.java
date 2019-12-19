package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class FeedCardWrapper {
    private final SelenideElement feedCardElm;
    private final String textWrapSlr = ".textWrap";
    private final String postMediaTextSlr = "a.media-text_a";

    public FeedCardWrapper(final SelenideElement feedCardElm) {
        this.feedCardElm = feedCardElm;
    }

    public String getFeedCardText() {
        return feedCardElm.$(textWrapSlr).shouldBe(Condition.visible).text();
    }

    public String getPostURL() {
        return feedCardElm.$(textWrapSlr).$(postMediaTextSlr).shouldHave(Condition.attribute("href")
                .because("We must know URL of this post")).getAttribute("href");
    }
}
