package cucumbergeneralfunctionality.stepdefinitions;

import generalfunctionality.dataobjects.GuestCommentFormData;
import generalfunctionality.dataobjects.NewPostObjectData;
import io.cucumber.java.en.Then;
import generalfunctionality.mysqlwordpressdbjdbc.WordpressDbJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

public class DatabaseStepDefinitions {

    @Autowired
    private GuestCommentFormData guestCommentFormData;

    @Autowired
    private WordpressDbJdbcTemplate wordpressDbJdbcTemplate;

    @Autowired
    private NewPostObjectData newPostObjectData;

    @Then("New comment data should present in database")
    public void new_comment_data_should_present_in_database() {
        Map<String, String> guestCommentDataMap =
                guestCommentFormData.getGuestCommentDataMap();
        Map<String, String> commentDataFromDb =
                wordpressDbJdbcTemplate.getFirstWpCommentAsAString();
        commentDataFromDb.keySet().retainAll(guestCommentDataMap.keySet());
        assertTrue("Data used for posting the comment does not match " +
                        "the most recent one in the 'wp_comments' table " +
                        "from the database:" + System.lineSeparator() +
                        "Expected: " + guestCommentDataMap.toString() +
                        System.lineSeparator() +
                        "Actual: " + commentDataFromDb.toString()
                , guestCommentDataMap.equals(commentDataFromDb));
    }

    @Then("New post data should present in database")
    public void new_post_data_should_present_in_database() {
        Map<String, String> expectedPostData = newPostObjectData.getNewPostDataMap();
        Map<String, String> firstPostDataFromDb = wordpressDbJdbcTemplate
                .getFirstWpPostAsAString();
        firstPostDataFromDb.keySet().retainAll(expectedPostData.keySet());
        assertTrue("Data used for the new post creation does not match " +
                        "the most recent one in the 'wp_posts' table " +
                        "from the database:" + System.lineSeparator() +
                        "Expected: " + expectedPostData.toString() +
                        System.lineSeparator() +
                        "Actual: " + firstPostDataFromDb.toString()
                , expectedPostData.equals(firstPostDataFromDb));
    }
}
