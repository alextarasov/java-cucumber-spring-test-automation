package generalfunctionality.dataobjects;

import generalfunctionality.springelements.components.Randoms;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class NewPostObjectData {
    private final Map<String, String> newPostDataMap;

    public NewPostObjectData() {
        Randoms randoms = new Randoms();
        newPostDataMap = new HashMap<String, String>();
        newPostDataMap.put("post_title", randoms.generateStringInLowerCase());
        newPostDataMap.put("post_status", "publish");
        newPostDataMap.put("post_content", randoms.generateRandomEmailAddress());
    }

    public Map<String, String> getNewPostDataMap() {
        return newPostDataMap;
    }
}
