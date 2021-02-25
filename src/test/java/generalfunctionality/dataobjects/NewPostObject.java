package generalfunctionality.dataobjects;

import generalfunctionality.springelements.components.Randoms;
import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class NewPostObject {
    private final String title;
    private final String status;
    private final String content;

    public NewPostObject() {
        Randoms randoms = new Randoms();
        title = randoms.generateStringInLowerCase();
        status = "publish";
        content = randoms.generateStringInLowerCase();
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
