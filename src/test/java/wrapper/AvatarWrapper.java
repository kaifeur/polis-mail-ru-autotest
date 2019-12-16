package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class AvatarWrapper {
    private final SelenideElement avatarElement;
    private final String ticoSlr = ".tico";

    public AvatarWrapper(final SelenideElement avatarElement) {
        this.avatarElement = avatarElement;
    }

    public String getAvatarId() {
        final String href = avatarElement.$("a[class=card_wrp]").getAttribute("href");
        return href.substring(href.lastIndexOf("/") + 1);
    }

    public String getAvatarHref() {
        return avatarElement.$("a[class=card_wrp]").getAttribute("href");
    }

    public void clickOnChangeAvatar() {
        avatarElement.shouldBe(Condition.visible).hover();
        $$(ticoSlr).find(Condition.exactText("Сменить фото")).click();
    }
}
