package pages.pageforms;

import generalfunctionality.dataobjects.GuestCommentFormData;
import generalfunctionality.SeleniumWrapper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class LeaveACommentForm {

    @Autowired
    private GuestCommentFormData guestCommentFormData;

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    private final By commentInputField = By.xpath("//textarea[@id='comment']");
    private final By nameInputField = By.xpath("//input[@id='author']");
    private final By emailInputField = By.xpath("//input[@id='email']");
    private final By websiteInputField = By.xpath("//input[@id='url']");
    private final By submitButton = By.xpath("//input[@id='submit']");
    private final By textAfterSubmission =
            By.xpath("//p[text()='Your comment is awaiting moderation."
                    + " This is a preview; your comment will be visible after" +
                    " it has been approved.']");

    public void postACommentAsAGuest() {
        Map<String, String> guestCommentDataMap =
                guestCommentFormData.getGuestCommentDataMap();
        seleniumWrapper.waitThenFillIn(commentInputField,
                guestCommentDataMap.get("comment_content"));
        seleniumWrapper.waitThenFillIn(nameInputField,
                guestCommentDataMap.get("comment_author"));
        seleniumWrapper.waitThenFillIn(emailInputField,
                guestCommentDataMap.get("comment_author_email"));
        seleniumWrapper.waitThenFillIn(websiteInputField,
                guestCommentDataMap.get("comment_author_url"));
        seleniumWrapper.waitThenClick(submitButton);
        seleniumWrapper.waitUntilVisible(textAfterSubmission);
    }
}
