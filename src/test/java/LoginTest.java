import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    FirefoxDriver firefoxDriver;

    @Given("buka browser")
    public void buka_browser() {
        System.out.println("step browser_dibuka");
        System.setProperty("webdriver.gecko.driver",
            Objects.requireNonNull(getClass().getClassLoader()
                .getResource("webdriver/geckodriver.exe")).getFile());
        firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        firefoxDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @Given("user berada di halaman login" +
        "")
    public void user_berada_di_halaman_login() {
        System.out.println("step user berada di halaman login");
        firefoxDriver.navigate().to("https://demo.guru99.com/insurance/v1/index.php");
    }

    @When("user input {string} dan {string}")
    public void user_input(String string, String string2) {
        System.out.println("step memasukan username dan password");
        firefoxDriver.findElement(By.name("email")).sendKeys(string);
        firefoxDriver.findElement(By.name("password")).sendKeys(string2);
    }

    @When("user menekan tombol Login")
    public void user_menekan_tombol_login() {
        System.out.println("step menekan button");
        firefoxDriver.findElement(By.name("submit")).click();
    }

    @Then("tampilkan page {string}")
    public void tampilkan_page(String string) {
        System.out.println("step memeriksa page");
        if(string.equalsIgnoreCase("home")){
            List<WebElement> checkHome = firefoxDriver.findElements(By.xpath("//*[contains(text(), '" + "Log out" + " ')]"));
            Assertions.assertTrue(checkHome.size()>0, "Not Home Page");
        }else if(string.equalsIgnoreCase("login")){
            List<WebElement> checkLogin = firefoxDriver.findElements(By.xpath("//*[contains(text(), '" + "Register" + " ')]"));
            Assertions.assertTrue(checkLogin.size()>0, "Not Login Page");
        }
        firefoxDriver.close();
        firefoxDriver.quit();
    }
}
