package block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.ChangeAvatarSubPage;

import static com.codeborne.selenide.Selenide.$$;

public class AvatarBlock {
    private final SelenideElement avatarElement;
    private final String ticoSlr = ".tico";
    private final String avatarCardWrpSlr = "a[class=card_wrp]";

    public AvatarBlock(final SelenideElement avatarElement) {
        this.avatarElement = avatarElement;
    }

    public String getAvatarId() {
        final String href = avatarElement.$(avatarCardWrpSlr).getAttribute("href");
        return href.substring(href.lastIndexOf("/") + 1);
    }

    public SelenideElement getAvatarCardWrp() {
        return avatarElement.$(avatarCardWrpSlr);
    }

    public String getAvatarHref() {
        return avatarElement.$(avatarCardWrpSlr).getAttribute("href");
    }

    public ChangeAvatarSubPage clickOnChangeAvatar() {
        avatarElement.shouldBe(Condition.visible).hover();
        $$(ticoSlr).find(Condition.exactText("Сменить фото")).click();
        return new ChangeAvatarSubPage();
    }
}
