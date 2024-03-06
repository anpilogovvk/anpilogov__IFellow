package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraAllTasksInProjectPage {

    private final SelenideElement advancedSearch = $x("//div[@class='atlassian-autocomplete']/textarea[@aria-label='Расширенный запрос']");
    private final SelenideElement searchButton = $x("//div[@class='search-options-container']/button[@original-title='Поиск задач']");
    private final SelenideElement numberTaskButton= $x("//div[@class='issue-content-container']/span[@class='issue-link-key']");
    private final SelenideElement searchExpertSearchButton= $x("    //div[@class='mode-switcher']/a[contains(text(),'Продвинутый')]");
    private final SelenideElement searchExportButton= $x("//div[@id='opsbar-jira.issue.tools']/a[@original-title='Экспортировать этот запрос в другой формат']/span[contains(text(),'Экспорт')]");

    public void searchTask(String searchTaskName){
        Selenide.sleep(500);
        advancedSearch.clear();
        advancedSearch.shouldBe(Condition.visible)
                .sendKeys(searchTaskName);
        searchExportButton.shouldHave(Condition.visible);
        searchButton.pressEnter();
    }

    public void clickExpertSearch(){
        searchExpertSearchButton.click();

    }

    public void clickTask(){
        searchExportButton.shouldHave(Condition.visible);
        Selenide.sleep(500);
        numberTaskButton.click();
    }


}
