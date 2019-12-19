import block.AvatarBlock;
import block.PhotoGridBlock;
import com.codeborne.selenide.Selenide;
import core.ChangeAvatarSubPage;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.PhotoCardWrapper;

public class SwitchAvatarTest extends TestBase {
    @Test
    public void switchAvatar() {
        TestBot testBot = TestBot.bot2();
        logger.info("Trying to log in with credentials: {}", testBot);
        MainPage mainPage = new LoginPage().doLogin(testBot);
        logger.info("Logged in successfully");

        AvatarBlock avatarBlock = mainPage.getAvatarWrapper();
        final String oldHref = avatarBlock.getAvatarHref();
        logger.info("Old avatar href: {}", oldHref);

        final ChangeAvatarSubPage changeAvatarSubPage = avatarBlock.clickOnChangeAvatar();

        final PhotoGridBlock photoGridBlock = changeAvatarSubPage.getPhotoGridBlock();
        logger.info("Photo grid size: {}", photoGridBlock.getPhotosExcludeAvatar().size());

        final PhotoCardWrapper newAvatarPhotoWrapper = photoGridBlock.getPhotosExcludeAvatar().get(0);
        logger.info("Chosen avatar id: {}", newAvatarPhotoWrapper.getId());

        newAvatarPhotoWrapper.clickOnThisPhoto();
        changeAvatarSubPage.acceptCropAvatar();

        logger.info("Avatar was changed, going to profile page");
        mainPage.clickProfileOnLeftColumn();

        logger.info("New avatar href before refresh: {}", avatarBlock.getAvatarHref());

        avatarBlock.checkNotEqualsAvatarHref(oldHref);
        logger.info("New avatar href after a potential refresh: {}", avatarBlock.getAvatarHref());

        mainPage = new MainPage();
        avatarBlock = mainPage.getAvatarWrapper();
        Selenide.refresh();
        logger.info("Avatar href before assert: {}", avatarBlock.getAvatarHref());
        Assert.assertNotEquals(oldHref, avatarBlock.getAvatarHref());
    }
}
