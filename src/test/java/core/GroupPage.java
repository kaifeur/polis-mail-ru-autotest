package core;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class GroupPage extends BasePage {

    private String blockAltGroupMainMenuSlr = "#hook_Block_AltGroupMainMenu";
    private String joinDropdownMenuSlr = "div[data-l=\"t,join\"] > .dropdown";
    private String exitGroupArrow = ".ic_exit_arrow";

    public GroupPage() {
        super();
    }

    public void leaveThisGroup() {
        $(blockAltGroupMainMenuSlr).$(joinDropdownMenuSlr).shouldBe(Condition.visible).click();
        $(exitGroupArrow).parent().shouldBe(Condition.visible).click();
    }

    @Override
    protected void check() {
        $(blockAltGroupMainMenuSlr).shouldBe(Condition.visible);
    }
}
