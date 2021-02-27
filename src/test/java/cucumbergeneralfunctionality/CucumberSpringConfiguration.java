package cucumbergeneralfunctionality;

import generalfunctionality.springelements.configurations.SpringConfiguration;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = {
        SpringConfiguration.class
})
public class CucumberSpringConfiguration {

}
