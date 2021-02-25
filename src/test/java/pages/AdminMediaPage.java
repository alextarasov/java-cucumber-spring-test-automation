package pages;

import generalfunctionality.RobotWrapper;
import generalfunctionality.SeleniumWrapper;
import org.openqa.selenium.By;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMediaPage {

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    @Autowired
    private RobotWrapper robotWrapper;

    private final By addNewButton =
            By.xpath("//a[@class='page-title-action aria-button-if-js']");

    private final By selectFilesButton =
            By.xpath("//button[@class='browser button button-hero']");

    private final By bulkSelectButton =
            By.xpath("//button[@class='button media-button select-mode-toggle-button']");

    private final By deletePermanentlyButton =
            By.xpath("//button[@class='button media-button button-primary " +
                    "button-large delete-selected-button']");

    private final By uploadedFileIcon = By.xpath("//div[@class='thumbnail']");

    public void openAdminMediaPage() {
        seleniumWrapper.openPage("/wp-admin/upload.php");
    }

    public void openAddNewForm() {
        seleniumWrapper.waitThenClick(addNewButton);
    }

    public void uploadFile(String filePath) throws AWTException {
        seleniumWrapper.waitUntilVisible(selectFilesButton);
        WebElement addFile = seleniumWrapper.getDriver().findElement(selectFilesButton);
        addFile.click();
        robotWrapper.attachFile(filePath);
    }

    public String getUploadedFileName() {
        return seleniumWrapper.waitAndGetElementText(uploadedFileIcon);
    }

    public void deleteFile() {
        seleniumWrapper.waitThenClick(bulkSelectButton);
        seleniumWrapper.waitThenClick(uploadedFileIcon);
        seleniumWrapper.waitThenClick(deletePermanentlyButton);
        seleniumWrapper.waitAndAcceptAlert();
    }
}
