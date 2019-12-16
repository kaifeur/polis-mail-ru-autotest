import com.codeborne.selenide.Selenide;
import core.DiscussionsPage;
import core.LoginPage;
import core.MainPage;
import core.PostOpenedSubPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.DiscussionWrapper;
import wrapper.FeedCardWrapper;
import wrapper.PostingFormWrapper;

import java.util.List;
import java.util.UUID;

public class DiscussionsTest extends TestBase {
    private static final String COMMENT_TEXT = "Wow nice post";

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

        PostOpenedSubPage postOpenedSubPage = new PostOpenedSubPage();
        postOpenedSubPage.writeNewCommentForPost(COMMENT_TEXT);

        Selenide.closeWindow();

        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot1);
        new MainPage().openDiscussions();
        DiscussionsPage discussionsPage = new DiscussionsPage();
        discussionsPage.openMyDiscussions();
        final List<DiscussionWrapper> discussionWrappers = discussionsPage.getDiscussions();
        discussionWrappers.removeIf(d -> !d.getDiscussionDesc().equals(feedPostText));
        Assert.assertFalse(discussionWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
