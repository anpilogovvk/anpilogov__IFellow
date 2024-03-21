package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class JiraTasksInTestProjectPage {

    private final SelenideElement searchProjectName = $x("//div[@class='aui-page-header-main']/ol/li/a").as("Имя проекта");
    private final SelenideElement countTasks = $x("//div[@class='showing']/span").as("Количество задач");
    private final SelenideElement goToViewAllTasks = $x("//div[@class='aui-item']/div/a[contains(text(),'Посмотреть все задачи и фильтры')]").as("Кнопка \"Посмотреть все задачи и фильтры\"");

    @Step("Записать имя проекта")
    public String getProjectNameResultText(){
        return searchProjectName.getText();
    }

    @Step("Посчитать количество незакрытых задач")
    public int setCountTasks() {
      String countTask = countTasks.getText();
      String[] splitString = countTask.split(" ");
        return Integer.parseInt(splitString[2]);
    }

    @Step("Посмотреть все задачи по проекту")
    public void viewAllTasks(){
        goToViewAllTasks.click();
    }
}
