package restapitestcases;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RestApiLoginPageTests {

    @Test
    public void printResponseFromServer() {
        String result = given()
                .get("https://restful-booker.herokuapp.com/ping")
                .then()
                .extract().asPrettyString();

        System.out.println("Response: " + result);
    }

    @Test
    public void shouldBeNotPossibleToLoginWithInvalidCredentials() {
        String loginParamsJson = "{\"username\":\"admin\",\"password\":\"WrongPassword\"}";
        String result = given()
                .body(loginParamsJson)
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .extract()
                .path("reason");

        assertThat(result).isEqualTo("Bad credentials");
    }

    @Test
    public void shouldLoginWithValidCredentials() {
        String loginParamsJson = "{\"username\":\"admin\",\"password\":\"password123\"}";
        String result = given()
                .header("Content-Type", "application/json")
                .body(loginParamsJson)
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");

        assertThat(result).isNotEmpty();
    }

}
