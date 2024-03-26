package tests;

import hooks.WebHooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

@DisplayName("Прогон всех тестов")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"classpath:steps", "classpath:hooks"},
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class TestClass extends WebHooks {

}