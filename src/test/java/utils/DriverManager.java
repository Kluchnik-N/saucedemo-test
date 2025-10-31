package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    
    public static WebDriver getDriver(String browserName, boolean headless) {
        WebDriver driver;
        
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                }
                chromeOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setBinary("C:\\Users\\79291\\Desktop\\msedgedriver.exe");
                if (headless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions defaultOptions = new ChromeOptions();
                if (headless) {
                    defaultOptions.addArguments("--headless");
                    defaultOptions.addArguments("--disable-gpu");
                    defaultOptions.addArguments("--no-sandbox");
                    defaultOptions.addArguments("--disable-dev-shm-usage");
                }
                defaultOptions.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(defaultOptions);
        }
        
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        
        return driver;
    }
}

