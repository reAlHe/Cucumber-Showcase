package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HerokuAppSteps {

    WebDriver driver;

    @Given("I am using the {string} browser")
    public void i_am_using_the_browser(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            driver = new ChromeDriver();
        }
        else {
            System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
            driver = new FirefoxDriver();
        }
    }

    @Given("I am on the Heroku Form Authentication Page")
    public void i_am_on_the_heroku_form_authentication_page() {
        driver.get("https://the-internet.herokuapp.com/login");
        assertEquals("The Internet", driver.getTitle());
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    @Then("I should see the login success message")
    public void the_page_title_should_contain() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000L));

        ExpectedCondition<Boolean> urlEndsWithSecure = driver -> driver.getCurrentUrl().endsWith("/secure");

        wait.until(urlEndsWithSecure);
        driver.quit();
    }
}