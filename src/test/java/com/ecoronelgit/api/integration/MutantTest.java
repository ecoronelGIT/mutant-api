package com.ecoronelgit.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MutantTest {
    private static final String CONTENT_TYPE = "application/json";
    private static final String POST = "POST";
    private static final String BASE_URL = "http://localhost:8080/mutant";

    @Test
    public void shouldGiveStatusCode200() {
        String json = "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";
        given()
                .contentType(CONTENT_TYPE)
                .body(json)
                .when()
                .request(POST, BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGiveStatusCode403() {
        String json = "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }";
        given()
                .contentType(CONTENT_TYPE)
                .body(json)
                .when()
                .request(POST, BASE_URL)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldGiveStatusCode403WithIncorrectDNA() {
        String json = "{ \"dna\":[\"ATGCGA\",\"CAGTG\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }";
        given()
                .contentType(CONTENT_TYPE)
                .body(json)
                .when()
                .request(POST, BASE_URL)
                .then()
                .statusCode(403);
    }
}
