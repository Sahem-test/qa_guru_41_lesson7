package guru.qa.classWork;

import com.codeborne.selenide.CollectionCondition;
import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DuckduckgoWeb {

    @BeforeEach
    void SetUp(){
        open("https://duckduckgo.com/");
    }

   @ValueSource(strings ={
           "Selenide",
           "JUnite",
           "Allure"
   })
    @ParameterizedTest(name = "Для поискового запроса {0}, должен возвращать не пустой список карточек")
    @Tag("blocker")
    void searchContainNotEmptyList(String searchQuery) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $$("li[data-layout='organic']").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }


   @CsvFileSource(resources = "/test_data/searchContainExpectedURL.csv")
    @ParameterizedTest(name = "Для поискового запроса {0}, должна быть ссылка {1}")
    @Tag("blocker")
    void searchContainExpectedURL(String searchQuery,String expectedLink) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=r1-0]").shouldHave(text(expectedLink));
    }


    @Test
    @DisplayName("Для поискового запроса 'selenide', должен возвращать не пустой список фото")
    void successfulSearchPhotoTest(){
        $("[name=q]").setValue("selenide").pressEnter();
        $(byText("Изображения")).click();
        $$("li[style]").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

}

