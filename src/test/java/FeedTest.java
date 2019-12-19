import block.PostingFormBlock;
import core.LoginPage;
import core.MainPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import wrapper.FeedCardWrapper;

public class FeedTest extends TestBase {
    @Test
    public void createNewPost() {
        TestBot testBot = TestBot.bot2();
        logger.info("Trying to log in with credentials: {}", testBot);
        MainPage mainPage = new LoginPage().doLogin(testBot);
        logger.info("Logged in successfully");

        final StatusesPage statusesPage = mainPage.clickStatusesOnLeftColumn();
        logger.info("Statuses page was opened");

        statusesPage.clickOnCreatePost();
        final PostingFormBlock postingFormBlock = statusesPage.getPostingFormBlock();
        final String feedPostText = RandomStringUtils.randomAlphabetic(16);
        logger.info("Post text: {}", feedPostText);

        postingFormBlock.createFeedPost(feedPostText);
        logger.info("Post was created successfully");

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        Assert.assertEquals(feedPostText, feedCardWrapper.getFeedCardText());
    }
}
