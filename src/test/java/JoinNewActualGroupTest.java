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
        logger.info("Trying to log in with credentials: {}", testBot);
        MainPage mainPage = new LoginPage().doLogin(testBot);
        logger.info("Logged in successfully");

        final GroupsPage groupsPage = mainPage.clickGroupsOnLeftColumn();
        logger.info("Groups page was opened");

        final List<GroupCardWrapper> actualGroupWrappers = groupsPage.getActualGroupWrappers();
        logger.info("Groups card list size: {}", actualGroupWrappers.size());

        final GroupCardWrapper randomPopularGroup = actualGroupWrappers
                .get(ThreadLocalRandom.current().nextInt(actualGroupWrappers.size()));
        final String groupId = randomPopularGroup.getGroupId();
        logger.info("Chosen group id: {}", groupId);

        randomPopularGroup.joinToThisGroup();
        logger.info("Joined to the group {}", groupId);

        mainPage = BasePage.openMainPage();
        logger.info("Main page was opened");

        mainPage.clickGroupsOnLeftColumn();
        groupsPage.clickMyGroupsOnLeftColumn();
        logger.info("User groups was opened");

        final List<GroupCardWrapper> userGroupWrappers = groupsPage.getUserGroupWrappers();
        logger.info("User groups list size: {}", userGroupWrappers.size());

        userGroupWrappers.removeIf(g -> !groupId.equals(g.getGroupId()));
        Assert.assertFalse("There must be the group than has been chosen", userGroupWrappers.isEmpty());
    }

    @Override
    public void cleanUp() {
        logger.info("Cleaning up: let's leave all user groups");
        super.cleanUp();
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        MainPage mainPage = new LoginPage().doLogin(testBot);
        GroupsPage groupsPage = mainPage.clickGroupsOnLeftColumn();
        groupsPage.clickMyGroupsOnLeftColumn();

        final int groupCount = groupsPage.getUserGroupWrappers().size();
        if (groupCount == 0) {
            return;
        }

        for (int i = 0; i < groupCount; i++) {
            final GroupCardWrapper groupCardWrapper = groupsPage.getUserGroupWrappers().get(0);
            logger.info("Leaving group {}", groupCardWrapper.getGroupId());
            groupCardWrapper.clickOnGroup();
            GroupPage groupPage = new GroupPage();
            groupPage.leaveThisGroup();
            Selenide.back();
        }
        Selenide.closeWindow();
    }
}
