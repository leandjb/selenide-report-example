import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TextReport.class, SoftAsserts.class, ExtentITestListenerClassAdapter.class})
@Report
public class SearchEbayTestNG_Test {
        ExtentReports extent = new ExtentReports();
        ExtentTest test1 = extent.createTest("Login Test");
        ExtentTest test2 = extent.createTest("Test #2");

    @BeforeTest
    void setUp(){
//        ExtentSparkReporter spark = new ExtentSparkReporter("test-results/index.html");
//        spark.config().setDocumentTitle("Automation Test #1 Report");
//        spark.config().setTheme(Theme.STANDARD);
//        spark.config().setReportName("Name of Automation Report");
//        extent.attachReporter(spark);

        test1.info("test started...");
        test1.info("URL uploaded");
        test1.pass("login test completed");


        Configuration.headless = true;
        Configuration.assertionMode = AssertionMode.SOFT;
        Selenide.open("https://www.ebay.com/");
    }
    @Test(groups = "Regression", testName = "Verificar precios de DJI Air Unit O3" )
    void verifyPricesO3() {
        test2.info("test #2 started...");

        if (Selenide.$(By.id("gh-ac")).exists()) {
            Selenide.$(By.id("gh-ac"))
                    .shouldBe(Condition.editable)
                    .setValue("dji o3 air unit");
            test2.info("Text uploaded");
        } else {

            test2.fail("search bar is missing.");
        }

        if (Selenide.$(By.id("gh-btn")).exists() && Selenide.$(By.id("gh-btn")).isEnabled()){
            Selenide.$(By.id("gh-btn"))
                    .shouldBe(Condition.enabled)
                    .click();

            Assert.assertTrue(
                    Selenide.$(Selectors.byId("gh-btn")).is(Condition.enabled));
            test2.pass("text entered and click on search button.");
        } else {
            test2.fail("search button is missing.");
        }

        if(Selenide.$(Selectors.byClassName("BOLD")).exists()){
            Selenide.$(Selectors.byClassName("BOLD"))
                    .shouldHave(Condition.text("o3"))
                    .shouldHave(Condition.visible);

        }else {
            test2.fail("Product category is missing.");
        }

        if(Selenide.$(Selectors.byClassName("srp-controls--selected-value")).exists() && Selenide.$(Selectors.byClassName("srp-controls--selected-value")).isDisplayed() ){
            Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                    .shouldHave(Condition.text("Mexico"))
                    .shouldHave(Condition.visible);

            test2.info("Testing");

            Assert.assertTrue(
                    Selenide.$(Selectors.byClassName("srp-controls--selected-value"))
                            .is(Condition.textCaseSensitive("Colombia")));


//            test2.pass("test Checked");


        }else {
            test2.fail("Country Tag is missing.");
        }

    }

    @AfterTest
    void tearDown(){
        extent.flush();
        Selenide.closeWindow();
        Selenide.closeWebDriver();
    }
}

