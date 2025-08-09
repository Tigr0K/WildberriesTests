package ru.wildberries.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:${runType}.properties",
        "classpath:localUi.properties",
})

public interface UiBaseConfig extends Config {
    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://www.wildberries.ru")
    String baseUrl();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    String remoteUrl();

    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserVersion")
    @DefaultValue("113")
    String browserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

}
