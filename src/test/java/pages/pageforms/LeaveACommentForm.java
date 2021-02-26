package pages.pageforms;

import generalfunctionality.dataobjects.GuestCommentFormData;
import generalfunctionality.SeleniumWrapper;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

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
        seleniumWrapper.waitThenFillIn(commentInputField,
                guestCommentFormData.getCommentContent());
        seleniumWrapper.waitThenFillIn(nameInputField,
                guestCommentFormData.getCommentAuthor());
        seleniumWrapper.waitThenFillIn(emailInputField,
                guestCommentFormData.getCommentAuthorEmail());
        seleniumWrapper.waitThenFillIn(websiteInputField,
                guestCommentFormData.getCommentAuthorUrl());
        seleniumWrapper.waitThenClick(submitButton);
        seleniumWrapper.waitUntilVisible(textAfterSubmission);
    }
}
