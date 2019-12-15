package core;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    public static final String MAIN_PAGE_URL = "https://ok.ru/feed/";

    public MainPage() {
        super();
    }

    public void clickStatusesOnLeftColumn() {
        $("a[data-l=\"t,userStatuses\"]").click();
    }

    public void clickGroupsOnLeftColumn() {
        $("a[data-l=\"t,userAltGroup\"]").click();
    }

    public void clickProfileOnLeftColumn() {
        $("a[data-l=\"t,userPage\"]").click();
    }

    public void openDiscussions() {
        $("#hook_ToolbarIconDiscussions_ToolbarDiscussions").shouldBe(Condition.visible).click();
    }

    @Override
    protected void check() {
        $("#hook_Block_Navigation").shouldBe(Condition.visible);
    }
}
