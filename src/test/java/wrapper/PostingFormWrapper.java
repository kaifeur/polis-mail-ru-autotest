package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PostingFormWrapper {
    private final SelenideElement postingFormElm;
    private SelenideElement postingItx;
    private SelenideElement postingButton;

    public PostingFormWrapper(final SelenideElement postingFormElm) {
        this.postingFormElm = postingFormElm;
        postingItx = postingFormElm.$(".posting_itx");
        postingButton = postingFormElm.$(".posting_submit");
    }

    public void createFeedPost(final String postText) {
        postingItx.sendKeys(postText);
        postingButton.click();
        postingFormElm.shouldNotBe(Condition.exist);
    }
}
