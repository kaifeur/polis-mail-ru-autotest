import com.codeborne.selenide.Selenide;
import core.BasePage;
import core.GroupPage;
import core.GroupsPage;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.GroupCardWrapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class JoinNewActualGroupTest extends TestBase {
    @Test
    public void joinNewActualGroup() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        final MainPage mainPage = new MainPage();
        mainPage.clickGroupsOnLeftColumn();

        final GroupsPage groupsPage = new GroupsPage();
        final List<GroupCardWrapper> actualGroupWrappers = groupsPage.getActualGroupWrappers();
        final GroupCardWrapper randomPopularGroup = actualGroupWrappers
                .get(ThreadLocalRandom.current().nextInt(actualGroupWrappers.size()));

        final String groupId = randomPopularGroup.getGroupId();
        randomPopularGroup.joinToThisGroup();

        BasePage.openMainPage();

        mainPage.clickGroupsOnLeftColumn();
        groupsPage.clickMyGroupsOnLeftColumn();

        final List<GroupCardWrapper> userGroupWrappers = groupsPage.getUserGroupWrappers();

        userGroupWrappers.removeIf(g -> !groupId.equals(g.getGroupId()));
        Assert.assertFalse("There must be the group than has been chosen", userGroupWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        super.cleanUp();
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);
        MainPage mainPage = new MainPage();
        mainPage.clickGroupsOnLeftColumn();
        GroupsPage groupsPage = new GroupsPage();
        groupsPage.clickMyGroupsOnLeftColumn();

        final int groupCount = groupsPage.getUserGroupWrappers().size();
        if (groupCount == 0) {
            return;
        }

        for (int i = 0; i < groupCount; i++) {
            groupsPage.getUserGroupWrappers().get(0).clickOnGroup();
            GroupPage groupPage = new GroupPage();
            groupPage.leaveThisGroup();
            Selenide.back();
        }
        Selenide.closeWindow();
    }
}
