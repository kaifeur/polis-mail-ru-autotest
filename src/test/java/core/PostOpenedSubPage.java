package core;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class PostOpenedSubPage extends BasePage {

    private final String newCommentInputSlr = ".js-comments_add";
    private final String addCommentButtonSlr = ".comments_add-controls > button[data-l=\"t,submit\"]";
    private final String mediaStatusLayerSlr = "#hook_Block_MediaStatusLayerBody";

    public PostOpenedSubPage() {
        super();
    }

    @Override
    protected void check() {
        Selenide.$(mediaStatusLayerSlr).shouldBe(Condition.visible);
    }

    public void writeNewCommentForPost(final String commentText) {
        $(newCommentInputSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled.because("We must be able to write a comment"))
                .sendKeys(commentText);
        $(addCommentButtonSlr)
                .shouldBe(Condition.visible).shouldBe(Condition.enabled
                .because("We must be able to add written comment")).click();
    }
}
