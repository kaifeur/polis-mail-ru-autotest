package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import wrapper.FeedCardWrapper;
import wrapper.PostingFormWrapper;

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
        $(postFeedHeadSlr).shouldBe(Condition.visible).click();
    }

    public FeedCardWrapper getFirstFeedCard() {
        return new FeedCardWrapper($$(feedCardSlr).shouldBe(CollectionCondition.sizeGreaterThan(0)).first());
    }

    public PostingFormWrapper getPostingFormWrapper() {
        return new PostingFormWrapper($(postingFormSlr).shouldBe(Condition.visible));
    }

    @Override
    protected void check() {
        $(postFeedHeadSlr).shouldBe(Condition.visible.because("If it isn't visible then we can't create a post"));
    }
}
