package utils;

import io.restassured.path.json.JsonPath;

public class JsonParser {
    public static String parse(String response,String key) {

    JsonPath jsonPath = new JsonPath(response);
    String value=jsonPath.getString(key);
    return value;
}

}
