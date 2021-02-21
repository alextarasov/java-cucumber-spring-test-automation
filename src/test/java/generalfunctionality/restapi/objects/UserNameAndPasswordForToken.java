package generalfunctionality.restapi.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class UserNameAndPasswordForToken {

    @Value("${wordpress.admin.username}")
    private String username;

    @Value("${wordpress.admin.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
