import com.aventstack.extentreports.ExtentReports;
import com.codeborne.selenide.*;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

@ExtendWith({TextReportExtension.class, SoftAssertsExtension.class})
@DisplayName("Verificar precios en ebay")
public class SearchEbay_Junit_Test {

    @BeforeEach
    void setUp(){
        Selenide.open("https://www.ebay.com/");
    }

    @Test
    @DisplayName("Verificar precios de DJI Air Unit O3")
    void verifyPricesO3() {
        Configuration.assertionMode = AssertionMode.SOFT;


        Selenide.$(By.id("gh-ac"))
                .shouldBe(Condition.editable)
                .setValue("dji o3 air unit");
        Selenide.$(By.id("gh-btn"))
                .shouldBe(Condition.enabled)
                .click();

        Selenide.$(Selectors.byClassName("BOLD"))
                .shouldHave(Condition.text("o3"))
                .shouldHave(Condition.exist, Condition.visible);

        Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                .shouldHave(Condition.text("COLOMBIA"))
                .shouldHave(Condition.exist, Condition.visible);

    }
}

