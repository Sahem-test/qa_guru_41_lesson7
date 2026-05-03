package guru.qa.classWork;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("класс с простыми тестами")
public class SimpleTest {

    @Test
    @DisplayName("Первый простой тест")  // аннотация для описания, что делает тест или класс
    void simpleTest(){
        System.out.println("Hello World!");
    }
}
