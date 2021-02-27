package generalfunctionality.dataobjects;

import generalfunctionality.springelements.components.Randoms;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ScenarioScope
public class GuestCommentFormData {
    private final Map<String, String> guestCommentDataMap;

    public GuestCommentFormData() {
        Randoms randoms = new Randoms();
        guestCommentDataMap = new HashMap<String, String>();
        guestCommentDataMap.put("comment_content", randoms.generateStringInLowerCase());
        guestCommentDataMap.put("comment_author", randoms.generateStringInLowerCase());
        guestCommentDataMap.put("comment_author_email", randoms.generateRandomEmailAddress());
        guestCommentDataMap.put("comment_author_url", randoms.generateRandomWebSiteAddress());
    }

    public Map<String, String> getGuestCommentDataMap() {
        return guestCommentDataMap;
    }
}
