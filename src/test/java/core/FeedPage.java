package core;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class FeedPage extends BasePage {
    public FeedPage() {
        super();
    }

    @Override
    protected void check() {
        $("div[data-l=\"t,navigation\"]").shouldBe(Condition.visible.because("Navbar should be visible!"));
        $("a[data-l=\"t,userStatuses\"]").shouldBe(Condition.visible)
                .shouldBe(Condition.enabled.because("We must be able to go to statuses page"));
    }
}
