package WebHooks;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;


public class WebHooks {

    @DisplayName("Открыть сайт edujira.ifellow.ru и задать параметры окна бразуера")
    @BeforeEach
    public void initBrowser() throws IOException {
        SelenideLogger.addListener("AllureSelenide",
        new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        Configuration.browser = Browsers.CHROME;
        Selenide.open(System.getProperty("url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

}
