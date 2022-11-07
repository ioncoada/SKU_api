package com.aimconsulting.api_test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositiveSkuTest extends BaseSkuPage {


    @DisplayName("Post a sku id \"berliner\"")
    @Order(1)
    @Test
    public void postNewSkuId() {

        Map<String, Object> postSkuMap = new HashMap<>();
        postSkuMap.put("sku", skuId);
        postSkuMap.put("description", skuDescription);
        postSkuMap.put("price", skuPrice);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON).and()
                .body(postSkuMap).when()
                .post();

        JsonPath jsonPath = response.jsonPath();

        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), equalTo("application/json"));
        assertThat(jsonPath.getString("sku"), equalTo(postSkuMap.get("sku")));
        assertThat(jsonPath.getString("description"), equalTo(postSkuMap.get("description")));
        assertThat(jsonPath.getString("price"), equalTo(postSkuMap.get("price")));
    }

    @DisplayName("Get all sku id's")
    @Order(2)
    @Test
    public void getAllSku() {
        Response response = given().accept(ContentType.JSON)
                .when().get();

        JSONArray jsonArray = new JSONArray(response.body().asString());

        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), equalTo("application/json"));
        assertTrue(jsonArray.length() > 0);
    }

    @DisplayName("Get sku id \"berliner\"")
    @Order(3)
    @Test
    public void getSpecificSku() {
        Response response = given().accept(ContentType.JSON).and().pathParam(id, skuId)
                .when().get("/{id}");

        JsonPath js = response.jsonPath();
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), equalTo("application/json"));
        assertEquals(skuId, js.getString("Item.sku"));

    }

    @DisplayName("Delete sku id \"berliner\"")
    @Order(4)
    @Test
    public void deleteSpecificSku() {
        given().accept(ContentType.JSON).and().pathParam(id, skuId)
                .when().delete("/{id}").then()
                .statusCode(HttpStatus.SC_OK).and()
                .body(is(emptyOrNullString()));


    }
}
