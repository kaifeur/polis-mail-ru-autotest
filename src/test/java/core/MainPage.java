package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import wrapper.AvatarWrapper;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private final String userStatusesOnLeftColumnSlr = "a[data-l=\"t,userStatuses\"]";
    private final String groupsOnLeftColumnSlr = "a[data-l=\"t,userAltGroup\"]";
    private final String userPageOnLeftColumnSlr = "a[data-l=\"t,userPage\"]";
    private final String toolbarDiscussionsSlr = "#hook_ToolbarIconDiscussions_ToolbarDiscussions";
    private final String blockNavigationSlr = "#hook_Block_Navigation";
    private String toolbarNavSlr = "ul[class=toolbar_nav]";
    private String musicToolbarButtonSlr = "li[data-l=\"t,music\"] #music_toolbar_button";

    public MainPage() {
        super();
    }

    public void clickStatusesOnLeftColumn() {
        $(userStatusesOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
    }

    public void clickGroupsOnLeftColumn() {
        $(groupsOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
    }

    public void clickProfileOnLeftColumn() {
        $(userPageOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
    }

    public void clickMusicOnToolbar() {
        $(toolbarNavSlr).$(musicToolbarButtonSlr).shouldBe(Condition.visible).click();
    }

    public AvatarWrapper getAvatarWrapper() {
        final SelenideElement avatarElm = $(".entity-avatar").shouldBe(Condition.visible);
        return new AvatarWrapper(avatarElm);
    }

    public void openDiscussions() {
        $(toolbarDiscussionsSlr).shouldBe(Condition.visible).click();
    }

    @Override
    protected void check() {
        $(blockNavigationSlr).shouldBe(Condition.visible);
    }
}
