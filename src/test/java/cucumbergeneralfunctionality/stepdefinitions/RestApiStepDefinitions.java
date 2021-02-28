package cucumbergeneralfunctionality.stepdefinitions;

import generalfunctionality.dataobjects.NewPostObjectData;
import generalfunctionality.restapi.RestApiEndpointsMethods;
import generalfunctionality.restapi.objects.NewPostObject;
import generalfunctionality.springelements.components.RestApiResponsesData;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RestApiStepDefinitions {

    @Autowired
    private NewPostObjectData newPostObjectData;

    @Autowired
    private RestApiEndpointsMethods restApiEndpointsMethods;

    @Autowired
    private RestApiResponsesData restApiResponsesData;

    @Given("Creating new post using rest api")
    public void creating_new_post_using_rest_api() {
        Map<String, String> newPostObjectDataMap =
                newPostObjectData.getNewPostDataMap();
        NewPostObject newPostObject = new NewPostObject();
        newPostObject.setTitle(newPostObjectDataMap.get("post_title"));
        newPostObject.setStatus("publish");
        newPostObject.setContent(newPostObjectDataMap.get("post_content"));
        Map<String, Object> response =
                restApiEndpointsMethods.addANewPost(newPostObject);
        restApiResponsesData.setAddANewPostResponse(response);
    }
}
