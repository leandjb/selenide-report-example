import com.codeborne.selenide.junit5.TextReportExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith({TextReportExtension.class})
public class SearchJunit_Test {

    @BeforeAll
    public static void setUp(){
        WebDriverManager.edgedriver().setup();
    }

    @Test
    public void userCanSearchOnGooglePage() {
        open("https://google.com");

        $(By.name("q")).setValue("DJI M30").pressEnter();
        $("#gbwa > div > a").shouldBe(visible);
    }

    @Test
    public void userCanSearchOnBingPage() {
        open("https://bing.com");

        $(By.name("q")).setValue("DJI M30").pressEnter();
        $("#gbwa > div > a").shouldBe(visible);
    }
}
