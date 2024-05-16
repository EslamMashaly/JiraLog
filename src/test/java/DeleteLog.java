import dataReaders.ExcelReader;
import dataReaders.PropertiesReader;
import io.restassured.parsing.Parser;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import pojo.LoginResponse;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class DeleteLog extends ExcelReader {
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
//        System.out.println(token);
    }


//    @Test(dataProvider = "Logging")
//    public void getIssueLog(String date, String time, String storyID, String description) {
//
//        baseURI = PropertiesReader.Data.getProperty("URL");
//        LoginResponse lr = given().relaxedHTTPSValidation()
//                .header("Content-Type", "application/json")
//                .pathParam("storyID", storyID)
//                .expect().defaultParser(Parser.JSON)
//                .when().get("/rest/api/2/issue/{storyID}/worklog")
//                .then().log().body()
//                .assertThat().statusCode(200)
//                .and().extract().response().as(LoginResponse.class);
//    }

    @Test(dataProvider = "Logging")
    public void deleteWorkLog(ITestContext context, String date, String time, String storyID, String description) {
        String workLogID= (String) context.getAttribute("workLogID");
        baseURI = PropertiesReader.Data.getProperty("URL");
        given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Cookie", token)
                .pathParam("storyID", storyID)
                .pathParam("id", workLogID)
                .when().delete("/rest/api/2/issue/{storyID}/worklog/{id}")
                .then().log().body()
                .assertThat().statusCode(204);
    }
}
