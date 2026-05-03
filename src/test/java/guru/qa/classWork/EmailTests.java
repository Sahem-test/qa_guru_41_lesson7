package guru.qa.classWork;


import org.junit.jupiter.api.*;

@DisplayName("tests Email sending")
public class EmailTests {


    @Test
    @Tag("smoke")
    @DisplayName("Email will should send a new user")
    void emailShouldBeSentForNewUser(){
        System.out.println("entForNewUser");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Email will should send a new user retryable")
    void emailShouldBeSentForNewUserRetry(){
        System.out.println("SentForNewUserRetry");
    }


    @Test
    @Tags({
            @Tag("smoke"),
            @Tag("web"),
    })
    @DisplayName("Email будет отправлен забаненому пользователю")
    void emailShouldBeSentForBannedUser(){
        System.out.println("SentForBannedUser");
    }


    @Disabled("id бага в, кпримеру в Jira BNPLNOT-25698 ")
    @Test
    @DisplayName("Email должен быть отправлен вслучае изменения PaymentMethod ")
    void emailShouldBeSentAfterChangePaymentMethod(){
        throw new AssertionError("Падаем!!!");
    }
}
