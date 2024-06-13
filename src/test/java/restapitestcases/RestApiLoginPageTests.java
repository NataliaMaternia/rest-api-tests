package restapitestcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static io.restassured.RestAssured.get;

public class RestApiLoginPageTests {

    private WebDriver driver;
    private RestApiLoginPageTests restApiLoginPageTests;

    @Test
    public void loginWithCorrectCredentials() throws InterruptedException {
        //beforeEach
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String basePageAddress = get("https://www.saucedemo.com/")
                .then()
                .extract().path("response.name");

    }

}
