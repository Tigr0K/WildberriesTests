package ru.wildberries.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement searchInput = $("#searchInput");
    private ElementsCollection productCards = $$(".product-card__wrapper");
    private ElementsCollection navbarItems = $$(".navbar-pc__item");
    private SelenideElement basketEmpty = $(".basket-empty__btn");
    private ElementsCollection serviceMenuList = $$(".service-menu__list li");
    private SelenideElement searchPhotoButton = $(".search-catalog__btn-wrap");
    private SelenideElement searchPhotoDndPlace = $("#uploadImageForSearchByImagePopUpContainer");

    public MainPage openPage() {
        open("/");
        return this;
    }

    public MainPage seatchInputClick(String searchQuery) {
        searchInput.setValue(searchQuery).shouldBe(visible).pressEnter();
        return this;
    }

    public MainPage clickOnEmptyBasket(String chapter) {
        navbarItems.findBy(text(chapter)).click();
        return this;
    }

    public MainPage titlesOnCkickBattonAddress(String chapter) {
        navbarItems.findBy(text(chapter)).click();
        return this;
    }

    public MainPage clickOnPhotoSearch() {
        searchPhotoButton.hover().click();
        return this;
    }


    //Проверки
    public MainPage searchResultShouldBeGreaterThan(Integer count) {
        productCards.shouldBe(sizeGreaterThan(count));
        return this;
    }

    public MainPage emptyBusketShouldHaveButton(String buttonText) {
        basketEmpty.shouldHave(text(buttonText));
        return this;
    }

    public MainPage titlesOnCkickBattonAddressResult(List<String> expectedLinks) {
        serviceMenuList.filter(visible).shouldHave(texts(expectedLinks));
        return this;
    }

    public MainPage popUpclickOnPhotoSearch() {
        searchPhotoDndPlace.shouldBe(visible);
        return this;
    }
}

