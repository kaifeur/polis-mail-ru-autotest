package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import transformer.MusicTransformer;
import wrapper.MusicWrapper;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicSubPage extends BasePage {
    private final String trackSlr = "wm-track";
    private final String toolbarMusicLayerSlr = ".toolbar-music-layer .toolbar-layer";
    private String playerControlsPauseIconSlr = ".wm-player-controls_play > wm-icon[icon='pause']";

    public MusicSubPage() {
        super();
    }

    public List<MusicWrapper> getSongWrappers() {
        return MusicTransformer.getInstance().transform($$(trackSlr)
                .shouldBe(CollectionCondition.sizeGreaterThan(0)
                        .because("There must be at least one song")));
    }

    public void checkMusicPlayingState() {
        $(playerControlsPauseIconSlr).should(Condition.exist.because("Music must be playing at this moment"));
    }

    public String getPlayerDragValueAttribute() {
        return $("wm-player-drag").getAttribute("value");
    }

    @Override
    protected void check() {
        $(toolbarMusicLayerSlr).shouldBe(Condition.visible);
    }
}
