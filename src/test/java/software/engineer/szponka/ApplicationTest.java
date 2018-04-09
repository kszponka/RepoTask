package software.engineer.szponka;

import static org.hamcrest.core.IsEqual.equalTo;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @LocalServerPort
   private int port;

  @Test
  public void contextLoads() {
  }

  @Test
  public void checkApiGitHubIsUp() {
    given().when().get("https://api.github.com/").then().statusCode(200);
  }

  @Test
  public void verifyAllAttributesFromOwnRepo() {
    given().port(port).
        when().
        get("/repositories/skrzychuz/manage_it")
        .then()
        .body("fullName", equalTo("skrzychuz/manage_it"))
        .body("description", equalTo("demo project of management system - lite"))
        .body("cloneUrl", equalTo("https://github.com/skrzychuz/manage_it.git"))
        .body("stars", equalTo(0))
        .body("createdAt", equalTo("2018-02-26T15:21:45Z"))
        .statusCode(200);
  }

  @Test
  public void verifyAttributesFromPopularRepo() {
    given().port(port).
        when().
        get("/repositories/tabler/tabler")
        .then()
        .body("fullName", equalTo("tabler/tabler"))
        .body("description",
            equalTo("Tabler is free and open-source HTML Dashboard UI Kit built on Bootstrap 4"))
        .body("cloneUrl", equalTo("https://github.com/tabler/tabler.git"))
        .body("stars", greaterThan(4000))
        .body("stars", lessThan(10000))
        .body("createdAt", equalTo("2018-02-01T09:08:59Z"))
        .statusCode(200);
  }

  @Test
  public void verifyNoExistOwnerAndNoExistRepo() {
    given().port(port).
        when().
        get("/repositories/noname!x@x#x$/norepo!x@x#x$")
        .then()
        .body("message", equalTo("No Such Repository"))
        .statusCode(404);
  }

  @Test
  public void verifyExitstOwnerAndNoExistRepo() {
    given().port(port).
        when().
        get("/repositories/skrzychuz/norepo!x@x#x$")
        .then()
        .body("message", equalTo("No Such Repository"))
        .statusCode(404);
  }
}