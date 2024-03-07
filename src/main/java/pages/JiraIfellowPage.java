package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraIfellowPage {

    private final SelenideElement searchInputUsername = $x("//input[@name='os_username']");

    private final SelenideElement searchInputPassword = $x("//input[@name='os_password']");

    private final SelenideElement searchButton = $x("//input[@value='Вход']");

    public void loginIntoJira(String searchLogin, String searchPassword) {
        searchInputUsername.shouldBe(Condition.visible)
                .sendKeys(searchLogin);
        searchInputPassword.shouldBe(Condition.visible)
                .sendKeys(searchPassword);
        searchButton.click();
    }
}
