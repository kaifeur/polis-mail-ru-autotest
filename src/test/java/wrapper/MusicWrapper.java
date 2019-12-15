package wrapper;

import com.codeborne.selenide.SelenideElement;

public class MusicWrapper {
    private final SelenideElement musicElement;

    public MusicWrapper(final SelenideElement musicElement) {
        this.musicElement = musicElement;
    }

    public void clickPlayButton() {
        musicElement.hover();
        musicElement.$("wm-track-play-button").click();
    }
}
