package com.aimconsulting.api_test;

import com.aimconsulting.utils.ConfigurationReader;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseSkuPage {
    protected static String skuId = "berliner";
    protected static String nonExistentSkuId = "nonExistentBerliner";
    protected static String skuDescription = "Jelly donut";
    protected static String skuPrice = "2.99";
    protected static String id = "id";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("sku.api.url");
    }
}
