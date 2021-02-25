package generalfunctionality.springelements.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("selenium.configuration.properties")
@PropertySource("wordpress.credentials.properties")
@PropertySource("rest.api.configuration.properties")
@ComponentScan(basePackages = {"generalfunctionality", "pages"})
public class SpringConfiguration {

}
