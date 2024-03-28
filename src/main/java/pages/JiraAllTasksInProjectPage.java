package pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraAllTasksInProjectPage {

    private final SelenideElement advancedSearch = $x("//div[@class='atlassian-autocomplete']/textarea[@aria-label='Расширенный запрос']").as("Поле для продвинутого поиска задач");
    private final SelenideElement searchButton = $x("//div[@class='search-options-container']/button[@original-title='Поиск задач']").as("Кнопка \"Поиск\"");
    private final SelenideElement searchLoading = $x("//div[@class='navigator-content']/div[@class='details-layout']/div[@class='loading']").as("Загрузка");

    @Step("Продвинутый поиск задач по JQL запросу")
    public void searchTask(String searchTaskName) {
        searchLoading.shouldNotHave(Condition.visible);
        advancedSearch.shouldHave(Condition.visible).clear();
        advancedSearch.shouldBe(Condition.empty);
        advancedSearch.shouldHave()
                .sendKeys(searchTaskName);
        advancedSearch.shouldHave(Condition.value(searchTaskName));
        searchButton.shouldHave(Condition.visible).click();
    }
}
