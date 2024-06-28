package com.testesApi.cursoUdemy;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@Data
@NoArgsConstructor
public class TesteApi {

    @Value("${testes.url}")
    private String url;

    @Test
    public void cadastro(){
        String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String corpo = "{\"email\":\"esantos@gmail.com\", \"appDescription\":\"testes the cat api\"}";

        Response response = given().contentType("application/json").body(corpo)
                .when().post(url);

        response.then().statusCode(200);
        System.out.println("" + response.body().asString());

        response.then().body("message", containsString("SUCCE")).statusCode(200);
    };

    @Test
    public void votacao(){
        String url = "https://api.thecatapi.com/v1/votes";
        String corpo = "{\"type\":\"mark-as-seen\",\"context\":\"\",\"subcontext\":\"\",\"context2\":\"\",\"subcontext2\":\"\",\"currency\":\"R$\",\"course_ids\":\"2908628,1243864,2675786\",\"list_price\":\"||\",\"discount_price\":\"||\"}";

        Response response = given().contentType("application/json").body(corpo)
                .when().post(url);

        response.then().statusCode(201);
        System.out.println("" + response.body().asString());

        response.then().body("type", containsString("mark-as-seen")).statusCode(201);
        String type = response.jsonPath().getString("type");
        System.out.println("type: " + type);
    };

    @Test
    public void deletaVoto(){
        String url = "https://api.thecatapi.com/v1/votes/{vote_id}";

        Response response = given()
                .contentType("application/json")
                .pathParam("vote_id", "vote_id")
                .when().delete(url);

        String type = response.body().asString();
        System.out.println("type: " + type);
        response.then().body("message", containsString("404 - please consult the documentation for correct url to call. https://docs.thecatapi.com/")).statusCode(405);
    };
}
