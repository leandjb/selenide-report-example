import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Listeners({TextReport.class})
@Report
public class SearchTestNG_Test {


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
