package pages;

import com.codeborne.selenide.Condition;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
public class TaskPage{

    private final SelenideElement searchFixVersionNumber = $x("//div[@class='wrap']/span[@id='fixfor-val']/span/a[contains(text(),'Version 2.0')]").as("Поле \"Fix version\" в задаче");
    private final SelenideElement searchStatus = $x("//li[@class='item item-right']/div[@class='wrap']/span[@id='status-val']/span").as("Поле \"Статус\" в задаче");
    private final SelenideElement searchInProgressStatusButton = $x("//div[@id='opsbar-opsbar-transitions']/a[@id='action_id_21']").as("Кнопка смены статуса \"В РАБОТЕ\"");
    private final SelenideElement searchOtherStatusBarButton = $x("//div[@id='opsbar-opsbar-transitions']/a[@id='opsbar-transitions_more']").as("Кнопка \"Бизнес-процесс\"");;
    private final SelenideElement searchDoneStatusButton = $x("//aui-item-link[@class='issueaction-workflow-transition']/a[@role='menuitem']").as("Кнопка смены статуса \"Выполнено\"");;
    private final SelenideElement searchInProgressStatus = $x("//li[@class='item item-right']/div[@class='wrap']/span[@id='status-val']/span[contains(text(),'В работе')]").as("Поле Статус\"В РАБОТЕ\"");
    private final SelenideElement searchLoading = $x("//div[@class='navigator-content']/div[@class='details-layout']/div[@class='loading']").as("Загрузка");

    @Step("Записать значения поля \"Fix version\"")
    public String getFixVersionNumber(){
        searchLoading.shouldNotHave(Condition.visible);
        return searchFixVersionNumber.getText();
    }

    @Step("Записать статус задачи")
    public String getStatus(){
        searchLoading.shouldNotHave(Condition.visible);
        searchInProgressStatus.shouldNotBe();
        return searchStatus.shouldNotBe().getText();
    }

    @Step("Изменить статус задачи на \"В РАБОТЕ\"")
    public void setStatusInProgress(){
        searchLoading.shouldNotHave(Condition.visible);
        searchInProgressStatusButton.click();
    }

    @Step("Открыть выпадашку \"Бизнес процесс\"")

    public void openOtherStatusBar() {
        searchLoading.shouldNotHave(Condition.visible);
        searchOtherStatusBarButton.shouldBe().click();
    }

    @Step("Изменить статус задачи на \"ГОТОВО\"")
    public void setStatusDone(){
        searchDoneStatusButton.shouldBe().click();
        searchOtherStatusBarButton.shouldNotHave(Condition.visible);
    }
}
