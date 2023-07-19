import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.HomePage;

import java.util.HashMap;
import java.util.Map;

public class HomePageTest {
    private WebDriver webDriver;
    HomePage homePage;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.get("https://www.amazon.com");

        homePage = new HomePage(webDriver);
    }

    @Test
    public void homePage_searchBar_lookForProduct() {
        homePage.searchProduct("Steam Deck");
        homePage.checkProductResults("\"Steam Deck\"");
    }

    @AfterEach
    public void clear() {
        webDriver.quit();
    }

    public ChromeOptions mobileEmulation() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;
    }
}
