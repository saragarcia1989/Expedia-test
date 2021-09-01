
Documentation
-------------

This project contains an automated test for the website www.expedia.com. It is runnable in Chrome and Firefox, and
has the following scenario:

1.	Go to site http://www.expedia.com
2.	Select "Flights"
3.	Type London in "Leaving from"
4.	Select Heathrow in popup
5.	Type Dublin in "Going to"
6.	Select "Dublin Airport (DUB), Ireland" in popup
7.	Select Departing: 20/10/2021
8.	Select Departing: 31/10/2021
9.	Select 2 adults
10.	Click Search Button      
11.	Wait for all flights to be loaded. 


Execution
-------------
```
mvn -Dtest=FlightsTest test
```