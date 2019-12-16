package wrapper;

import com.codeborne.selenide.SelenideElement;

public class MusicWrapper {
    private final SelenideElement musicElement;
    private final String trackPlayButtonSlr = "wm-track-play-button";

    public MusicWrapper(final SelenideElement musicElement) {
        this.musicElement = musicElement;
    }

    public void clickPlayButton() {
        musicElement.hover();
        musicElement.$(trackPlayButtonSlr).click();
    }
}
