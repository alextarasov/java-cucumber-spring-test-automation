package pages;

import generalfunctionality.SeleniumWrapper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminLoginPage {

    @Value("${wordpress.admin.username}")
    private String username;

    @Value("${wordpress.admin.password}")
    private String password;

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    private final By usernameOrEmailAddressField = By.xpath("//input[@id='user_login']");
    private final By passwordInputField = By.xpath("//input[@id='user_pass']");
    private final By logInButton = By.xpath("//input[@id='wp-submit']");
    private final By homePageDashboardText = By.xpath("//h1[text()='Dashboard']");

    public void openAdminLoginPage() {
        seleniumWrapper.openPage("/wp-login.php");
    }

    public void loginToWpAdmin() {
        seleniumWrapper.waitThenFillIn(usernameOrEmailAddressField, username);
        seleniumWrapper.waitThenFillIn(passwordInputField, password);
        seleniumWrapper.waitThenClick(logInButton);
        seleniumWrapper.waitUntilVisible(homePageDashboardText);
    }
}
