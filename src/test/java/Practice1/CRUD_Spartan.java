package Practice1;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.Matchers.* ;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CRUD_Spartan {

    private static int currentID;

    @BeforeAll
    public static void setUp(){
        baseURI= "http://54.91.92.1:8000";
        basePath= "/api";
    }

    //CRUD:     Create, Read, Update, Delete
    //Requests:   POST,   GET,  PUT,  DELETE

    //CREATE
    @Order(1)
    @DisplayName("Create new Spartan using Map as body")
    @Test
    public void postRequest(){
        /*
            {
                "name": "Amir",
                "gender": "Male",
                "phone": 1231231234
            }
         */

        //serialization: from map, pojo to json object
        //deserialization: from json object to map, pojo
        Map<String, Object> spartan1 = new HashMap<>();
        spartan1.put("name", "Batch20");
        spartan1.put("gender", "Male");
        spartan1.put("phone", 1231231234);

        currentID =
                given()
                        .auth().basic("admin", "admin")
                        .contentType(ContentType.JSON)
                        .body(spartan1)
                        .when()
                        .post("/spartans").
                        then()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .time(lessThan(3000L), TimeUnit.MILLISECONDS)
                        .extract()
                        .jsonPath().getInt("data.id");

        System.out.println(spartan1.get("name"));

    }

    //READ
    @Order(2)
    @DisplayName("Read newly created Spartan")
    @Test
    public void getRequest(){
        given()
                .auth().basic("admin","admin")
                .pathParam("id", currentID).
                when()
                .get("/spartans/{id}").
                then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(3000L), TimeUnit.MILLISECONDS);
    }

    //UPDATE
    @Order(3)
    @DisplayName("Update newly created Spartan")
    @Test
    public void putRequest(){
        Map<String, Object> spartan2 = new HashMap<>();
        spartan2.put("name", "Batch21");
        spartan2.put("gender", "Female");
        spartan2.put("phone", 1999999999);

        given()
                .auth().basic("admin", "admin")
                .body(spartan2)
                .contentType(ContentType.JSON)
                .pathParam("id", currentID).
                when()
                .put("/spartans/{id}").
                then()
                .statusCode(204)
                .time(lessThan(3000L), TimeUnit.MILLISECONDS);

        System.out.println(spartan2.get("name"));

    }

    //DELETE
    @Order(4)
    @DisplayName("Delete newly created Spartan")
    @Test
    public void deleteRequest(){
        given()
                .auth().basic("admin", "admin")
                .pathParam("id", currentID).
                when()
                .delete("/spartans/{id}").
                then()
                .statusCode(204)
                .time(lessThan(3000L), TimeUnit.MILLISECONDS);
    }



    @AfterAll
    public static void tearDown(){
        reset();
    }

}
