package steps;

import hooks.WebHooksForSteps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.JiraAllTasksInProjectPage;
import pages.JiraIfellowPage;
import pages.JiraMainPage;
import pages.JiraTasksInTestProjectPage;
import pages.TaskPage;



public class SelenideTest extends WebHooksForSteps {

    private final JiraIfellowPage  jiraIfellowPage = new JiraIfellowPage();

    private final JiraMainPage jiraMainPage = new JiraMainPage();

    private final JiraTasksInTestProjectPage jiraTasksInTestProjectPage = new JiraTasksInTestProjectPage();


    private final JiraAllTasksInProjectPage jraAllTasksInProjectPage = new JiraAllTasksInProjectPage();

    private final TaskPage taskPage = new TaskPage();

    @Test
    public void logInTest(){
        String searchRequest = "System Dashboard";
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        Assertions.assertEquals(searchRequest,jiraMainPage.getFirstResultText());
    }

    @Test
    public void getTestProject(){
        String searchRequest = "Test";
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        Assertions.assertEquals(searchRequest,jiraTasksInTestProjectPage.getProjectNameResultText());
    }

    @Test
    public void getTestSeleniumTask(){
        String version = "Version 2.0";
        String status = "СДЕЛАТЬ";
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraTasksInTestProjectPage.viewAllTasks();
        jraAllTasksInProjectPage.clickExpertSearch();
        jraAllTasksInProjectPage.searchTask("project = TEST AND status = \"To Do\" AND resolution = Unresolved AND fixVersion = \"Version 2.0\" AND text ~ \"TestSelenium\" ORDER BY priority DESC, updated DESC");
        jraAllTasksInProjectPage.clickTask();
        Assertions.assertEquals(version,taskPage.getFixVersionNumber());
        Assertions.assertEquals(status,taskPage.getStatus());
    }

    @Test
    public void createTask(){
        String status = "ГОТОВО";
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask("Тестовая задача", "Тестовая задача с описанием");
        taskPage.setStatusInProgress();
        taskPage.openOtherStatusBar();
        taskPage.setStatusDone();
        Assertions.assertEquals(status,taskPage.getStatus());
    }

    @Test
    public void createTaskAndChekTaskCount(){
        jiraIfellowPage.loginIntoJira("AT1","Qwerty123");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        int result1 = jiraTasksInTestProjectPage.setCountTasks();
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask("Тестовая задача", "Тестовая задача с описанием");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        int result2 = jiraTasksInTestProjectPage.setCountTasks();
        Assertions.assertTrue(result2>result1);
    }

}

