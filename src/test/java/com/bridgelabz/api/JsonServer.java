//*****************************************
//Author Name : Surabhi Bhagat
//Project     : Json-Server Automation
//Date        : 31/01/2023
//*****************************************

package com.bridgelabz.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonServer {

    //posts
    @Test
    public void getAllPosts() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:3000/posts");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void createPosts() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "        \"id\": 4,\n" +
                        "        \"title\": \"json-server\",\n" +
                        "        \"author\": \"Sayali\"\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/posts");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }
    @Test
    public void PutPosts() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "        \"id\": 4,\n" +
                        "        \"title\": \"json-server\",\n" +
                        "        \"author\": \"Manali\"\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/posts/4");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }
    @Test
    public void deletePosts() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .delete("http://localhost:3000/posts/2");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void getPost_havingIDAsOne() {
        Response result = given().queryParam("id",1)
                .when().get("http://localhost:3000/posts");
        result.prettyPrint();
    }

    //comments
    @Test
    public void getAllComments() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:3000/comments");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void createComments() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "      \"id\": 4,\n" +
                        "      \"name\": \"naina\",\n" +
                        "      \"body\": \"some comment\",\n" +
                        "      \"postId\": 1\n" +
                        "    }")
                .when()
                .post("http://localhost:3000/comments");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }

    @Test
    public void putComments() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "      \"id\": 4,\n" +
                        "      \"name\": \"Jay\",\n" +
                        "      \"body\": \"some comment\",\n" +
                        "      \"postId\": 1\n" +
                        "    }")
                .when()
                .put("http://localhost:3000/comments/4");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteComments(){
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .delete("http://localhost:3000/comments/3");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void getAllComments_havingPostIDAsOne() {
        Response result = given().queryParam("postId",1)
                .when().get("http://localhost:3000/comments");
        result.prettyPrint();
    }

    //Profile
    @Test
    public void getAllProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:3000/profile");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void createProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "    \"id\": \"3\",\n" +
                        "    \"name\": \"json\",\n" +
                        "    \"age\": \"21\"\n" +
                        "  }")
                .when()
                .post("http://localhost:3000/profile");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
    }

    @Test
    public void putProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "    \"id\": \"3\",\n" +
                        "    \"name\": \"tina\",\n" +
                        "    \"age\": \"21\"\n" +
                        "  }")
                .when()
                .put("http://localhost:3000/profile/3");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void deleteProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .when()
                .delete("http://localhost:3000/profile/1");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
    }

    @Test
    public void getProfile_havingIDAsTwo() {
        Response result = given().queryParam("id",2)
                .when().get("http://localhost:3000/profile");
        result.prettyPrint();
    }
}
