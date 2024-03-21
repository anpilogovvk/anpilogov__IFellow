package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$x;

public class JiraIfellowPage{

    private final SelenideElement searchInputUsername = $x("//input[@name='os_username']").as("Поле для ввода username");

    private final SelenideElement searchInputPassword = $x("//input[@name='os_password']").as("Поле для ввода password");

    private final SelenideElement searchButton = $x("//input[@value='Вход']").as("Кнопка вход");

    @Step("Авторизация в Jira")
    public void loginIntoJira(String searchLogin, String searchPassword) {
        searchInputUsername.shouldBe(Condition.visible)
                .sendKeys(searchLogin);
        searchInputPassword.shouldBe(Condition.visible)
                .sendKeys(searchPassword);
        searchButton.click();
    }
}
