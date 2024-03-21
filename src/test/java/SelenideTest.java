import WebHooks.WebHooks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraAllTasksInProjectPage;
import pages.JiraIfellowPage;
import pages.JiraMainPage;
import pages.JiraTasksInTestProjectPage;
import pages.TaskPage;


@DisplayName("Jira Test")
public class SelenideTest extends WebHooks {

    private final JiraIfellowPage  jiraIfellowPage = new JiraIfellowPage();

    private final JiraMainPage jiraMainPage = new JiraMainPage();

    private final JiraTasksInTestProjectPage jiraTasksInTestProjectPage = new JiraTasksInTestProjectPage();

    private final JiraAllTasksInProjectPage jraAllTasksInProjectPage = new JiraAllTasksInProjectPage();

    private final TaskPage taskPage = new TaskPage();

    @DisplayName("Проверка авторизации в Jira")
    @Test
    public void logInTest (){
        String searchRequest = "System Dashboard";
        jiraIfellowPage.loginIntoJira(System.getProperty("login"),System.getProperty("password"));
        Assertions.assertEquals(searchRequest,jiraMainPage.getFirstResultText());

    }

    @DisplayName("Перейти в проект Test")
    @Test
    public void getTestProject(){
        String searchRequest = "Test";
        jiraIfellowPage.loginIntoJira(System.getProperty("login"),System.getProperty("password"));
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        Assertions.assertEquals(searchRequest,jiraTasksInTestProjectPage.getProjectNameResultText());
    }

    @DisplayName("Найти в проекте Test задачу удовлетворяющую параметрам фильтра")
    @Test
    public void getTestSeleniumTask(){
        String version = "Version 2.0";
        String status = "СДЕЛАТЬ";
        jiraIfellowPage.loginIntoJira(System.getProperty("login"),System.getProperty("password"));
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraTasksInTestProjectPage.viewAllTasks();
        jraAllTasksInProjectPage.clickExpertSearch();
        jraAllTasksInProjectPage.searchTask("project = TEST AND status = \"To Do\" AND resolution = Unresolved AND fixVersion = \"Version 2.0\" AND text ~ \"TestSelenium\" ORDER BY priority DESC, updated DESC");
        jraAllTasksInProjectPage.clickTask();
        Assertions.assertEquals(version,taskPage.getFixVersionNumber());
        Assertions.assertEquals(status,taskPage.getStatus());
    }

    @DisplayName("Создать новую задачу")
    @Test
    public void createTask(){
        String status = "ГОТОВО";
        jiraIfellowPage.loginIntoJira(System.getProperty("login"),System.getProperty("password"));
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask("Тестовая задача", "Тестовая задача с описанием");
        taskPage.setStatusInProgress();
        taskPage.openOtherStatusBar();
        taskPage.setStatusDone();
        Assertions.assertEquals(status,taskPage.getStatus());
    }

    @DisplayName("Проверка увеличения количества задачи после создания новой задачи")
    @Test
    public void createTaskAndChekTaskCount(){
        jiraIfellowPage.loginIntoJira(System.getProperty("login"),System.getProperty("password"));
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

