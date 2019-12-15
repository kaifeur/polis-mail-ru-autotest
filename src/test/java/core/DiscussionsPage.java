package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DiscussionsPage extends BasePage {
    public DiscussionsPage() {
        super();
    }

    @Override
    protected void check() {
        $(".mdialog").shouldBe(Condition.visible);
    }

    public void openMyDiscussions() {
        final SelenideElement mDialogList = $(".mdialog_list").shouldBe(Condition.visible);
        final SelenideElement discussionTabs = mDialogList.$(".mdialog_list_tabs").shouldBe(Condition.visible);
        discussionTabs.$("#d-f-tab-fM").click();
    }

    @Deprecated
    public void openNewestDiscussion() {
        final ElementsCollection discs = $$(".mdialog_list_conversations > div[uid=\"discitem\"]");
        discs.shouldBe(CollectionCondition.sizeGreaterThan(0));
        final SelenideElement lastDiscussion = discs.first();
        lastDiscussion.click();
    }

    public ElementsCollection getDiscussions() {
        return $$(".mdialog_list_conversations > div[uid=\"discitem\"]");
    }

    public String getOpenedDiscussionText() {
        final SelenideElement mDialogChatElm = $(".mdialog_chat").shouldBe(Condition.visible);
        final SelenideElement discWindow = mDialogChatElm.$(".mdialog_chat_window_cnt").shouldBe(Condition.visible);
        return discWindow.$(".media-text_cnt_tx").shouldBe(Condition.not(Condition.empty)).text();
    }
}
