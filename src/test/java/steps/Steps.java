package steps;


import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;

import org.junit.jupiter.api.Assertions;
import pages.*;


public class Steps{

    private final JiraIfellowPage  jiraIfellowPage = new JiraIfellowPage();

    private final JiraMainPage jiraMainPage = new JiraMainPage();

    private final JiraTasksInTestProjectPage jiraTasksInTestProjectPage = new JiraTasksInTestProjectPage();

    private final JiraAllTasksInProjectPage jraAllTasksInProjectPage = new JiraAllTasksInProjectPage();

    private final TaskPage taskPage = new TaskPage();

    private int result1;
    private int result2;

    @И("авторизоваться в Jira")
    public void logInTest(){
        String searchRequest = "System Dashboard";
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        Assertions.assertEquals(searchRequest,jiraMainPage.getFirstResultText());
    }

    @И("перейти в проект Test")
    public void getTestProject(){
        String searchRequest = "Test";
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        Assertions.assertEquals(searchRequest,jiraTasksInTestProjectPage.getProjectNameResultText());
    }

    @И ("сделать поиск задач")
    public void advanceSearchTasks(){
        jiraTasksInTestProjectPage.viewAllTasks();
        jraAllTasksInProjectPage.clickExpertSearch();
        jraAllTasksInProjectPage.searchTask("project = TEST AND status = \"To Do\" AND resolution = Unresolved AND fixVersion = \"Version 2.0\" AND text ~ \"TestSelenium\" ORDER BY priority DESC, updated DESC");
        jraAllTasksInProjectPage.clickTask();
    }

    @И("проверяем статус {string}")
    public void setVersion(String status) {
        Assertions.assertEquals(status ,taskPage.getStatus(), "Ожидаемый код статуса не равен полученному!");
    }

    @И("проверяем fix version {string}")
    public void setStatus(String version) {
        Assertions.assertEquals(version ,taskPage.getFixVersionNumber(), "Ожидаемый код статуса не равен полученному!");
    }

    @Допустим("создаём новую задачу в Jira с темой {string} и описанием {string}")
    public void createTask(String taskTopic, String description){
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask(taskTopic, description);
    }

    @И ("провести созданную задачу по всем  статусам")
    public void changeStatus(){
        taskPage.setStatusInProgress();
        taskPage.openOtherStatusBar();
        taskPage.setStatusDone();
    }

    @И ("посчитать количество задач первый раз")
    public void checkCountFirst(){
        result1 =  jiraTasksInTestProjectPage.setCountTasks();
    }

    @И ("посчитать количество задач второй раз")
    public void checkCountSecond(){
        result2 =  jiraTasksInTestProjectPage.setCountTasks();
    }

    @И("проверить изменилось ли количество задач")
    public void compareResults(){
        Assertions.assertTrue(result2>result1);
    }

}

