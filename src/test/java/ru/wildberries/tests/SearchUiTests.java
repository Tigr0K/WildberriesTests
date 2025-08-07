package ru.wildberries.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.wildberries.pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


@DisplayName("Параметризированные тесты на проверку поиска Wildberries")
@Tag("SearchTests")
public class SearchUiTests extends TestBase {
    MainPage mainPage = new MainPage();

    @ValueSource(strings = {"Перфоратор", "Светильник", "Видеокарта"})
    @ParameterizedTest(name = "Поиск на запрос {0} должен выдавать 10 результатов")
    @Tag("SMOKE")
    void searchShouldReturn10ResultsTest(String searchQuery) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Вводим поисковый запрос", () -> {
            mainPage.openPage().seatchInputClick(searchQuery);
        });
        step("Смотрим, что результатов >10", () -> {
            mainPage.searchResultShouldBeGreaterThan(10);
        });
    }


    static Stream<Arguments> titlsOnClickBattonAddress() {
        return Stream.of(Arguments.of(" Адреса ", List.of("Частые вопросы", "Доставка", "Как вернуть товар и деньги", "Для бизнеса")));
    }

    @MethodSource
    @Tag("SMOKE")
    @ParameterizedTest(name = "Заголовки при нажитии на кнопку \"Адреса\"")
    void titlsOnClickBattonAddress(String chapter, List<String> expectedLinks) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Нажимаем на кнопку", () -> {
            mainPage.openPage().clickOnNavbarButton(chapter);
        });
        step("Проверяем наличие заголовков", () -> {
            mainPage.titlesOnCkickBattonAddressResult(expectedLinks);
        });
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Заголовок при НЕ валидном запросе поиска")
    void titleNotValidReqSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Вводим поисковый запрос", () -> {
            mainPage.openPage().seatchInputClick("khhhhkjii");
        });
        step("Проверяем текст в заголовке", () -> {
            mainPage.notFoundSearchTitileExist("khhhhkjii");
        });
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Заголовок при валидном запросе поиска")
    void titleValidReqSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Вводим поисковый запрос", () -> {
            mainPage.openPage().seatchInputClick("Перфоратор");
        });
        step("Проверяем текст в заголовке", () -> {
            mainPage.validSearchTitileExist("Перфоратор");
        });

    }

}
