package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {
    private final String trackSlr = "wm-track";
    private final String toolbarMusicLayerSlr = ".toolbar-music-layer .toolbar-layer";

    public MusicPage() {
        super();
    }

    public ElementsCollection getSongElmCollection() {
        $$(trackSlr).shouldBe(CollectionCondition.sizeGreaterThan(0));
        return $$(trackSlr);
    }

    @Override
    protected void check() {
        $(toolbarMusicLayerSlr).shouldBe(Condition.visible);
    }
}
