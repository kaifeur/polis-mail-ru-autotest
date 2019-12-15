import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import core.LoginPage;
import core.MainPage;
import core.StatusesPage;
import core.TestBase;
import model.TestBot;
import org.junit.Test;
import wrapper.FeedCardWrapper;
import wrapper.PostingFormWrapper;

public class FeedTest extends TestBase {
    @Test
    public void createNewPost() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);
        new MainPage().clickStatusesOnLeftColumn();

        final StatusesPage statusesPage = new StatusesPage();
        statusesPage.clickOnCreatePost();
        PostingFormWrapper postingFormWrapper = new PostingFormWrapper(statusesPage.getPostingFormElm());
        final String feedPostText = postingFormWrapper.createFeedPost();
        statusesPage.getPostingFormElm().shouldNotBe(Condition.exist);

        final ElementsCollection feedCards = statusesPage.getFeedCards();
        feedCards
                .shouldBe(CollectionCondition.sizeGreaterThan(0));
        FeedCardWrapper feedCardWrapper = new FeedCardWrapper(feedCards.first());
        feedCardWrapper.getFeedCardTextElement().should(Condition.exactTextCaseSensitive(feedPostText));
    }
}
