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
        logger.info("Trying to log in with credentials: {}", testBot1);
        MainPage mainPage = new LoginPage().doLogin(testBot1);
        logger.info("Logged in successfully");
        StatusesPage statusesPage = mainPage.clickStatusesOnLeftColumn();

        logger.info("Creating a new post");
        statusesPage.clickOnCreatePost();
        final PostingFormBlock postingFormBlock = statusesPage.getPostingFormBlock();
        final String feedPostText = RandomStringUtils.randomAlphabetic(16);
        logger.info("Post text: {}", feedPostText);
        postingFormBlock.createFeedPost(feedPostText);
        logger.info("New post created successfully");

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        final String postURL = feedCardWrapper.getPostURL();
        logger.info("New post URL: {}", postURL);

        Selenide.closeWindow();
        logger.info("Window and session closed");

        TestBot testBot2 = TestBot.bot2();
        Selenide.open(BASE_URL);
        logger.info("Trying to log in with credentials: {}", testBot2);
        new LoginPage().doLogin(testBot2);
        logger.info("Logged in successfully");
        logger.info("Opening post URL");
        Selenide.open(postURL);

        PostOpenedSubPage postOpenedSubPage = new PostOpenedSubPage();
        logger.info("Post page opened successfully");

        postOpenedSubPage.writeNewCommentForPost(COMMENT_TEXT);
        logger.info("New comment was wrote: {}", COMMENT_TEXT);

        Selenide.closeWindow();
        logger.info("Window and session closed");

        Selenide.open(BASE_URL);
        logger.info("Trying to log in with credentials: {}", testBot1);
        mainPage = new LoginPage().doLogin(testBot1);
        logger.info("Logged in successfully");

        DiscussionsPage discussionsPage = mainPage.openDiscussions();
        discussionsPage.openMyDiscussions();
        logger.info("My discussions was opened");

        final List<DiscussionWrapper> discussionWrappers = discussionsPage.getDiscussions();
        logger.info("Discussion list size: {}", discussionWrappers.size());
        discussionWrappers.removeIf(d -> !d.getDiscussionDesc().equals(feedPostText));

        Assert.assertFalse(discussionWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
    }
}
