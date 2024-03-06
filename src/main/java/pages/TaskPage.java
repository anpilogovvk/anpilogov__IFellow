package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
public class TaskPage{

    private final SelenideElement searchFixVersionNumber = $x("//div[@class='wrap']/span[@id='fixfor-val']/span/a[contains(text(),'Version 2.0')]");
    private final SelenideElement searchStatus = $x("//li[@class='item item-right']/div[@class='wrap']/span[@id='status-val']/span");
    private final SelenideElement searchInProgressStatusButton = $x("//div[@id='opsbar-opsbar-transitions']/a[@id='action_id_21']");
    private final SelenideElement searchOtherStatusBarButton = $x("//div[@id='opsbar-opsbar-transitions']/a[@id='opsbar-transitions_more']");
    private final SelenideElement searchDoneStatusButton = $x("//aui-item-link[@class='issueaction-workflow-transition']/a[@role='menuitem']");

    public String getFixVersionNumber(){
        return searchFixVersionNumber.getText();
    }

    public String getStatus(){
        return searchStatus.shouldBe(Condition.visible).getText();
    }

    public void setStatusInProgress(){
        searchInProgressStatusButton.click();
    }

    public void openOtherStatusBar(){
        searchOtherStatusBarButton.click();
    }

    public void setStatusDone(){
        searchDoneStatusButton.click();
        Selenide.sleep(500);
    }

}
