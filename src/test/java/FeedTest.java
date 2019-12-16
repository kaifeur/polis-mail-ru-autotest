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
        MainPage mainPage = new LoginPage().doLogin(testBot);

        final StatusesPage statusesPage = mainPage.clickStatusesOnLeftColumn();
        statusesPage.clickOnCreatePost();
        final PostingFormBlock postingFormBlock = statusesPage.getPostingFormBlock();

        final String feedPostText = RandomStringUtils.randomAlphabetic(16);
        postingFormBlock.createFeedPost(feedPostText);

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        Assert.assertEquals(feedPostText, feedCardWrapper.getFeedCardText());
    }
}
