package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PhotoCardWrapper {
    private final SelenideElement photoCardElement;
    private final String photoUcardImgSlr = ".ucard-b_img";
    private final String avatarListBlockWithLinkSlr = "a[data-l$='AvatarListBlock_ChangeAvatar']";
    private final String photoAvatarToCrop = ".photo-crop_cnt";

    public PhotoCardWrapper(final SelenideElement photoCardElement) {
        this.photoCardElement = photoCardElement;
    }

    public boolean isSelected() {
        return photoCardElement.$(photoUcardImgSlr).has(Condition.attribute("class", ".ucard-b_img __selected"));
    }

    public String getId() {
        final String href = photoCardElement.$(avatarListBlockWithLinkSlr)
                .shouldHave(Condition.attribute("href").because("We must know ID of this photo"))
                .getAttribute("href");
        final String idWithEnd = href.substring(href.indexOf("photoId=") + 8);
        return idWithEnd.substring(0, idWithEnd.indexOf('&'));
    }

    public void clickOnThisPhoto() {
        photoCardElement.$(photoAvatarToCrop).shouldBe(Condition.visible).click();
    }
}
