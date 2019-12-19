package block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.StatusesPage;

public class PostingFormBlock {
    private final SelenideElement postingFormElm;
    private SelenideElement postingItx;
    private SelenideElement postingButton;
    private String postingItxInputSlr = ".posting_itx";
    private String postingSubmitSlr = ".posting_submit";

    public PostingFormBlock(final SelenideElement postingFormElm) {
        this.postingFormElm = postingFormElm;
        postingItx = postingFormElm.$(postingItxInputSlr);
        postingButton = postingFormElm.$(postingSubmitSlr);
    }

    public StatusesPage createFeedPost(final String postText) {
        postingItx.sendKeys(postText);
        postingButton.click();
        postingFormElm.shouldNotBe(Condition.exist
                .because("Posting form must be closed after creating post"));
        return new StatusesPage();
    }
}
