package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraMainPage{

    private final SelenideElement searchResult = $x("//h1[contains(text(),'System Dashboard')]").as("Надпись System Dashboard");
    private final SelenideElement searchButton = $x("//a[@title='Просмотр недавних проектов или списка всех проектов']").as("Просмотр недавних проектов или списка всех проектов");
    private final SelenideElement searchButtonProject = $x("//a[contains(text(),'Test (TEST)')]").as("Кнопка для перехода в проект Test");
    private final SelenideElement searchCreateButton = $x("//li[@id='create-menu']/a[contains(text(),'Создать')]").as("Кнопка \"Создать задачу\"");
    private final SelenideElement searchSummaryField = $x("//div[@class='field-group']/input[@name='summary']").as("Текстовое поле \"Тема\"");
    private final SelenideElement searchTextButton = $x("//div[@class='aui-navgroup-primary']/ul[@class='aui-nav']/li[@data-mode='source']/button[contains(text(),'Текст')]").as("Кнопка \"Текст\"");;
    private final SelenideElement searchTextArea = $x("//div[@id='description-wiki-edit']/textarea[@id='description']").as("Текстовое поле \"Описание\"");
    private final SelenideElement searchFinalCreateButton = $x("//div[@class='buttons']/input[@id='create-issue-submit']").as("Кнопка \"Создать\"");
    private final SelenideElement searchGoToTaskButton = $x("//div[@class='aui-message closeable aui-message-success aui-will-close']/a[@class='issue-created-key issue-link']").as("Ссылка на новую задачу");

    @Step("Создать новую задачу")
    public void CreateTask(String insertSummary, String insertDescription) {
        searchSummaryField.shouldBe(Condition.visible)
                .sendKeys(insertSummary);
        searchTextButton.click();
        searchTextArea.shouldBe(Condition.visible)
                .sendKeys(insertDescription);
        searchFinalCreateButton.click();
        searchGoToTaskButton.click();
    }

    @Step("После авторизации найти найти надпись System Dashboard")
    public String getFirstResultText() {
        return searchResult.getText();
    }

    @Step("Просмотр всех проектов")
    public void getProjectsMenu() {
        searchButton.click();
    }
    @Step("Перейти в проект Test")
    public void getProject() {
        searchButtonProject.click();
    }
    @Step("Нажать кнопку \"Создать задачу\"")
    public void getCreateForm() {
        searchCreateButton.click();
    }
}
