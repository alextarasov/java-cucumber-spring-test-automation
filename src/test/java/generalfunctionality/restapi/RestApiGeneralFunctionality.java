package generalfunctionality.restapi;

import static io.restassured.RestAssured.*;

import generalfunctionality.restapi.objects.UserNameAndPasswordForToken;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RestApiGeneralFunctionality {

    @Value("${base.uri}")
    private String baseUri;

    @Autowired
    private UserNameAndPasswordForToken userNameAndPasswordForToken;

    private String token;

    public void requestToken() {
        Response response = given().contentType("application/json")
                .body(userNameAndPasswordForToken)
                .when().
                        post(baseUri + "/jwt-auth/v1/token");
        response.then().
                log().ifError().statusCode(200);
        Map<String, Object> body =
                response.as(new TypeRef<Map<String, Object>>() {
                });
        token = (String) body.get("token");
    }

    public String getToken() {
        return token;
    }
}
