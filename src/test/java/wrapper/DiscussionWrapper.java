package wrapper;

import com.codeborne.selenide.SelenideElement;

public class DiscussionWrapper {
    private final SelenideElement discussionElm;

    public DiscussionWrapper(SelenideElement discussionElm) {
        this.discussionElm = discussionElm;
    }

    public String getDiscussionDesc() {
        return discussionElm.$(".disc-i_cnt_group_theme").text();
    }
}
