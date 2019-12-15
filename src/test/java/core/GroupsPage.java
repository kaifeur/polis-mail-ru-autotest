package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GroupsPage extends BasePage {
    private static final String ACTUAL_GROUP_BLOCK_SELECTOR = "div#hook_Block_PopularGroupsListBlock";
    private static final String USER_GROUP_BLOCK_SELECTOR = "div#hook_Block_DetailedUserGroupsListBlock";
    private static final String GROUP_CARD_SELECTOR = "div[data-l^=groupCard]";

    public GroupsPage() {
        super();
    }

    public ElementsCollection getUserGroupElementCollection() {
        return $(USER_GROUP_BLOCK_SELECTOR).$$(GROUP_CARD_SELECTOR);
    }

    public ElementsCollection getActualGroupElementCollection() {
        return $(ACTUAL_GROUP_BLOCK_SELECTOR).$$(GROUP_CARD_SELECTOR);
    }

    public void clickMyGroupsOnLeftColumn() {
        $("#hook_Block_MyGroupsNavBlock").$("a[hrefattrs$=\"User_MyGroupsNav_Header\"]").click();
    }

    @Override
    protected void check() {
        final SelenideElement actualGroupsListBlockElm = $(ACTUAL_GROUP_BLOCK_SELECTOR);
        actualGroupsListBlockElm.shouldBe(Condition.visible
                .because("Groups list must be visible"));

        actualGroupsListBlockElm.$("a[href^=\"/dk?cmd=PopularGroupsListBlock\"][class=\"filter_i __active\"]")
                .shouldHave(Condition.exactTextCaseSensitive("Актуально")
                        .because("The \"Actual\" section should be opened"));

        actualGroupsListBlockElm.$$(GROUP_CARD_SELECTOR)
                .shouldHave(CollectionCondition.sizeGreaterThan(0)
                        .because("There must be at least one group in the list"));
    }
}
