import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Listeners({TextReport.class, SoftAsserts.class, ExtentITestListenerClassAdapter.class})
@Report
public class SearchEbayTestNG_Test {

    //TODO: Adaptar credenciales de Scotia
//    @DataProvider(name = "Authentication")
//    public static Object[][] credentials() {
//        return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_2", "Test@123" } };
//    }

    @DataProvider(name = "droneModels")
    public Object[] droneModelsProvider() {
        return new Object[]{
                "BetaFPV pavo20",
                "BetaFPV pavo pico"
        };
    }

    @BeforeTest
    void setUp(){
//        ExtentSparkReporter spark = new ExtentSparkReporter("test-results/index.html");
//        spark.config().setDocumentTitle("Automation Test #1 Report");
//        spark.config().setTheme(Theme.STANDARD);
//        spark.config().setReportName("Name of Automation Report");
//        extent.attachReporter(spark);


        Configuration.headless = true;
        Configuration.assertionMode = AssertionMode.SOFT;
        Selenide.open("https://www.ebay.com/");
    }
    @Test(groups = "Regression", testName = "Verificar precios de DJI Air Unit O3" )
    void verifyPricesO3() {


        if (Selenide.$(By.id("gh-ac")).exists()) {
            Selenide.$(By.id("gh-ac"))
                    .shouldBe(Condition.editable)
                    .setValue("dji o3 air unit");

        } else {


        }

        Assert.assertTrue(
                Selenide.$(Selectors.byId("gh-btn")).is(Condition.enabled));
        Selenide.$(By.id("gh-btn"))
                .shouldBe(Condition.hidden)
                .click();

        if (Selenide.$(By.id("gh-btn")).exists() && Selenide.$(By.id("gh-btn")).isEnabled()){
            Selenide.$(By.id("gh-btn"))
                    .shouldBe(Condition.enabled)
                    .click();



        } else {

        }

        if(Selenide.$(Selectors.byClassName("BOLD")).exists()){
            Selenide.$(Selectors.byClassName("BOLD"))
                    .shouldHave(Condition.text("o3"))
                    .shouldHave(Condition.visible);

        }else {

        }

        if(Selenide.$(Selectors.byClassName("srp-controls--selected-value")).exists() && Selenide.$(Selectors.byClassName("srp-controls--selected-value")).isDisplayed() ){
            Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                    .shouldHave(Condition.text("Mexico"))
                    .shouldHave(Condition.visible);



            Assert.assertTrue(
                    Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                            .is(Condition.textCaseSensitive("Colombia")));





        }else {

        }
    }

    @Test(groups = "Regression", testName = "Verificar precios de BetaFPV", dataProvider = "droneModels" )
    void verifyPricesBetaFPV( String droneModel) {

        //TODO: Se debe corregir las validaciones de testNG en tablas porque no coinciden con el reporte HTML (index.html)

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(Selenide.$(By.id("gh-ac")).is(Condition.editable));

        Selenide.$(By.id("gh-ac"))
                .shouldBe(Condition.editable)
                .setValue(droneModel);

        softAssert.assertTrue(Selenide.$(By.id("gh-btn")).is(Condition.enabled));
        Selenide.$(By.id("gh-btn"))
                .shouldBe(Condition.enabled)
                .click();
        softAssert.assertTrue(Selenide.$(By.id("gh-btn")).is(Condition.visible));
        Selenide.$(Selectors.byClassName("BOLD"))
                .shouldHave(Condition.exist, Condition.visible);

        softAssert.assertTrue(Selenide.$(Selectors.byClassName("srp-controls--selected-value")).is(Condition.text("colombia")));
        Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                .shouldHave(Condition.text("COLOMBIA"))
                .shouldHave(Condition.exist, Condition.visible);


    }

    @AfterTest
    void tearDown(){

        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) throws IOException {
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                Selenide.screenshot("test-results/screenshot-fail");
                ExtentTestManager.getTest(result).fail("TestResult.FAILURE, event afterMethod");

                break;
            case ITestResult.SKIP:
                ExtentTestManager.getTest(result).skip("TestResult.SKIP, event afterMethod");
                break;
            default:
                ExtentTestManager.getTest(result).pass("TestResult.PASS, event afterMethod");
                break;
        }
    }


}

