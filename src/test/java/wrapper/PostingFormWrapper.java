package wrapper;

import com.codeborne.selenide.SelenideElement;

import java.util.UUID;

public class PostingFormWrapper {
    private final SelenideElement postingFormElm;
    private SelenideElement postingItx;
    private SelenideElement postingButton;

    public PostingFormWrapper(final SelenideElement postingFormElm) {
        this.postingFormElm = postingFormElm;
        postingItx = postingFormElm.$(".posting_itx");
        postingButton = postingFormElm.$(".posting_submit");
    }

    public String createFeedPost() {
        final String postText = UUID.randomUUID().toString();
        postingItx.sendKeys(postText);
        postingButton.click();
        return postText;
    }
}
