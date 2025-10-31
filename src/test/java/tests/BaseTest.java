package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected String browserName;
    protected boolean headless = true;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser, @Optional("true") String headlessMode) {
        this.browserName = browser;
        this.headless = Boolean.parseBoolean(headlessMode);
        driver = DriverManager.getDriver(browser, this.headless);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
