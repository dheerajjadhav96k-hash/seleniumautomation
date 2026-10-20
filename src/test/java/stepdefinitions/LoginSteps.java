package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
    
public class LoginSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("the user is on the login page")
    public void openLoginPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }

    @When("the user enters username {string}")
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
    }

    @When("the user enters password {string}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
    }

    @When("the user clicks the login button")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
    }

    @Then("the products page should be displayed")
    public void verifyProductsPage() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "Products page was not displayed"
        );
        WebElement pageTitle = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        Assert.assertEquals(pageTitle.getText(), "Products", "Products page title was not displayed");
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}
