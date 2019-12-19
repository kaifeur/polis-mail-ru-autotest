package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import transformer.DiscussionTransformer;
import wrapper.DiscussionWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DiscussionsPage extends BasePage {
    private final String mDialogSlr = ".mdialog";
    private final String mDialogListSlr = ".mdialog_list";
    private final String mDialogListTabsSlr = ".mdialog_list_tabs";
    private final String discussionItemSlr = ".mdialog_list_conversations > div[uid=\"discitem\"]";
    private final String mDialogChatSlr = ".mdialog_chat";
    private final String mDialogChatWindowCntSlr = ".mdialog_chat_window_cnt";
    private final String discussionItemTextSlr = ".media-text_cnt_tx";
    private final String discussionFilterMySlr = "#d-f-tab-fM";

    public DiscussionsPage() {
        super();
    }

    @Override
    protected void check() {
        $(mDialogSlr).shouldBe(Condition.visible);
    }

    public void openMyDiscussions() {
        final SelenideElement mDialogList = $(mDialogListSlr).shouldBe(Condition.visible);
        final SelenideElement discussionTabs = mDialogList.$(mDialogListTabsSlr)
                .shouldBe(Condition.visible);
        discussionTabs.$(discussionFilterMySlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
    }

    public List<DiscussionWrapper> getDiscussions() {
        return DiscussionTransformer.getInstance().transform(
                $$(discussionItemSlr).shouldBe(CollectionCondition.sizeGreaterThan(0)));
    }
}
