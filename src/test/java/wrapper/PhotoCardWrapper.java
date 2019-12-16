package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PhotoCardWrapper {
    private final SelenideElement photoCardElement;

    public PhotoCardWrapper(final SelenideElement photoCardElement) {
        this.photoCardElement = photoCardElement;
    }

    public boolean isSelected() {
        return photoCardElement.$(".ucard-b_img").has(Condition.attribute("class", ".ucard-b_img __selected"));
    }

    public String getId() {
        final String href = photoCardElement.$("a[data-l$='AvatarListBlock_ChangeAvatar']").getAttribute("href");
        final String idWithEnd = href.substring(href.indexOf("photoId=") + 8);
        return idWithEnd.substring(0, idWithEnd.indexOf('&'));
    }

    public void clickOnThisPhoto() {
        photoCardElement.$(".photo-crop_cnt").shouldBe(Condition.visible).click();
    }
}
