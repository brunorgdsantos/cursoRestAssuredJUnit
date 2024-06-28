package com.testesApi.cursoUdemy;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
        //String url = "https://www.udemy.com/api-2.0/visits/me/funnel-logs/";
        String corpo = "{\"type\":\"mark-as-seen\",\"context\":\"\",\"subcontext\":\"\",\"context2\":\"\",\"subcontext2\":\"\",\"currency\":\"R$\",\"course_ids\":\"2908628,1243864,2675786\",\"list_price\":\"||\",\"discount_price\":\"||\"}";

        Response response = given().contentType("application/json").body(corpo)
                .when().post(url);

        response.then().statusCode(201);
        System.out.println("" + response.body().asString());

        response.then().body("type", containsString("mark-as-seen")).statusCode(201);
    };
}
