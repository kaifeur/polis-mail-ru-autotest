import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.DiscussionsPage;
import core.LoginPage;
import core.MainPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import transformer.DiscussionTransformer;
import wrapper.DiscussionWrapper;
import wrapper.FeedCardWrapper;
import wrapper.PostingFormWrapper;

import java.util.List;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.$;

public class DiscussionsTest extends TestBase {
    @Test
    public void createDiscussion() {
        TestBot testBot1 = TestBot.bot1();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot1);
        new MainPage().clickStatusesOnLeftColumn();

        final StatusesPage statusesPage = new StatusesPage();
        statusesPage.clickOnCreatePost();
        final PostingFormWrapper postingFormWrapper = statusesPage.getPostingFormWrapper();
        final String feedPostText = UUID.randomUUID().toString();
        postingFormWrapper.createFeedPost(feedPostText);

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        final String postURL = feedCardWrapper.getPostURL();

        Selenide.closeWindow();

        TestBot testBot2 = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot2);
        new MainPage();
        Selenide.open(postURL);

        $(".js-comments_add").shouldBe(Condition.enabled)
                .sendKeys("Wow nice post");
        $(".comments_add-controls > button[data-l=\"t,submit\"]")
                .shouldBe(Condition.visible).click();

        Selenide.closeWindow();

        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot1);
        new MainPage().openDiscussions();
        DiscussionsPage discussionsPage = new DiscussionsPage();
        discussionsPage.openMyDiscussions();
        final List<DiscussionWrapper> discussionWrappers = DiscussionTransformer
                .getInstance().transform(discussionsPage.getDiscussions());
        discussionWrappers.removeIf(d -> !d.getDiscussionDesc().equals(feedPostText));
        Assert.assertFalse(discussionWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
