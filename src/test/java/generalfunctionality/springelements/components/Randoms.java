package generalfunctionality.springelements.components;

import io.cucumber.spring.ScenarioScope;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
public class Randoms {

    private final Integer stringLength = 20;

    public String generateRandomString() {
        return RandomStringUtils
                .randomAlphabetic(stringLength);
    }

    public String generateRandomEmailAddress() {
        return generateStringInLowerCase() +
                "@" + generateStringInLowerCase() + ".com";
    }

    public String generateRandomWebSiteAddress() {
        return "https://www." + generateStringInLowerCase() + ".com";
    }

    public String generateStringInLowerCase() {
        return RandomStringUtils
                .randomAlphabetic(stringLength).toLowerCase();
    }
}
