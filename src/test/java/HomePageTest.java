import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class HomePageTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("start-maximized");
        webDriver = new ChromeDriver(options);
        webDriver.get("https://www.amazon.com");
    }

    @Test
    public void homePage_title_isVisible() {
        String title = webDriver.getTitle();
        assertThat(title, containsString("Amazon"));
    }

    @Test
    public void homePage_searchBar_lookForProduct() {
        String resultText = webDriver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText();
        WebElement searchBar = webDriver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys("Steam Deck");

        webDriver.findElement(By.id("nav-search-submit-button")).click();
        assertThat(resultText, equalTo("\"Steam Deck\""));
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
