import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Test;
import wrapper.AvatarWrapper;
import wrapper.PhotoCardWrapper;
import wrapper.PhotoGridWrapper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SwitchAvatarTest extends TestBase {
    @Test
    public void switchAvatar() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        final SelenideElement avatarElm = $(".entity-avatar");
        final AvatarWrapper avatarWrapper = new AvatarWrapper(avatarElm);
        final String oldHref = avatarWrapper.getAvatarAElm().getAttribute("href");
        final String oldAvatarId = avatarWrapper.getAvatarId();

        avatarElm.hover();
        $$(".tico").find(Condition.exactText("Сменить фото")).click();

        final PhotoGridWrapper photoGridWrapper = new PhotoGridWrapper($("#hook_Block_AvatarDialogV2Photos .photo-sc_grid"));

        final SelenideElement notAvatarPhotoElm = photoGridWrapper.getPhotos().exclude(
                Condition.attribute("class", "ucard-b_img  __selected"))
                .first();

        PhotoCardWrapper photoCardWrapper = new PhotoCardWrapper(notAvatarPhotoElm);
        final String newAvatarId = photoCardWrapper.getId();

        notAvatarPhotoElm.$(".photo-crop_cnt").click();
        $(".js-doCrop").click();

        Selenide.sleep(5000); //because server need some time to really update avatar
        new MainPage().clickProfileOnLeftColumn();

        avatarWrapper.getAvatarAElm().should(Condition.not(
                Condition.attribute("href", oldHref)));
    }
}
