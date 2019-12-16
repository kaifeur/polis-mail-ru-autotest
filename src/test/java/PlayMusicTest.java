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
        MainPage mainPage = new LoginPage().doLogin(testBot);
        MusicSubPage musicSubPage = mainPage.clickMusicOnToolbar();
        final List<MusicWrapper> musicWrappers = musicSubPage.getSongWrappers();
        final MusicWrapper musicWrapper = musicWrappers
                .get(ThreadLocalRandom.current().nextInt(musicWrappers.size()));
        musicWrapper.clickPlayButton();
        musicSubPage.checkMusicPlayingState();
        Assert.assertNotEquals(0, musicSubPage.getPlayerDragValueAttribute());
    }
}
