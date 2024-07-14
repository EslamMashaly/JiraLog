import dataReaders.ExcelReader;
import dataReaders.PropertiesReader;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;
import pojo.LoginResponse;
import utils.DateFormat;

import java.text.ParseException;

import static io.restassured.RestAssured.*;

public class Log extends ExcelReader {
    String token;

    @Test(dataProvider = "Credentials")
    public void authenticate(String ntAccount, String password) {

        baseURI = PropertiesReader.Data.getProperty("URL");
        LoginResponse lr = given().relaxedHTTPSValidation().body("{ \n" +
                        "    \"username\": \"" + ntAccount + "\", \n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "     }")
                .header("Content-Type", "application/json")
                .expect().defaultParser(Parser.JSON)
                .when().post("/rest/auth/1/session")
                .then().log().body()
                .assertThat().statusCode(200)
                .extract().response().as(LoginResponse.class);
        token = lr.getSession().getName() + "=" + lr.getSession().getValue();
        System.out.println(token);
    }


    @Test(dataProvider = "Logging")
    public void logHours(String date, String time, String storyID, String description) throws ParseException {

        String loggedDate = DateFormat.DateFormatter(date);

        baseURI = PropertiesReader.Data.getProperty("URL");

        try {

            Double timeInDouble = Double.parseDouble(time) * 3600;
            int timeInSeconds = timeInDouble.intValue();
            System.out.println(timeInDouble);

            given().relaxedHTTPSValidation().body("{\n" +
                            "    \"comment\": \"" + description + "\",\n" +
                            "    \"started\": \"" + loggedDate + "T12:15:16.746+0000\",\n" +
                            "    \"timeSpentSeconds\": \"" + timeInSeconds + "\"\n" +
                            "}")
                    .header("Content-Type", "application/json")
                    .header("Cookie", token)
                    .log().all()
                    .pathParam("storyID", storyID)
                    .when().post("/rest/api/2/issue/{storyID}/worklog")
                    .then().log().all()
                    .assertThat().statusCode(201);

        }
        catch (NumberFormatException ex) {
            given().relaxedHTTPSValidation().body("{\n" +
                            "    \"comment\": \"" + description + "\",\n" +
                            "    \"started\": \"" + loggedDate + "T12:15:16.746+0000\",\n" +
                            "    \"timeSpent\": \"" + time + "\"\n" +
                            "}")
                    .header("Content-Type", "application/json")
                    .header("Cookie", token)
                    .log().all()
                    .pathParam("storyID", storyID)
                    .when().post("/rest/api/2/issue/{storyID}/worklog")
                    .then().log().all()
                    .assertThat().statusCode(201);
        }

    }
}
