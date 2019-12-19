package condition;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

public class NotAttributeWithValueAndRefresh extends Condition {
    private final String attributeName;
    private final String expectedAttributeValue;

    public NotAttributeWithValueAndRefresh(String attributeName, String expectedAttributeValue) {
        super("attribute");
        this.attributeName = attributeName;
        this.expectedAttributeValue = expectedAttributeValue;
    }

    @Override
    public boolean apply(Driver driver, WebElement element) {
        if (!expectedAttributeValue.equalsIgnoreCase(element.getAttribute(attributeName))) {
            return true;
        } else {
            Selenide.refresh();
            return false;
        }
    }

    @Override
    public String actualValue(Driver driver, WebElement element) {
        return element.getAttribute(attributeName);
    }
}
