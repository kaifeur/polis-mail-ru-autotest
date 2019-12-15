package wrapper;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class PhotoGridWrapper {
    private final SelenideElement photoGridElm;

    public PhotoGridWrapper(final SelenideElement photoGridElm) {
        this.photoGridElm = photoGridElm;
    }

    public ElementsCollection getPhotos() {
        return photoGridElm.$$(".ucard-b_img");
    }
}
