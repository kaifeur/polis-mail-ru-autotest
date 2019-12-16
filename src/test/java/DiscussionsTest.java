import block.PostingFormBlock;
import com.codeborne.selenide.Selenide;
import core.DiscussionsPage;
import core.LoginPage;
import core.MainPage;
import core.PostOpenedSubPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import wrapper.DiscussionWrapper;
import wrapper.FeedCardWrapper;

import java.util.List;

public class DiscussionsTest extends TestBase {
    private static final String COMMENT_TEXT = "Wow nice post";

    @Test
    public void createDiscussion() {
        TestBot testBot1 = TestBot.bot1();
        MainPage mainPage = new LoginPage().doLogin(testBot1);
        StatusesPage statusesPage = mainPage.clickStatusesOnLeftColumn();

        statusesPage.clickOnCreatePost();
        final PostingFormBlock postingFormBlock = statusesPage.getPostingFormBlock();
        final String feedPostText = RandomStringUtils.randomAlphabetic(16);
        postingFormBlock.createFeedPost(feedPostText);

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        final String postURL = feedCardWrapper.getPostURL();

        Selenide.closeWindow();

        TestBot testBot2 = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot2);
        Selenide.open(postURL);

        PostOpenedSubPage postOpenedSubPage = new PostOpenedSubPage();
        postOpenedSubPage.writeNewCommentForPost(COMMENT_TEXT);

        Selenide.closeWindow();

        Selenide.open(BASE_URL);
        mainPage = new LoginPage().doLogin(testBot1);
        DiscussionsPage discussionsPage = mainPage.openDiscussions();
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
