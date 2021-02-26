package pages;

import generalfunctionality.SeleniumWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pages.pageforms.LeaveACommentForm;

@Component
public class TestPostPage extends LeaveACommentForm {

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    public void openTestPostPage() {
        seleniumWrapper.openPage("/2021/02/06/test-post/");
    }
}
