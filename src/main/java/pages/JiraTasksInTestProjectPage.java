package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class JiraTasksInTestProjectPage {

    private final SelenideElement searchProjectName = $x("//div[@class='aui-page-header-main']/ol/li/a");
    private final SelenideElement countTasks = $x("//div[@class='showing']/span");
    private final SelenideElement goToViewAllTasks = $x("//div[@class='aui-item']/div/a[contains(text(),'Посмотреть все задачи и фильтры')]");

    public String getProjectNameResultText(){
        return searchProjectName.getText();
    }

    public int setCountTasks() {
      String countTask = countTasks.getText();
      String[] splitString = countTask.split(" ");
      int maxTasksBefore = Integer.parseInt(splitString[2]);
      return maxTasksBefore;
    }

    public void viewAllTasks(){
        goToViewAllTasks.click();
    }





}
