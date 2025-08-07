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

import static io.qameta.allure.Allure.step;


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
        step("Нажимаем на кнопку корзины", () -> {
            mainPage.openPage()
                    .clickOnNavbarButton(chapter);
        });
        step("Проверяем текст на кнопке", () -> {
            mainPage.emptyBusketShouldHaveButton(buttonText);
        });
    }


    @Tag("SMOKE")
    @Test
    @DisplayName("Появление всплывающего меню при клике на поиск по фото")
    void popUpPhotoSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Нажимаем на кнопку поиска по фото", () -> {
            mainPage.openPage()
                    .clickOnPhotoSearch();
        });
        step("Проверяем текст на кнопке", () -> {
            mainPage.popUpclickOnPhotoSearch();
        });

    }


    @Tag("SMOKE")
    @Test
    @DisplayName("Добавление товара в корзину")
    void addToBasket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Вводим поисковый запрос", () -> {
            mainPage.openPage()
                    .seatchInputClick("Перфоратор");
        });
        step("Добавляем товар в корзину", () -> {
            mainPage.addToBasketGood();
        });
        step("Нажимаем на корзину", () -> {
            mainPage.clickOnNavbarButton("Корзина");
        });
        step("Проверяем, что товар в корзине", () -> {
            mainPage.goodEntityIsExist();
        });
    }

    @Tag("SMOKE")
    @Test
    @DisplayName("Удаления товара из корзины")
    void deleteToBasket() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Вводим поисковый запрос", () -> {
            mainPage.openPage()
                    .seatchInputClick("Перфоратор");
        });
        step("Добавляем товар в корзину", () -> {
            mainPage.addToBasketGood();
        });
        step("Нажимаем на корзину", () -> {
            mainPage.clickOnNavbarButton("Корзина");
        });
        step("Удаляем товар из корзины", () -> {
            mainPage.deleteFromBasketGood();
        });
        step("Проверяем, что товаров в корзине нет", () -> {
            mainPage.goodEntityIsNotExist();
        });
    }

}
