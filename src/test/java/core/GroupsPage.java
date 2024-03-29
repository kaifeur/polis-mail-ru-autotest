package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import transformer.GroupCardTransformer;
import wrapper.GroupCardWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class GroupsPage extends BasePage {
    private final String actualGroupBlockSlr = "div#hook_Block_PopularGroupsListBlock";
    private final String userGroupBlockSlr = "div#hook_Block_DetailedUserGroupsListBlock";
    private final String groupCardSlr = "div[data-l^=groupCard]";
    private final String myGroupsNavBlockSlr = "#hook_Block_MyGroupsNavBlock";
    private final String myGroupsNavHeaderSlr = "a[hrefattrs$=\"User_MyGroupsNav_Header\"]";
    private final String groupsActiveFilterSlr = "a[href^=\"/dk?cmd=PopularGroupsListBlock\"][class=\"filter_i __active\"]";

    public GroupsPage() {
        super();
    }

    public List<GroupCardWrapper> getUserGroupWrappers() {
        $(userGroupBlockSlr).shouldBe(Condition.visible.because("You must open \"my groups\" before getting user groups"));
        return GroupCardTransformer.getInstance().transform(
                $(userGroupBlockSlr).$$(groupCardSlr)
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)
                                .because("There must be at least one user group")));
    }

    public List<GroupCardWrapper> getActualGroupWrappers() {
        return GroupCardTransformer.getInstance().transform(
                $(actualGroupBlockSlr).$$(groupCardSlr)
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)
                                .because("There must be at least one actual group")));
    }

    public GroupsPage clickMyGroupsOnLeftColumn() {
        $(myGroupsNavBlockSlr).$(myGroupsNavHeaderSlr).click();
        return this;
    }

    @Override
    protected void check() {
        final SelenideElement actualGroupsListBlockElm = $(actualGroupBlockSlr);
        actualGroupsListBlockElm.shouldBe(Condition.visible
                .because("Groups list must be visible"));

        actualGroupsListBlockElm.$(groupsActiveFilterSlr)
                .shouldHave(Condition.exactTextCaseSensitive("Актуально")
                        .because("The \"Actual\" section should be opened"));

        actualGroupsListBlockElm.$$(groupCardSlr)
                .shouldHave(CollectionCondition.sizeGreaterThan(0)
                        .because("There must be at least one group in the list"));
    }
}
