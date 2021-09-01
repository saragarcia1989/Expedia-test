package base;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightsPage;
import pages.FlightsResultsPage;

public class FlightsTest extends BaseTests {

    @Test
    public void FlightsTest() {

        FlightsPage flightsPage = homePage.clickFlightButton();

        flightsPage.insertDeparture("London");
        flightsPage.selectAirport("Heathrow");
        flightsPage.insertDestination("Dublin");
        flightsPage.selectAirport("Dublin");
        flightsPage.selectDepartureDate("Oct 20, 2021");
        flightsPage.selectReturningDate("Oct 31, 2021");
        flightsPage.selectTravelers();

        FlightsResultsPage flightsResultsPage = flightsPage.clickSearchButton();
        flightsResultsPage.waitToLoadThePage();

        String flightPrice = flightsResultsPage.getFlightPrices().get(0);

//        /**
//         * Assert Positive scenario that the price in first row is $142(or any other price at your time).
//         */
//        Assert.assertEquals(flightPrice, "$160", ">>>Different price<<<");


        /**
         * Assert Negative scenario that the price in first row is not $22
         */
        Assert.assertNotEquals(flightPrice, "$22", ">>>Same price<<<");


        /**
         * Assert the visible left panel list of “Airlines included” below the list of “Stops”
         */
        Assert.assertTrue(flightsResultsPage.isAirlinesListBelowStopsList(), ">>>Airlines list is not below stops list<<<");


        /**
         * Scroll to the bottom of the page and assert the visibility of text “© 2021 Expedia, Inc., an Expedia Group company. All rights reserved. (Or any other text at your time.)”
         */
        flightsResultsPage.scrollToBottom();

        Assert.assertTrue(flightsResultsPage.getBottomText().contains("© 2021 Expedia, Inc., an Expedia Group company. All rights reserved."),
                ">>>Different text<<<");
    }

}
