package utils;

import dataReaders.PropertiesReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class ReqSpec {

    static RequestSpecBuilder rs=new RequestSpecBuilder();
    public static RequestSpecification req(){
        rs.setBaseUri(PropertiesReader.Data.getProperty("URL"))
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .build();
        return (RequestSpecification) rs;
    }
}
