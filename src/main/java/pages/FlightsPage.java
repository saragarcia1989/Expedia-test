package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightsPage {

    private WebDriver driver;

    private By leavingFromSpace = By.id("location-field-leg1-origin-menu");
    private By inputLeavingFrom = By.cssSelector("input[id = 'location-field-leg1-origin']");
    private By airports = By.xpath("//ul/li/button/div/div/span/strong");
    private By destinationSpace = By.xpath("//div[contains(@id, 'location-field-leg1-destination-menu')]");
    private By inputDestination = By.cssSelector("input[id = 'location-field-leg1-destination']");
    private By departingDate = By.cssSelector("button[id = 'd1-btn']");
    private By travelersButton = By.id("adaptive-menu");
    private By searchButton = By.cssSelector("button[data-testid = 'submit-button']");


    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertDeparture(String leavingFrom) {

        driver.findElement(leavingFromSpace).click();
        driver.findElement(inputLeavingFrom).sendKeys(leavingFrom);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(airports));
    }

    public void insertDestination(String destination) {

        driver.findElement(destinationSpace).click();
        driver.findElement(inputDestination).sendKeys(destination);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectAirport(String airportName) {

        List<WebElement> listOfAirports = driver.findElements(airports);

        for (WebElement airport : listOfAirports) {
            if (airport.getText().contains(airportName)) {
                airport.click();
            }
        }
    }

    /**
     * @param date date format must be for example "Sep 2, 2021"
     */

    public void selectDepartureDate(String date) {

        driver.findElement(departingDate).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement departingDateChosen = driver.findElement(By.xpath("//tr/td/button[contains(@aria-label, '" + date + "')]"));
        departingDateChosen.click();

    }

    /**
     * @param date date format must be for example "Sep 2, 2021"
     */

    public void selectReturningDate(String date) {

        WebElement returningDateChosen = driver.findElement(By.xpath("//tr/td/button[contains(@aria-label, '" + date + "')]"));
        returningDateChosen.click();
        WebElement doneButton = driver.findElement(By.xpath("//span[text()='Save changes and close the date picker.']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doneButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(doneButton).click(doneButton).perform();

    }

    public void selectTravelers() {

        driver.findElement(travelersButton).click();

        WebElement increaseAdultsNumberButton = driver.findElement(By.cssSelector("title[id*='increase-adults']"));
        WebElement element = increaseAdultsNumberButton.findElement(By.xpath("./.."));

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.click(element).build().perform();

        WebElement travelersDoneButton = driver.findElement(By.xpath("//button[contains(@data-testid,'guests-done-button')]"));

        actions.moveToElement(travelersDoneButton).click(travelersDoneButton).perform();
    }

    public FlightsResultsPage clickSearchButton() {

        driver.findElement(searchButton).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return new FlightsResultsPage(driver);
    }

}
