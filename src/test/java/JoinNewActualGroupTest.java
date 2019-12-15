import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import core.BasePage;
import core.GroupsPage;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import transformer.GroupCardTransformer;
import wrapper.GroupCardWrapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;

public class JoinNewActualGroupTest extends TestBase {
    @Test
    public void joinNewActualGroup() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        final MainPage mainPage = new MainPage();
        mainPage.clickGroupsOnLeftColumn();

        final GroupsPage groupsPage = new GroupsPage();
        final ElementsCollection actualGroupElementCollection = groupsPage.getActualGroupElementCollection();
        final GroupCardWrapper randomPopularGroup = GroupCardTransformer
                .getInstance().transform(actualGroupElementCollection)
                .get(ThreadLocalRandom.current().nextInt(actualGroupElementCollection.size()));

        final String groupId = randomPopularGroup.getGroupId();
        randomPopularGroup.getJoinGroupButton().click();

        BasePage.openMainPage();

        mainPage.clickGroupsOnLeftColumn();
        groupsPage.clickMyGroupsOnLeftColumn();

        final List<GroupCardWrapper> groupCardWrappers = GroupCardTransformer
                .getInstance().transform(groupsPage.getUserGroupElementCollection());

        groupCardWrappers.removeIf(g -> !groupId.equals(g.getGroupId()));
        Assert.assertFalse("There must be the group than has been chosen", groupCardWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        Selenide.open(MainPage.MAIN_PAGE_URL);
        MainPage mainPage = new MainPage();
        mainPage.clickGroupsOnLeftColumn();
        GroupsPage groupsPage = new GroupsPage();
        groupsPage.clickMyGroupsOnLeftColumn();

        final int groupCount = groupsPage.getUserGroupElementCollection().size();
        if (groupCount == 0) {
            return;
        }

        for (int i = 0; i < groupCount; i++) {
            new GroupCardWrapper(groupsPage.getUserGroupElementCollection().first()).clickOnGroup();
            $("#hook_Block_AltGroupMainMenu div[data-l=\"t,join\"] > .dropdown").click();
            $(".ic_exit_arrow").parent().click();
            Selenide.back();
        }
    }
}
