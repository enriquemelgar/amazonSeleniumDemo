package pageobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver webDriver;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBar;

    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    WebElement resultText;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void searchProduct(String name) {
        searchBar.sendKeys(name);
        searchButton.click();
    }

    public void checkProductResults(String productName) {
        assertThat(resultText.getText(), equalTo(productName));
    }
}
