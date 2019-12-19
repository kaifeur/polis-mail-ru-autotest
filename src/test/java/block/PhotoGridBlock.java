package block;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import transformer.PhotoCardTransformer;
import wrapper.PhotoCardWrapper;

import java.util.List;

public class PhotoGridBlock {
    private final SelenideElement photoGridElm;
    private String photoUcardImgSlr = ".ucard-b_img";

    public PhotoGridBlock(final SelenideElement photoGridElm) {
        this.photoGridElm = photoGridElm;
    }

    public List<PhotoCardWrapper> getPhotosExcludeAvatar() {
        return PhotoCardTransformer.getInstance()
                .transform(photoGridElm.$$(photoUcardImgSlr).exclude(
                        Condition.attribute("class", "ucard-b_img  __selected"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)
                                .because("There must be at least one not avatar photo")));
    }
}
