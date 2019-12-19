package core;

import block.AvatarBlock;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {
    private final String userStatusesOnLeftColumnSlr = "a[data-l=\"t,userStatuses\"]";
    private final String groupsOnLeftColumnSlr = "a[data-l=\"t,userAltGroup\"]";
    private final String userPageOnLeftColumnSlr = "a[data-l=\"t,userPage\"]";
    private final String toolbarDiscussionsSlr = "#hook_ToolbarIconDiscussions_ToolbarDiscussions";
    private final String blockNavigationSlr = "#hook_Block_Navigation";
    private String toolbarNavSlr = "ul[class=toolbar_nav]";
    private String musicToolbarButtonSlr = "li[data-l=\"t,music\"] #music_toolbar_button";
    private String entityAvatarSlr = ".entity-avatar";

    public MainPage() {
        super();
    }

    public StatusesPage clickStatusesOnLeftColumn() {
        $(userStatusesOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
        return new StatusesPage();
    }

    public GroupsPage clickGroupsOnLeftColumn() {
        $(groupsOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
        return new GroupsPage();
    }

    public void clickProfileOnLeftColumn() {
        $(userPageOnLeftColumnSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled).click();
    }

    public MusicSubPage clickMusicOnToolbar() {
        $(toolbarNavSlr).$(musicToolbarButtonSlr).shouldBe(Condition.visible).click();
        return new MusicSubPage();
    }

    public AvatarBlock getAvatarWrapper() {
        final SelenideElement avatarElm = $(entityAvatarSlr).shouldBe(Condition.visible
                .because("Avatar block must be visible"));
        return new AvatarBlock(avatarElm);
    }

    public DiscussionsPage openDiscussions() {
        $(toolbarDiscussionsSlr).shouldBe(Condition.visible).click();
        return new DiscussionsPage();
    }

    @Override
    protected void check() {
        $(blockNavigationSlr).shouldBe(Condition.visible);
    }
}
