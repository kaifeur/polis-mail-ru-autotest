import block.AvatarBlock;
import block.PhotoGridBlock;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import core.ChangeAvatarSubPage;
import core.LoginPage;
import core.MainPage;
import core.TestBase;
import model.TestBot;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import wrapper.PhotoCardWrapper;

public class SwitchAvatarTest extends TestBase {
    private static Condition attributeWithRefresh(final String propName, final String propValue) {
        return new Condition("attribute") {
            @Override
            public boolean apply(Driver driver, WebElement element) {
                if (propValue.equalsIgnoreCase(element.getAttribute(propName))) {
                    return true;
                } else {
                    Selenide.refresh();
                    return false;
                }
            }

            @Override
            public String actualValue(Driver driver, WebElement element) {
                return element.getAttribute(propName);
            }
        };
    }

    @Test
    public void switchAvatar() {
        TestBot testBot = TestBot.bot2();
        MainPage mainPage = new LoginPage().doLogin(testBot);
        final AvatarBlock avatarBlock = mainPage.getAvatarWrapper();

        ChangeAvatarSubPage changeAvatarSubPage = avatarBlock.clickOnChangeAvatar();

        final PhotoGridBlock photoGridBlock = changeAvatarSubPage.getPhotoGridBlock();
        PhotoCardWrapper newAvatarPhotoWrapper = photoGridBlock.getPhotosExcludeAvatar().get(0);

        newAvatarPhotoWrapper.clickOnThisPhoto();
        changeAvatarSubPage.acceptCropAvatar();

        avatarBlock.getAvatarCardWrp().waitUntil(
                attributeWithRefresh("href", avatarBlock.getAvatarHref()), Configuration.timeout); //move to avatar block

//        Assert.assertNotEquals(oldHref, avatarWrapper.getAvatarHref());
    }
}
