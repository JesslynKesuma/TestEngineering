import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Objects;

public class RegisterTest {
    FirefoxDriver firefoxDriver;

    @Given("browser dibuka")
    public void browser_dibuka() {
        System.out.println("step browser_dibuka");
        System.setProperty("webdriver.gecko.driver",
            Objects.requireNonNull(getClass().getClassLoader()
                .getResource("webdriver/geckodriver.exe")).getFile());
        firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        firefoxDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @Given("user berada di halaman register")
    public void user_berada_di_halaman_register() {
        System.out.println("step user berada di halaman register");
        firefoxDriver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
    }

    @When("user memasukan {string} dan {string}")
    public void user_memasukan_dan(String string, String string2) {
        System.out.println("step memasukan username dan password");
        firefoxDriver.findElement(By.name("email")).sendKeys(string);
        firefoxDriver.findElement(By.name("password")).sendKeys(string2);
        firefoxDriver.findElement(By.name("c_password")).sendKeys(string2);
    }

    @When("user menekan {string}")
    public void user_menekan(String string) {
        System.out.println("step menekan button");
        if(string.equalsIgnoreCase("submit")){
            firefoxDriver.findElement(By.name("submit")).click();
        }else{
            firefoxDriver.findElement(By.id("resetform")).click();
        }
    }

    @Then("tampilkan {string}")
    public void tampilkan(String string) {
        System.out.println("step memeriksa page");
        if(string.equalsIgnoreCase("log in")){
            String checkLogin = firefoxDriver.findElement(By.xpath("//*[contains(text(), '" + "Register" + " ')]")).getText();
            Assertions.assertTrue(checkLogin.equalsIgnoreCase("Register"), "Not Login Page");
        }else if(string.equalsIgnoreCase("create")){
            String checkRegister = firefoxDriver.findElement(By.name("email")).getText();
            Assertions.assertTrue(checkRegister.equalsIgnoreCase(""), "Not Register Page");
        }
        firefoxDriver.close();
        firefoxDriver.quit();
    }

}
