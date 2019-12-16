package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class FeedCardWrapper {
    private final SelenideElement feedCardElm;
    private final String textWrapSlr = ".textWrap";

    public FeedCardWrapper(final SelenideElement feedCardElm) {
        this.feedCardElm = feedCardElm;
    }

    public String getFeedCardText() {
        return feedCardElm.$(textWrapSlr).shouldBe(Condition.visible).text();
    }

    public String getPostURL() {
        return feedCardElm.$(".textWrap > a.media-text_a").getAttribute("href");
    }
}
