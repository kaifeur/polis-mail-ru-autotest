package wrapper;

import com.codeborne.selenide.SelenideElement;

public class AvatarWrapper {
    private final SelenideElement avatarElement;

    public AvatarWrapper(final SelenideElement avatarElement) {
        this.avatarElement = avatarElement;
    }

    public String getAvatarId() {
        final String href = avatarElement.$("a[class=card_wrp]").getAttribute("href");
        return href.substring(href.lastIndexOf("/") + 1);
    }

    public SelenideElement getAvatarAElm() {
        return avatarElement.$("a[class=card_wrp]");
    }
}
