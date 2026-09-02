package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.abstractPages.AuthenticatedBasePage;
import utils.ElementActions;

public class BuzzPage extends AuthenticatedBasePage {

    private static final Logger logger =
            LoggerFactory.getLogger(BuzzPage.class);

    /*
     * Buzz navigation menu
     */
    private final By buzzMenu =
            By.xpath("//a[contains(@href,'/buzz/viewBuzz')]");

    /*
     * Feed text area
     */
    private final By feedTextArea =
            By.cssSelector(
                    "textarea[placeholder=\"What's on your mind?\"]"
            );

    /*
     * Post button
     */
    private final By postButton =
            By.xpath("//button[normalize-space()='Post']");

    /*
     * Top/latest feed post content
     */
    private final By topFeedPostContent =
            By.xpath(
                    "(//div[contains(@class,'orangehrm-buzz-post-body')])[1]//p[contains(@class,'orangehrm-buzz-post-body-text')]"
            );

    public BuzzPage clickBuzz() {

        logger.info("Navigating to Buzz");

        ElementActions.click(buzzMenu);

        return this;
    }

    public BuzzPage enterFeedContent(String content) {

        logger.info(
                "Entering feed content: {}",
                content
        );

        ElementActions.type(
                feedTextArea,
                content
        );

        return this;
    }

    public BuzzPage clickPost() {

        logger.info("Clicking Post button");

        ElementActions.click(postButton);

        return this;
    }

    public BuzzPage createFeed(String content) {

        enterFeedContent(content)
                .clickPost();

        return this;
    }

    public String getTopFeedContent() {

		/*
		 * try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
        String actualContent =
                ElementActions.getText(topFeedPostContent);

        logger.info(
                "Top feed content: {}",
                actualContent
        );

        return actualContent;
    }
}