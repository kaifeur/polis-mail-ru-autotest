package core;

import block.PhotoGridBlock;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class ChangeAvatarSubPage extends BasePage {
    private final String blockAvatarDialogSlr = "#hook_Block_AvatarDialogV2";
    private final String doCropButtonSlr = ".js-doCrop";
    private String photoGrid = "#hook_Block_AvatarDialogV2Photos .photo-sc_grid";

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

    public PhotoGridBlock getPhotoGridBlock() {
        return new PhotoGridBlock($(photoGrid).shouldBe(Condition.visible));
    }
}
