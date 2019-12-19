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
        logger.info("Trying to log in with credentials: {}", testBot);
        MainPage mainPage = new LoginPage().doLogin(testBot);
        logger.info("Logged in successfully");

        MusicSubPage musicSubPage = mainPage.clickMusicOnToolbar();
        logger.info("Music was opened");

        final List<MusicWrapper> musicWrappers = musicSubPage.getSongWrappers();
        logger.info("Song list size: {}", musicWrappers.size());

        final MusicWrapper musicWrapper = musicWrappers
                .get(ThreadLocalRandom.current().nextInt(musicWrappers.size()));

        musicWrapper.clickPlayButton();
        logger.info("Clicked on play");

        musicSubPage.checkMusicPlayingState();
        logger.info("Play icon changed to a pause icon");

        Assert.assertNotEquals(0, musicSubPage.getPlayerDragValueAttribute());
    }
}
