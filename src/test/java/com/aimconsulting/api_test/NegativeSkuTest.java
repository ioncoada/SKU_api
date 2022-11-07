package com.aimconsulting.api_test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class NegativeSkuTest extends BaseSkuPage {

    @DisplayName("Post a sku with no id ")
    @Order(1)
    @Test
    public void postNotFullNewSkuId() {

        Map<String, Object> postSkuMap = new HashMap<>();
        postSkuMap.put("description", skuDescription);
        postSkuMap.put("price", skuPrice);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON).and()
                .body(postSkuMap).when()
                .post();

        assertThat(response.statusCode(), is(HttpStatus.SC_BAD_REQUEST));
        assertEquals("missing field : sku", response.asString());
    }

    @DisplayName("Post a sku with emptyBody ")
    @Order(2)
    @Test
    public void postEmptyBodySkuId() {

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON).and()
                .body("").when()
                .post();

        JsonPath js = response.jsonPath();

        assertThat(response.statusCode(), is(HttpStatus.SC_BAD_GATEWAY));
        assertEquals("Internal server error",js.getString("message"));
    }

    @DisplayName("Get a sku with non-existent id ")
    @Order(3)
    @Test
    public void getNonExistentSkuId() {

        Response response = given().accept(ContentType.JSON).and().pathParam(id, nonExistentSkuId)
                .when().get("/{id}");

        JsonPath js = response.jsonPath();
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), equalTo("application/json"));
        assertFalse(response.asString().contains(nonExistentSkuId));


    }
    @DisplayName("Delete sku with non-existent id")
    @Order(4)
    @Test
    public void deleteNonExistentSku() {
        given().accept(ContentType.JSON).and().pathParam(id, nonExistentSkuId)
                .when().delete("/{id}").then()
                .statusCode(HttpStatus.SC_OK).and()
                .body(is(emptyOrNullString()));


    }


}
