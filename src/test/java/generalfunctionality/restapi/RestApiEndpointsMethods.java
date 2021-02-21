package generalfunctionality.restapi;

import static io.restassured.RestAssured.*;

import generalfunctionality.restapi.objects.NewPostObject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RestApiEndpointsMethods {

    @Autowired
    private RestApiGeneralFunctionality restApiGeneralFunctionality;

    @Value("${base.uri}")
    private String baseUri;

    public Map<String, Object> addANewPost(NewPostObject newPostObject) {
        return given().headers(
                "Authorization",
                "Bearer " + restApiGeneralFunctionality.getToken(),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .body(newPostObject).when()
                .post(baseUri + "/wp/v2/posts").then().
                        log().ifError().statusCode(201).extract().response().
                        as(new TypeRef<Map<String, Object>>() {
                        });
    }

    public Map<String, Object> deletePostById(String postId) {
        return given().headers(
                "Authorization",
                "Bearer " + restApiGeneralFunctionality.getToken(),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).when()
                .delete(baseUri + "/wp/v2/posts/" + postId).then().
                        log().ifError().statusCode(200).extract().response().
                        as(new TypeRef<Map<String, Object>>() {
                        });
    }
}
