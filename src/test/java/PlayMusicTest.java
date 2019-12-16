import com.codeborne.selenide.Selenide;
import core.LoginPage;
import core.MainPage;
import core.MusicSubPage;
import core.TestBase;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import wrapper.MusicWrapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayMusicTest extends TestBase {
    @Test
    public void playMusic() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        MainPage mainPage = new MainPage();
        mainPage.clickMusicOnToolbar();
        MusicSubPage musicSubPage = new MusicSubPage();
        final List<MusicWrapper> musicWrappers = musicSubPage.getSongWrappers();
        final MusicWrapper musicWrapper = musicWrappers
                .get(ThreadLocalRandom.current().nextInt(musicWrappers.size()));
        musicWrapper.clickPlayButton();
        musicSubPage.checkMusicPlayingState();
        Assert.assertNotEquals(0, musicSubPage.getPlayerDragValueAttribute());
    }
}
