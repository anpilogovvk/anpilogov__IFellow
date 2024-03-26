import WebHooks.WebHooks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.JiraAllTasksInProjectPage;
import pages.JiraMainPage;
import pages.JiraTasksInTestProjectPage;
import pages.TaskPage;
import util.TestProperties;

@DisplayName("Jira Test")
public class SelenideTest extends WebHooks {

    private final JiraMainPage jiraMainPage = new JiraMainPage();

    private final JiraTasksInTestProjectPage jiraTasksInTestProjectPage = new JiraTasksInTestProjectPage();

    private final JiraAllTasksInProjectPage jraAllTasksInProjectPage = new JiraAllTasksInProjectPage();

    private final TaskPage taskPage = new TaskPage();

    @DisplayName("Проверка авторизации в Jira")
    @Test
    public void logInTest (){
        Assertions.assertEquals(TestProperties.getProperty("search.auth.done"),jiraMainPage.getFirstResultText());
    }

    @DisplayName("Перейти в проект Test")
    @Test
    public void getTestProject(){
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        Assertions.assertEquals(TestProperties.getProperty("search.project.name"),jiraTasksInTestProjectPage.getProjectNameResultText());
    }

    @DisplayName("Найти в проекте Test задачу удовлетворяющую параметрам фильтра")
    @Test
    public void getTestSeleniumTask(){
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraTasksInTestProjectPage.viewAllTasks();
        jraAllTasksInProjectPage.searchTask("project = TEST AND status = \"To Do\" AND resolution = Unresolved AND fixVersion = \"Version 2.0\" AND text ~ \"TestSelenium\" ORDER BY priority DESC, updated DESC");
        Assertions.assertEquals(TestProperties.getProperty("task.fix.version"),taskPage.getFixVersionNumber());
        Assertions.assertEquals(TestProperties.getProperty("task.status.next"),taskPage.getStatus());
    }

    @DisplayName("Создать новую задачу")
    @Test
    public void createTask(){
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask("Тестовая задача", "Тестовая задача с описанием");
        taskPage.setStatusInProgress();
        taskPage.openOtherStatusBar();
        taskPage.setStatusDone();
        Assertions.assertEquals(TestProperties.getProperty("task.status.done"),taskPage.getStatus());
    }

    @DisplayName("Проверка увеличения количества задачи после создания новой задачи")
    @Test
    public void createTaskAndChekTaskCount(){
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        int resultBefore = jiraTasksInTestProjectPage.setCountTasks();
        jiraMainPage.getCreateForm();
        jiraMainPage.CreateTask("Тестовая задача", "Тестовая задача с описанием");
        jiraMainPage.getProjectsMenu();
        jiraMainPage.getProject();
        int resultAfter = jiraTasksInTestProjectPage.setCountTasks();
        Assertions.assertTrue(resultAfter>resultBefore);
    }
}

