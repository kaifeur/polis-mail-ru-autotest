package core;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {
    public MusicPage() {
        super();
    }

    public ElementsCollection getSongElmCollection() {
        $$("wm-track").shouldBe(CollectionCondition.sizeGreaterThan(0));
        return $$("wm-track");
    }

    @Override
    protected void check() {
        $(".toolbar-music-layer .toolbar-layer").shouldBe(Condition.visible);
    }
}
