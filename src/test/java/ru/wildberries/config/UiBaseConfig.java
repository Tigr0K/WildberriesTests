package ru.wildberries.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:${runType}.properties"
})

public interface UiBaseConfig extends Config {
    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://www.wildberries.ru")
    String baseUrl();

    @Key("remoteUrl")
    @DefaultValue("https://www.wildberries.ru")
    String remoteUrl();

    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserVersion")
    @DefaultValue("137")
    String browserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

}
