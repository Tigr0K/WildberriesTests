package ru.wildberries.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.wildberries.pages.MainPage;

import static io.qameta.allure.Allure.step;


@DisplayName("Тесты корзины")
@Tag("BasketTests")
public class BasketUiTests extends UiTestBase {
    MainPage mainPage = new MainPage();

    @CsvSource(value = {
            "Корзина, Перейти на главную"
    })
    @Feature("Корзина")
    @Story("Заголовок пустой корзины")
    @Owner("Yuferev")
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
    @Feature("Корзина")
    @Story("Работа с товарами в корзине")
    @Owner("Yuferev")
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
    @Feature("Корзина")
    @Story("Работа с товарами в корзине")
    @Owner("Yuferev")
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
