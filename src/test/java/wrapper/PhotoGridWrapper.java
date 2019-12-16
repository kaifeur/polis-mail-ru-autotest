package wrapper;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import transformer.PhotoCardTransformer;

import java.util.List;

public class PhotoGridWrapper {
    private final SelenideElement photoGridElm;

    public PhotoGridWrapper(final SelenideElement photoGridElm) {
        this.photoGridElm = photoGridElm;
    }

    public List<PhotoCardWrapper> getPhotosExcludeAvatar() {
        return PhotoCardTransformer.getInstance()
                .transform(photoGridElm.$$(".ucard-b_img").exclude(
                        Condition.attribute("class", "ucard-b_img  __selected"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }
}
