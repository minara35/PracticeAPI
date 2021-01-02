package Practice1;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.SpartanLombo;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class Spartan1 {
    @DisplayName("Sending a PATCH request while using POJO as body")
    @Test
    public void patchRequest2(){
        SpartanLombo  s = new SpartanLombo();
        s.getName();
        s.getGender();
        s.setGender("Male");



                given()
                .auth().basic("admin", "admin")
                // we are sending a pojo and expecting it to convert to JSON format using Jackson Databind dependency
                .body(s)
                .contentType(ContentType.JSON)
                .pathParam("id",255).
                when()
                .patch("/spartans/{id}").
                then()
                .log().all()
                .statusCode(204);
    }

}
