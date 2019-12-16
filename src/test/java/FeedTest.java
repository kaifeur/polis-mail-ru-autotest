import com.codeborne.selenide.Selenide;
import core.LoginPage;
import core.MainPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.FeedCardWrapper;
import wrapper.PostingFormWrapper;

import java.util.UUID;

public class FeedTest extends TestBase {
    @Test
    public void createNewPost() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);
        new MainPage().clickStatusesOnLeftColumn();

        final StatusesPage statusesPage = new StatusesPage();
        statusesPage.clickOnCreatePost();
        final PostingFormWrapper postingFormWrapper = statusesPage.getPostingFormWrapper();

        final String feedPostText = UUID.randomUUID().toString();
        postingFormWrapper.createFeedPost(feedPostText);

        FeedCardWrapper feedCardWrapper = statusesPage.getFirstFeedCard();
        Assert.assertEquals(feedPostText, feedCardWrapper.getFeedCardText());
    }
}
