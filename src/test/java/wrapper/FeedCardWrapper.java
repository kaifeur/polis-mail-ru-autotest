package wrapper;

import com.codeborne.selenide.SelenideElement;

public class FeedCardWrapper {
    private final SelenideElement feedCardElm;

    public FeedCardWrapper(final SelenideElement feedCardElm) {
        this.feedCardElm = feedCardElm;
    }

    public SelenideElement getFeedCardTextElement() {
        return feedCardElm.$(".textWrap");
    }

    public String getPostURL() {
        return feedCardElm.$(".textWrap > a.media-text_a").getAttribute("href");
    }
}
