package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import wrapper.PhotoGridWrapper;

import static com.codeborne.selenide.Selenide.$;

public class ChangeAvatarSubPage extends BasePage {
    private final String blockAvatarDialogSlr = "#hook_Block_AvatarDialogV2";
    private final String doCropButtonSlr = ".js-doCrop";

    public ChangeAvatarSubPage() {
        super();
    }

    @Override
    protected void check() {
        Selenide.$(blockAvatarDialogSlr).shouldBe(Condition.visible);
    }

    public void acceptCropAvatar() {
        $(doCropButtonSlr).shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
    }

    public PhotoGridWrapper getPhotoGridWrapper() {
        return new PhotoGridWrapper($("#hook_Block_AvatarDialogV2Photos .photo-sc_grid").shouldBe(Condition.visible));
    }
}
