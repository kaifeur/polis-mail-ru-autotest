package block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import condition.NotAttributeWithValueAndRefresh;
import core.ChangeAvatarSubPage;

import static com.codeborne.selenide.Selenide.$$;

public class AvatarBlock {
    private final SelenideElement avatarElement;
    private final String ticoSlr = ".tico";
    private final String avatarCardWrpSlr = "a[class=card_wrp]";

    public AvatarBlock(final SelenideElement avatarElement) {
        this.avatarElement = avatarElement;
    }

    public String getAvatarHref() {
        return avatarElement.$(avatarCardWrpSlr).getAttribute("href");
    }

    public ChangeAvatarSubPage clickOnChangeAvatar() {
        avatarElement.shouldBe(Condition.visible.because("Avatar block needed")).hover();
        $$(ticoSlr).find(Condition.exactText("Сменить фото")).click();
        return new ChangeAvatarSubPage();
    }

    public void checkNotEqualsAvatarHref(final String href) {
        avatarElement.$(avatarCardWrpSlr).shouldHave(
                new NotAttributeWithValueAndRefresh("href", href)
                        .because("Avatar href must be changed"));
    }
}
