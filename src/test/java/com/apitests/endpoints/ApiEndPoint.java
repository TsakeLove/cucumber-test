package com.apitests.endpoints;

import com.apitests.config.Config;
import com.apitests.models.Login;
import com.apitests.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ApiEndPoint {

    public static Response getUserById(long id) {
        return given().
                pathParam("userId", id).when().
                get(Config.USER_BY_ID).
                then().
                extract().response();
    }

    public static Response createPost(Post post) {
        return given()
                .body(post)
                .when()
                .post(Config.POSTS)
                .then()
                .extract()
                .response();
    }

    public static Response getAllUsers() {
        return given().when().
                get(Config.USERS_ENDPOINT).
                then().extract().response();
    }

    public static Response loginUser(Login login) {

        return given().
                body(login).
                post(Config.LOGIN_ENDPOINT).
                then().extract().response();
    }

    public static RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.BASE_URL)
                .header("API-KEY", Config.apiToken)
                .contentType(ContentType.JSON);
    }
}