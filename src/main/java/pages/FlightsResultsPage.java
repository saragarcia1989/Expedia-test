package pages;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightsResultsPage {

    private WebDriver driver;
    private By flightPrices = By.xpath("//span[contains(@class, 'uitk-lockup-price')]");
    private By filterByList = By.xpath("//fieldset[contains(@class, 'uitk-spacing uitk-spacing-margin-blockend-six')]");
    private By bottomText = By.xpath("//li[contains(@class, 'uitk-flex-item uitk-flex-basis-full_width')]//following-sibling::li");

    public FlightsResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for some seconds to load the page with the results
    public void waitToLoadThePage() {

        driver.manage().timeouts().pageLoadTimeout(7, TimeUnit.SECONDS);

    }

    /**
     * @return the list with the prices of the flights
     */
    public List<String> getFlightPrices() {

        List<String> list = new ArrayList<>();

        List<WebElement> prices = driver.findElements(flightPrices);

        for (WebElement price : prices) {
            String priceText = price.getText();
            list.add(priceText);
        }

        return list;
    }

    /**
     * @return This method returns a boolean comparing the index of two web elements to check if one is below the other.
     */

    public boolean isAirlinesListBelowStopsList() {

        List<WebElement> list = driver.findElements(filterByList);

        int stopsIndex = 0;
        int airlinesIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains("Stops")) {
                stopsIndex = i;
            }
            if (list.get(i).getText().contains("Airlines")) {
                airlinesIndex = i;
            }
        }

        return stopsIndex < airlinesIndex;

    }


    /**
     * Scrolls to the bottom of the page
     */
    public void scrollToBottom() {

        String script = "window.scrollTo(0, document.body.scrollHeight)";

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(script);

    }

    /**
     *
     * @return the text from the bottom of the page
     */

    public String getBottomText() {

        String text = driver.findElement(bottomText).getText();

        return text;
    }

}
