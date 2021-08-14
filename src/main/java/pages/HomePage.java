package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By flightButton = By.xpath("//ul/li/a/span[text()='Flights']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public FlightsPage clickFlightButton() {
        driver.findElement(flightButton).click();
        return new FlightsPage(driver);
    }

}
