package com.ecoronelgit.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MutantTest {
    private static final String CONTENT_TYPE = "application/json";
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String DELETE = "DELETE";
    private static final String BASE_URL = "http://localhost:8080/mutant";
    private static final String STATS_URL = "http://localhost:8080/stats";
    private static final String RESET_URL = "http://localhost:8080/reset";

    @Test
    public void shouldGiveStatusCode200() {
        resetMutantRestApi();
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
        resetMutantRestApi();
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
        resetMutantRestApi();
        String json = "{ \"dna\":[\"ATGCGA\",\"CAGTG\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }";
        given()
                .contentType(CONTENT_TYPE)
                .body(json)
                .when()
                .request(POST, BASE_URL)
                .then()
                .statusCode(403);
    }

    @Test
    public void shouldGiveStatsWhenSaveSeveralData() {
        resetMutantRestApi();
        givenSaveFourMutantOneRepeated();
        givenSaveTenHumanOneRepeated();

        given()
                .contentType(CONTENT_TYPE)
                .when()
                .request(GET, STATS_URL)
                .then()
                .statusCode(200)
                .body("count_mutant_dna", equalTo(4))
                .body("count_human_dna", equalTo(10))
                .body("ratio", equalTo(0.4f));
    }

    private void givenSaveFourMutantOneRepeated() {
        givenCallToMutantRestApi(new String[]{"{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTT\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTA\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTC\"] }"});
    }

    private void givenSaveTenHumanOneRepeated() {
        givenCallToMutantRestApi(new String[]{"{ \"dna\":[\"ATGCGA\",\"AAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"GAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"TAGTGC\",\"TTATTC\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"ATATTG\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTA\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"GTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"CTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"TGACGG\",\"GCGTCA\",\"TCACTG\"] }",
                "{ \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"GGACGG\",\"GCGTCA\",\"TCACTG\"] }"});
    }

    private void givenCallToMutantRestApi(String[] jsons) {
        for(String json : jsons) {
            given()
                    .contentType(CONTENT_TYPE)
                    .body(json)
                    .when()
                    .request(POST, BASE_URL);
        }
    }

    private void resetMutantRestApi() {
        given()
                .contentType(CONTENT_TYPE)
                .when()
                .request(DELETE, RESET_URL);
    }

}
