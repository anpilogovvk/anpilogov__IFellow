package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class JiraMainPage {

    private final SelenideElement searchResult = $x("//h1[contains(text(),'System Dashboard')]");
    private final SelenideElement searchButton = $x("//a[@title='Просмотр недавних проектов или списка всех проектов']");
    private final SelenideElement searchButtonProject = $x("//a[contains(text(),'Test (TEST)')]");
    private final SelenideElement searchCreateButton = $x("//li[@id='create-menu']/a[contains(text(),'Создать')]");
    private final SelenideElement searchSummaryField = $x("//div[@class='field-group']/input[@name='summary']");
    private final SelenideElement searchTextButton = $x("//div[@class='aui-navgroup-primary']/ul[@class='aui-nav']/li[@data-mode='source']/button[contains(text(),'Текст')]");
    private final SelenideElement searchTextArea = $x("//div[@id='description-wiki-edit']/textarea[@id='description']");
    private final SelenideElement searchFinalCreateButton = $x("//div[@class='buttons']/input[@id='create-issue-submit']");
    private final SelenideElement searchGoToTaskButton = $x("//div[@class='aui-message closeable aui-message-success aui-will-close']/a[@class='issue-created-key issue-link']");


    public void CreateTask(String insertSummary, String insertDescription) {
        searchSummaryField.shouldBe(Condition.visible)
                .sendKeys(insertSummary);
        searchTextButton.click();
        searchTextArea.shouldBe(Condition.visible)
                .sendKeys(insertDescription);
        searchFinalCreateButton.click();
        searchGoToTaskButton.click();
    }


    public String getFirstResultText() {
        return searchResult.getText();
    }

    public void getProjectsMenu() {
        searchButton.click();
    }

    public void getProject() {
        searchButtonProject.click();
    }

    public void getCreateForm() {
        searchCreateButton.click();
    }


}
