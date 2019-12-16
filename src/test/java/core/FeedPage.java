package core;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class FeedPage extends BasePage {

    private final String leftNavBarSlr = "div[data-l=\"t,navigation\"]";
    private final String userStatusesOnNavBarSlr = "a[data-l=\"t,userStatuses\"]";

    public FeedPage() {
        super();
    }

    @Override
    protected void check() {
        $(leftNavBarSlr).shouldBe(Condition.visible.because("Navbar should be visible!"));
        $(userStatusesOnNavBarSlr).shouldBe(Condition.visible)
                .shouldBe(Condition.enabled.because("We must be able to go to statuses page"));
    }
}
