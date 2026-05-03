package guru.qa.classWork;

import guru.qa.data.Language;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTests {

    @EnumSource(Language.class)
    @ParameterizedTest
    void SelenideSiteShouldDisplayCorrectText(Language language){
        open("https://ru.selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }

    static Stream<Arguments>SelenideSiteShouldDisplayCorrectTButtons(){

        return Stream.of(
                Arguments.of(Language.EN, List.of("Quick start","Docs","FAQ","Blog","Javadoc","Users","Quotes")),
                Arguments.of(Language.RU, List.of("С чего начать?","Док","ЧАВО","Блог","Javadoc","Пользователи","Отзывы"))
        );
    }

    @MethodSource
    @ParameterizedTest
    void SelenideSiteShouldDisplayCorrectTButtons(Language language, List<String> expectedButtons){
        open("https://ru.selenide.org/");
        $$("#languages a").findBy(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                        .shouldHave(texts(expectedButtons));
        $("h3").shouldHave(text(language.description));
    }
}
