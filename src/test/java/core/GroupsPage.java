package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GroupsPage extends BasePage {
    private final String actualGroupBlockSlr = "div#hook_Block_PopularGroupsListBlock";
    private final String userGroupBlockSlr = "div#hook_Block_DetailedUserGroupsListBlock";
    private final String groupCardSlr = "div[data-l^=groupCard]";

    public GroupsPage() {
        super();
    }

    public ElementsCollection getUserGroupElementCollection() {
        return $(userGroupBlockSlr).$$(groupCardSlr);
    }

    public ElementsCollection getActualGroupElementCollection() {
        return $(actualGroupBlockSlr).$$(groupCardSlr);
    }

    public void clickMyGroupsOnLeftColumn() {
        $("#hook_Block_MyGroupsNavBlock").$("a[hrefattrs$=\"User_MyGroupsNav_Header\"]").click();
    }

    @Override
    protected void check() {
        final SelenideElement actualGroupsListBlockElm = $(actualGroupBlockSlr);
        actualGroupsListBlockElm.shouldBe(Condition.visible
                .because("Groups list must be visible"));

        actualGroupsListBlockElm.$("a[href^=\"/dk?cmd=PopularGroupsListBlock\"][class=\"filter_i __active\"]")
                .shouldHave(Condition.exactTextCaseSensitive("Актуально")
                        .because("The \"Actual\" section should be opened"));

        actualGroupsListBlockElm.$$(groupCardSlr)
                .shouldHave(CollectionCondition.sizeGreaterThan(0)
                        .because("There must be at least one group in the list"));
    }
}
