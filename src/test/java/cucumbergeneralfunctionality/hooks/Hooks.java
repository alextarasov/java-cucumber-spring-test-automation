package cucumbergeneralfunctionality.hooks;

import generalfunctionality.SeleniumWrapper;
import generalfunctionality.dataobjects.GuestCommentFormData;
import generalfunctionality.mysqlwordpressdbjdbc.WordpressDbDataSource;
import generalfunctionality.mysqlwordpressdbjdbc.WordpressDbJdbcTemplate;
import generalfunctionality.restapi.RestApiEndpointsMethods;
import generalfunctionality.restapi.RestApiGeneralFunctionality;
import generalfunctionality.springelements.components.RestApiResponsesData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import pages.AdminMediaPage;

import java.io.IOException;

public class Hooks {

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    @Autowired
    private WordpressDbDataSource wordpressDbDataSource;

    @Autowired
    private WordpressDbJdbcTemplate wordpressDbJdbcTemplate;

    @Autowired
    private RestApiGeneralFunctionality restApiGeneralFunctionality;

    @Autowired
    private RestApiEndpointsMethods restApiEndpointsMethods;

    @Autowired
    private RestApiResponsesData restApiResponsesData;

    @Autowired
    private AdminMediaPage adminMediaPage;

    @Autowired
    private GuestCommentFormData guestCommentFormData;

    @Before("@selenium")
    public void seleniumWebDriverSetup(Scenario scenario) throws IOException {
        seleniumWrapper.createAndStartService();
        seleniumWrapper.setUpWebDriver();
    }

    @After(order = 0, value = "@selenium")
    public void seleniumWebDriverTeardown(Scenario scenario) {
        seleniumWrapper.driverTearDown();
        seleniumWrapper.getService().stop();
    }

    @After(order = 1, value = "@selenium")
    public void takeAndAttachScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            seleniumWrapper.takeAndAttachScreenshot(scenario);
        }
    }

    @Before("@mysql")
    public void databaseConnectionSetup(Scenario scenario) {
        wordpressDbDataSource.setUpDataSource();
        wordpressDbJdbcTemplate
                .setDataSource(wordpressDbDataSource.getWordPressDbDataSource());
    }

    @After("@delete_posted_comment_from_db_after_test")
    public void deletePostedCommentFromDb(Scenario scenario) {
        String commentAuthorEmail = guestCommentFormData
                .getGuestCommentDataMap().get("comment_author_email");
        String errorMessage = "Entry with 'comment_author_email' equals " + commentAuthorEmail
                + " does not present in the 'wp_comments' table of the database";
        int deleteExecutionResult = wordpressDbJdbcTemplate
                .deleteByCommentAuthorEmail(commentAuthorEmail);
        assertEquals(errorMessage, 1, deleteExecutionResult);
    }

    @After("@delete_uploaded_file_after_test")
    public void deleteUploadedFile(Scenario scenario) {
        adminMediaPage.deleteFile();
    }

    @Before("@obtain_rest_api_token")
    public void obtainRestApiToken(Scenario scenario) {
        restApiGeneralFunctionality.requestToken();
    }

    @After("@delete_created_post_via_rest_api_after_test")
    public void deleteCreatedPostViaRestApi(Scenario scenario) {
        restApiEndpointsMethods
                .deletePostById(restApiResponsesData
                        .getAddANewPostResponse().get("id")
                        .toString());
    }
}
