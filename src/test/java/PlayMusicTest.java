import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import core.LoginPage;
import core.MainPage;
import core.MusicPage;
import core.TestBase;
import model.TestBot;
import org.junit.Test;
import transformer.MusicTransformer;
import wrapper.MusicWrapper;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.$;

public class PlayMusicTest extends TestBase {
    @Test
    public void playMusic() {
        TestBot testBot = TestBot.bot2();
        Selenide.open(BASE_URL);
        new LoginPage().doLogin(testBot);

        MainPage mainPage = new MainPage();
        $("ul[class=toolbar_nav] > li[data-l=\"t,music\"] #music_toolbar_button").click();
        MusicPage musicPage = new MusicPage();
        final ElementsCollection songElmCollection = musicPage.getSongElmCollection();
        final List<MusicWrapper> musicWrappers = MusicTransformer
                .getInstance().transform(songElmCollection);
        final MusicWrapper musicWrapper = musicWrappers
                .get(ThreadLocalRandom.current().nextInt(musicWrappers.size()));
        musicWrapper.clickPlayButton();
        $(".wm-player-controls_play > wm-icon[icon='pause']").should(Condition.exist);
        $("wm-player-drag").shouldHave(
                Condition.not(Condition.attribute("value", "0")));
    }
}
