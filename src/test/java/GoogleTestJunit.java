import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({TextReportExtension.class})
public class GoogleTestJunit {

    @BeforeAll
    public static void setUp(){
        open("https://google.com");
    }

    @Test
    public void userCanSearchOnGooglePage(){

        $(By.name("q")).setValue("DJI M30").pressEnter();
        $("#gbwa > div > a").shouldBe(visible);
    }
}
