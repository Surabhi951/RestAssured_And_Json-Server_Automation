//*****************************************
//Author Name : Surabhi Bhagat
//Project     : Rest Api Automation
//Date        : 31/01/2023
//*****************************************

package com.bridgelabz.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class RestApiAutomation {

    public String Authorization;
    public static String userId;
    public String playlistId;

    @BeforeTest
    public void setUp() {
        Authorization = "Bearer BQB1B9UYVo9ccDCpWZIa32yLNOKMaDHn2n0qlUo0rzHmwyPYYcC1ArkUjuaPzHiwCkkMa02a-KUHrUlCWwuaA9lCfpKN6Gm-HbukcOz6yViLFqc3A4hzxa0ncLhmmSrKYOGddAzB_-2RK6-Aet7Ob_BjKUVXi0y_xYHaUW2CBqmVxg2nNpCtqplIJ7ycDg3HaSlorgkgmV7hjoBURZ3HlP5por9dI2zeNLsRHJyZZ9cKwNvtlHt1-d15m7eHgD9G7XXNph0XPt4kN-vmspik9X20KCyeX4Ti";
        userId = "31m5tyn3nnivrykril3u64ji6agq";
        playlistId = "0QK5YzWnxkJQF6w6hKqwax";
    }

    @Test(priority = 1)
    public void getCurrentUserProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me");
        String userId = result.path("id");
        System.out.println("UserId : " +userId);
        String userName = result.path("display_name");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        Assert.assertEquals("Surabhi951", userName);
    }

    @Test(priority = 2)
    public void getUserProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/" +userId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/users/").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 3)
    public void to_createPlaylist() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","Dil Bechara");
        requestBody.put("description","Bil Bechara description20");
        requestBody.put("public","false");

        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())
                .when()
                .post("https://api.spotify.com/v1/users/"+userId+"/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        System.out.println(given().when().post("https://api.spotify.com/v1/users/"+userId+"/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(201);
    }

    @Test(priority = 4)
    public void addItemToPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"uris\": [\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\",\"spotify:track:1301WleyT98MSxVHPZCA6M\", \"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        System.out.println(given().when().post("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(201);
    }

    @Test(priority = 5)
    public void updatePlaylistItems() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("range_start",1);
        requestBody.put("insert_before",3);
        requestBody.put("range_length",2);

        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())
                .when()
                .put("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().put("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 6)
    public void changePlaylistDetails() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","Updated Playlist Dil Bechara");
        requestBody.put("description","Updated playlist description");
        requestBody.put("public","false");

        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())
                .when()
                .put("https://api.spotify.com/v1/playlists/" +playlistId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().put("https://api.spotify.com/v1/playlists/").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 7)
    public void getPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/" +playlistId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 8)
    public void getUsersPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/"+userId+"/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/users/\"+userId+\"/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);

    }

    @Test(priority = 9)
    public void getCurrentUsersPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/me/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 10)
    public void getPlaylistItem() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/\"+playlistId+\"/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 11)
    public void removePlaylistItems() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks");
        result.prettyPrint();
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().delete("https://api.spotify.com/v1/playlists/"+playlistId+"/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 12)
    public void getSeveralTracks() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 13)
    public void getTrack() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 14)
    public void getTracksAudioFeatures() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-features/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-features/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 15)
    public void getTracksAudioAnalysis() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-analysis/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 16)
    public void searchForItem() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .queryParam("q" , "artist")
                .queryParam("type", "track")
                .when()
                .get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 17)
    public void getSeveralShows() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 18)
    public void getShow() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/5as3aKmN2k11yfDDDSrvaZ");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/5as3aKmN2k11yfDDDSrvaZ").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 19)
    public void getShowEpisodes() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/5as3aKmN2k11yfDDDSrvaZ/episodes" );
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/5as3aKmN2k11yfDDDSrvaZ/episodes").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
}
