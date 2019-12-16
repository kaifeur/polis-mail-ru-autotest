package wrapper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class GroupCardWrapper {
    private final SelenideElement groupCardElm;
    private String joinGroupButtonSlr = "form[action*=JoinGroupWidget] > button[data-l=\"t,join\"]";
    private String groupLinkWithIdSlr = "a[hrefattrs*=\"groupId=\"]";
    private String visitGroupSlr = "a[data-l=\"t,visit\"]";

    public GroupCardWrapper(final SelenideElement groupCardElm) {
        this.groupCardElm = groupCardElm;
    }

    public String getGroupId() {
        final String hrefattrs = groupCardElm.$(groupLinkWithIdSlr).getAttribute("hrefattrs");
        final String substring = hrefattrs.substring(hrefattrs.indexOf("groupId=") + 1);
        return substring.substring(0, substring.indexOf('&'));
    }

    public void joinToThisGroup() {
        groupCardElm.$(joinGroupButtonSlr)
                .shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
    }

    public void clickOnGroup() {
        groupCardElm.$(visitGroupSlr).click();
    }
}
