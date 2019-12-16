package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class DiscussionWrapper {
    private final SelenideElement discussionElm;
    private final String discTextSlr = ".disc-i_cnt_group_theme";

    public DiscussionWrapper(SelenideElement discussionElm) {
        this.discussionElm = discussionElm;
    }

    public String getDiscussionDesc() {
        return discussionElm.$(discTextSlr).text();
    }

    public void clickOnDiscussion() {
        discussionElm.shouldBe(Condition.enabled).click();
    }
}
