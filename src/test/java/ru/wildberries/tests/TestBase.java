package ru.wildberries.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.wildberries.config.BaseConfig;
import ru.wildberries.helpers.UiAttachUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    @BeforeAll
    static void setupConfig() {
        Configuration.pageLoadStrategy = "eager";

        BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getProperties());
        Configuration.browserSize = config.browserSize();
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browserName();
        Configuration.browserVersion = config.browserVersion();

        if (config.isRemote()) {
            configureRemote();
        }
    }

    private static void configureRemote() {
        BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getProperties());
        Configuration.remote = config.remoteUrl();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void setUp() {
        open("/");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterEach
    void tearDown() {
        UiAttachUtils.screenshotAs("Last screenshot");
        UiAttachUtils.pageSource();
        UiAttachUtils.browserConsoleLogs();
        UiAttachUtils.addVideo();
        Selenide.closeWebDriver();
    }
}
