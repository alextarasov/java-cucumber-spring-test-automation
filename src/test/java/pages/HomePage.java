package pages;

import generalfunctionality.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HomePage {

    @Autowired
    private SeleniumWrapper seleniumWrapper;

    private final By postsBlocks = By.xpath("//article");
    private final By blockWithAPostsSelector = By.xpath("//*[@class='site-main']");

    public void openHomePage() {
        seleniumWrapper.openHomePage();
    }

    public List<Map<String, String>> getListOfPostsData() {
        WebElement blockWithAPosts = seleniumWrapper.getDriver()
                        .findElement(blockWithAPostsSelector);
        List<WebElement> postsObjects = blockWithAPosts.findElements(postsBlocks);
        List<Map<String, String>> listOfPostsData =
                new ArrayList<Map<String, String>>();

        for (int i = 0; i < postsObjects.size(); i++) {
            Map<String, String> post = new HashMap<>();
            post.put("post_title", blockWithAPosts
                    .findElements(By.xpath("//article//h2")).get(i).getText());
            post.put("post_content", blockWithAPosts
                    .findElements(By.xpath("//article//p")).get(i).getText());
            listOfPostsData.add(post);
        }

        return listOfPostsData;
    }
}
