package wrapper;

import com.codeborne.selenide.SelenideElement;

public class GroupCardWrapper {
    private final SelenideElement groupCardElm;

    public GroupCardWrapper(final SelenideElement groupCardElm) {
        this.groupCardElm = groupCardElm;
    }

    public String getGroupId() {
        final String hrefattrs = groupCardElm.$("a[hrefattrs*=\"groupId=\"]").getAttribute("hrefattrs");
        final String substring = hrefattrs.substring(hrefattrs.indexOf("groupId=") + 1);
        return substring.substring(0, substring.indexOf('&'));
    }

    public SelenideElement getJoinGroupButton() {
        return groupCardElm.$("form[action*=JoinGroupWidget] > button[data-l=\"t,join\"]");
    }

    public void clickOnGroup() {
        groupCardElm.$("a[data-l=\"t,visit\"]").click();
    }
}
