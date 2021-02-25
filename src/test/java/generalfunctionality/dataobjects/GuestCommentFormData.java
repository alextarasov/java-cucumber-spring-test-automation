package generalfunctionality.dataobjects;

import generalfunctionality.springelements.components.Randoms;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class GuestCommentFormData {
    private final String commentContent;
    private final String commentAuthor;
    private final String commentAuthorEmail;
    private final String commentAuthorUrl;

    public GuestCommentFormData() {
        Randoms randoms = new Randoms();
        commentContent = randoms.generateStringInLowerCase();
        commentAuthor = randoms.generateStringInLowerCase();
        commentAuthorEmail = randoms.generateRandomEmailAddress();
        commentAuthorUrl = randoms.generateStringInLowerCase();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }
}
