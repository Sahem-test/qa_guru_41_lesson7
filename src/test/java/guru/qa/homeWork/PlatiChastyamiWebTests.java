package guru.qa.homeWork;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PlatiChastyamiWebTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://platichastyami.ru/");
    }

    @Tag("homeWork")
    @ValueSource(strings = {"Покупателям", "Продавцам", "Магазины", "Отвечаем на вопросы"})
    @ParameterizedTest(name = "Проверка, пункт меню {0} отображается в шапке сайта")
    void headerMenuShouldContainExpectedItem(String menuItem) {
        open("https://platichastyami.ru/");
        $$(".navigation__link").findBy(text(menuItem)).shouldBe(visible);
    }

    @Tag("homeWork")
    @CsvFileSource(resources = "/test_data/pageOfBuyersShouldContainLinkOfPartnersTest.csv")
    @ParameterizedTest(name = "Проверка, что блок партнёра {0} содержит корректную ссылку:  {2}")
    void pageOfBuyersShouldContainLinkOfPartnersTest(String partnerName, String id, String expectedLink) {
        open("https://platichastyami.ru/");
        $$(".navigation__link").findBy(text("Покупателям")).click();

        $("[data-elem-id='" + id + "']")
                .$("a")
                .shouldHave(attribute("href", expectedLink))
                .shouldBe(exist);
    }

    static Stream<Arguments> pageData() {
        return Stream.of(
                Arguments.of("Покупателям", "https://platichastyami.ru/"),
                Arguments.of("Продавцам", "https://platichastyami.ru/for-partners"),
                Arguments.of("Магазины", "https://platichastyami.ru/partners"),
                Arguments.of("Отвечаем на вопросы", "https://platichastyami.ru/faq")
        );
    }
    @Tag("homeWork")
    @MethodSource("pageData")
    @ParameterizedTest(name = "Логотип отображается на странице: {0}")
    void logoShouldBeVisibleOnEachPage(String pageName, String pageUrl) {
        open(pageUrl);
        $(".logo__img").shouldBe(visible);
    }


}


