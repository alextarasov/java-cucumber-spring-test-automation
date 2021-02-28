package cucumbergeneralfunctionality.stepdefinitions;

import generalfunctionality.dataobjects.NewPostObjectData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pages.AdminLoginPage;
import pages.AdminMediaPage;
import pages.HomePage;
import pages.TestPostPage;

import java.awt.*;
import java.util.Map;

import static org.junit.Assert.*;

public class SeleniumStepDefinitions {

    @Autowired
    private HomePage homePage;

    @Autowired
    private TestPostPage testPostPage;

    @Autowired
    private AdminLoginPage adminLoginPage;

    @Autowired
    private AdminMediaPage adminMediaPage;

    @Autowired
    private NewPostObjectData newPostObjectData;

    @And("Open the home page")
    public void i_open_the_home_page() {
        homePage.openHomePage();
    }

    @Given("Open {string} page")
    public void open_page(String string) {
        testPostPage.openTestPostPage();
    }

    @And("Leave new comment as a guest")
    public void leave_new_comment_as_a_guest() {
        testPostPage.postACommentAsAGuest();
    }

    @Given("Login to the admin page")
    public void i_login_to_the_admin_page() {
        adminLoginPage.openAdminLoginPage();
        adminLoginPage.loginToWpAdmin();
    }

    @And("Open admin media page")
    public void open_admin_media_page() {
        adminMediaPage.openAdminMediaPage();
    }

    @And("Upload a csv file")
    public void i_upload_a_csv_file() throws AWTException {
        adminMediaPage.openAddNewForm();
        adminMediaPage.uploadFile("C:\\java-cucumber-spring-test-automation" +
                "\\src\\test\\resources\\testfiles\\test_csv_file.csv");
    }

    @Then("File name on the page should match uploaded")
    public void file_name_on_the_page_should_match_uploaded() {
        String fileNameOnThePage = adminMediaPage.getUploadedFileName();
        assertEquals("File name on the page does not match uploaded one:" +
                System.lineSeparator(), "test_csv_file.csv", fileNameOnThePage);
    }

    @Then("Created post should present as first in the list")
    public void created_post_should_present_as_first_in_the_list() {
        Map<String, String> firstPostDataOnFrontend = homePage.getListOfPostsData().get(0);
        Map<String, String> newPostObjectDataMap = newPostObjectData.getNewPostDataMap();
        newPostObjectDataMap.keySet().retainAll(firstPostDataOnFrontend.keySet());
        assertTrue("Data used for post creation does not match " +
                        "data of the first post on the front-end:" +
                        System.lineSeparator() +
                        "Expected: " + newPostObjectDataMap.toString() +
                        System.lineSeparator() +
                        "Actual: " + firstPostDataOnFrontend.toString()
                , firstPostDataOnFrontend.equals(newPostObjectDataMap));
    }
}
