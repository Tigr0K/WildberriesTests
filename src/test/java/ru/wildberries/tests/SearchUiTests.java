package ru.wildberries.tests;

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

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


@DisplayName("Параметризированные тесты на проверку поиска Wildberries")
@Tag("SearchTests")
public class SearchUiTests extends TestBase {
    MainPage mainPage = new MainPage();

    @ValueSource(strings = {
            "Перфоратор",
            "Светильник",
            "Видеокарта"
    })
    @ParameterizedTest(name = "Поиск на запрос {0} должен выдавать 10 результатов")
    @Tag("SMOKE")
    void searchShouldReturn10ResultsTest(String searchQuery) {
        mainPage.openPage()
                .seatchInputClick(searchQuery)
                .searchResultShouldBeGreaterThan(10);
    }

    @CsvSource(value = {
            "Корзина, Перейти на главную"
    })
    @Tag("SMOKE")
    @ParameterizedTest(name = "Появление кнопки при нажатии на корзину")
    void buttunForClickOnBasketTest(String chapter, String buttonText) {
        mainPage.openPage()
                .clickOnEmptyBasket(chapter)
                .emptyBusketShouldHaveButton(buttonText);
    }

    static Stream<Arguments> titlsOnClickBattonAddress() {
        return Stream.of(
                Arguments.of(" Адреса ", List.of(
                        "Частые вопросы",
                        "Доставка",
                        "Как вернуть товар и деньги",
                        "Для бизнеса"))
        );
    }

    @MethodSource
    @Tag("SMOKE")
    @ParameterizedTest(name = "Заголовки при нажитии на кнопку \"Адреса\"")
    void titlsOnClickBattonAddress(String chapter, List<String> expectedLinks) {
        mainPage.openPage()
                .titlesOnCkickBattonAddress(chapter)
                .titlesOnCkickBattonAddressResult(expectedLinks);
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Появление всплывающего меню при клике на поиск по фото")
    void popUpPhotoSearch() {
        $(".search-catalog__btn-wrap").hover().click();
        $("#uploadImageForSearchByImagePopUpContainer").shouldBe(visible);
    }

}
