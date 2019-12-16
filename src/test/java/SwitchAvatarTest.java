import com.codeborne.selenide.Selenide;
import core.ChangeAvatarSubPage;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.AvatarWrapper;
import wrapper.PhotoCardWrapper;
import wrapper.PhotoGridWrapper;

public class SwitchAvatarTest extends TestBase {
    @Test
    public void switchAvatar() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        MainPage mainPage = new MainPage();
        final AvatarWrapper avatarWrapper = mainPage.getAvatarWrapper();
        final String oldHref = avatarWrapper.getAvatarHref();

        avatarWrapper.clickOnChangeAvatar();
        ChangeAvatarSubPage changeAvatarSubPage = new ChangeAvatarSubPage();

        final PhotoGridWrapper photoGridWrapper = changeAvatarSubPage.getPhotoGridWrapper();
        PhotoCardWrapper newAvatarPhotoWrapper = photoGridWrapper.getPhotosExcludeAvatar().get(0);

        newAvatarPhotoWrapper.clickOnThisPhoto();
        changeAvatarSubPage.acceptCropAvatar();

        Selenide.sleep(5000);
        Selenide.refresh();//because server need some time to really update avatar

        Assert.assertNotEquals(oldHref, avatarWrapper.getAvatarHref());
    }
}
