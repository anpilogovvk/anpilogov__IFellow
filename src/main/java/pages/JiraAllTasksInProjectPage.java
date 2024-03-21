package pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.$x;

public class JiraAllTasksInProjectPage {

    private final SelenideElement advancedSearch = $x("//div[@class='atlassian-autocomplete']/textarea[@aria-label='Расширенный запрос']").as("Поле для продвинутого поиска задач");
    private final SelenideElement searchButton = $x("//div[@class='search-options-container']/button[@original-title='Поиск задач']").as("Кнопка \"Поиск\"");
    private final SelenideElement numberTaskButton= $x("//div[@class='issue-content-container']/span[@class='issue-link-key']").as("Номер задачи");
    private final SelenideElement searchExpertSearchButton= $x("    //div[@class='mode-switcher']/a[contains(text(),'Продвинутый')]").as("Кнопка \"Продвинутый поиск\"");
    private final SelenideElement searchExportButton= $x("//div[@id='opsbar-jira.issue.tools']/a[@original-title='Экспортировать этот запрос в другой формат']/span[contains(text(),'Экспорт')]").as("Кнопка \"Экспорт\"");
    private final SelenideElement searchLoading = $x("//div[@class='navigator-content']/div[@class='details-layout']/div[@class='loading']").as("Загрузка после поиска");

    @Step("Продвинутый поиск задач по JQL запросу")
    public void searchTask(String searchTaskName){
        advancedSearch.clear();
        advancedSearch.shouldBe(Condition.visible)
                .sendKeys(searchTaskName);
        searchExportButton.shouldHave();
        searchButton.shouldHave(Condition.visible).click();
        searchLoading.shouldNotHave(Condition.visible);
    }

    @Step("Нажать кнопку \"Поиск\" для поиска задач по фильтру")
    public void clickExpertSearch(){
        searchExpertSearchButton.click();
    }

    @Step("Перейти в одну из отфильтрованных задач")
    public void clickTask(){
        searchExportButton.shouldHave(Condition.visible);
        numberTaskButton.click();
    }
}
