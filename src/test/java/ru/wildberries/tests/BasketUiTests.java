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


@DisplayName("Параметризированные тесты на проверку поиска Wildberries")
@Tag("BasketTests")
public class BasketUiTests extends TestBase {
    MainPage mainPage = new MainPage();

    @CsvSource(value = {
            "Корзина, Перейти на главную"
    })
    @Tag("SMOKE")
    @ParameterizedTest(name = "Появление кнопки при нажатии на корзину")
    void buttunForClickOnBasketTest(String chapter, String buttonText) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        mainPage.openPage()
                .clickOnNavbarButton(chapter)
                .emptyBusketShouldHaveButton(buttonText);
    }


    @Tag("SMOKE")
    @Test
    @DisplayName("Появление всплывающего меню при клике на поиск по фото")
    void popUpPhotoSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        mainPage.openPage()
                .clickOnPhotoSearch()
                .popUpclickOnPhotoSearch();
    }


    @Tag("SMOKE")
    @Test
    @DisplayName("Добавление товара в корзину")
    void addToBasket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        mainPage.openPage()
                .seatchInputClick("Перфоратор")
                .addToBasketGood()
                .clickOnNavbarButton("Корзина")
                .goodEntityIsExist();
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Удаления товара из корзины")
    void deleteToBasket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        mainPage.openPage()
                .seatchInputClick("Перфоратор")
                .addToBasketGood()
                .clickOnNavbarButton("Корзина")
                .deleteFromBasketGood()
                .goodEntityIsNotExist();
    }

}
