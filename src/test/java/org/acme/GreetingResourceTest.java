package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.acme.entities.JsonHello;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint1() {
        given()
          .when().accept(ContentType.ANY).get("/demo/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }
    
    @Test
    public void testHelloEndpoint2() {
        given()
          .when().accept(ContentType.JSON).get("/demo/hello")
          .then()
             .statusCode(200)
                .body("greeting", equalTo("JSON hello from RESTEasy Reactive"));
    }
    
    @Test
    public void testHelloEndpoint3() {
        given()
          .when().accept(ContentType.ANY).get("/demo/userhello/jonas/hello")
          .then()
             .statusCode(200)
                .body(is("Hello jonas: hello"));
    }
    
    @Test
    public void testHelloEndpoint4() {
        given()
          .when().accept(ContentType.ANY).get("/demo/queryhello?user=jonas&greeting=Nice to see you")
          .then()
             .statusCode(200)
                .body(is("Hello jonas: Nice to see you"));
    }
    
    @Test
    public void testHelloEndpoint5() {
        given()
          .when().accept(ContentType.ANY).get("/demo/querymixed/jonas?greeting=Nice to see you")
          .then()
             .statusCode(200)
                .body(is("Hello jonas: Nice to see you"));
        // Test default value as well
        given()
          .when().accept(ContentType.ANY).get("/demo/querymixed/jonas")
          .then()
             .statusCode(200)
                .body(is("Hello jonas: Nice to see you!"));
    }
    
    @Test
    public void testHelloEndpoint6() {
        // Test call corresponds to curl:
        // curl -H "Content-Type: application/json" -H "Accept: text/plain" -X POST -d '{"greeting":"Bing Bång"}' "http://localhost:8080/hello/kalle"
        
        given()
          .when().accept(ContentType.ANY).contentType(ContentType.JSON).body(new JsonHello("Bing Bång")).post("/demo/hello/jonas")
          .then()
             .statusCode(200)
                .body(is("Hello jonas: Bing Bång"));

    }

}