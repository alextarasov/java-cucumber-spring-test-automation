package generalfunctionality.springelements.components;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ScenarioScope
public class RestApiResponsesData {
    private Map<String, Object> addANewPostResponse;

    public Map<String, Object> getAddANewPostResponse() {
        return addANewPostResponse;
    }

    public void setAddANewPostResponse(Map<String, Object> addANewPostResponse) {
        this.addANewPostResponse = addANewPostResponse;
    }
}
