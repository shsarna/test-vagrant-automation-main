# Test Vagrant Automation

## Documentation
* [Introduction](#introduction)
* [Frontend](#frontend)
    - [Lean Page Object Model](#lean-page-object-model)
* [Backend](#backend)
* [Tech Stack](#tech-stack)
* [Test Case Design Approach](#test-case-design-approach)
    - [UI Sample](#ui-sample)
    - [API Sample](#api-sample)
* [Reporting](#reporting)
* [Running the build](#running-the-build)
* [Additional Information](#additional-information)
* [Further Reading](#further-reading)
* [Conclusion](#conclusion)

## Introduction
Setting up a repo for the framework I'm designing as part of test vagrant's assignment.
The idea is to build a framework that would adhere to the principles of Java and Test Automation. This will be built off 
of the Page Object Model for the frontend UI validation and RestAssured for backend validation.

## Frontend
The idea is to overcome the common pitfalls of the page object model, by which I mean, issues like problems with scaling up as the application becomes more complex.
Also, modern web pages are so complex maintaining all objects in 1:1 Pages (i.e. a Page class for a page in the website) becomes very tedious.

Due to the vast number of objects in a page, they become very bloated and hard to maintain.

### Lean Page Object Model
For this I followed the **Lean Page Object Model**. By dividing the Pages by components instead of actual pages in the website the problems I mentioned above are now largely reduced.
In addition to this, I also separated the business logic from the page objects. The Page classes should effectively act as APIs that are consumed by the Action classes 
which is where the business logic is implemented.

Having these classes allows for easier and more maintainable interactions between the different page component objects.
The test classes will then use the objects of the aforementioned action classes to define the flow of the tests.

## Backend
To test the backend services, I followed the Service Oriented model, which similar to the page object model will have a page dedicated to each service. This allows for neater, more readable and more maintainable test code.
The end point URLs will be stored in a configuration file.
That being said, one of the items I implemented in the framework that our team built in my company is a configurable yaml file which will act as a repository of sorts for all APIs.
This was not implemented in the test framework owing to two reasons. The first being, given that I only have to play with one endpoint, designing this would be an overkill, and I'd needlessly be over engineering it to a fault.
The second reason was a lack of time.

## Tech Stack
- Java 
- Selenium for UI
- Rest Assured for backend
- Allure for reporting
- Gradle for build management
- A customized SoftAssert Wrapper for assertion
- Jackson APIs to process JSON files

## Test Case Design Approach
We can add scenarios for API and UI layers in their respective JSON files, *WeatherAPIScenarios.json* and *WeatherComparisonScenario.json*.
This activity requires no coding effort beyond what has been done for developing the framework and just requires passing data in the form of JSON objects.

### UI Sample
```
  {
    "scenarioName": <Test case name>,
    "scenarioDescription": <Test description>,
    "execution": <ON/OFF>,
    "city": <City name>,
    "condition": <Humidity/ Temp in Degrees/ Temp in Fahrenheit/ Wind>,
    "variationAllowed": <variation value>
  }
``` 
### API Sample
```
  {
    "scenarioName": <Test case name>,
    "scenarioDescription": <Test description>,
    "execution": <ON/OFF>,
    "city": <City name>,
    "passAccessToken": <true/false>,
    "unitMeasurement": <METRIC/ IMPERIAL/ STANDARD>,
    "expectedResponse": <401/404/200>
  }
```
I have done some basic error handling that being said if provided with the privilege of time error handling could've been handled a lot better.

## Reporting
I used allure as the reporting tool of my choice. In addition to the vanilla features it offers I have used the Allure api to make some addons, like, attaching a screenshot on assertion failure, 
adding a custom step, among other things.
Allure allows for seamless integration with jenkins and requires little coding overhead.
To generate allure report, after execution, run the below command in the project location:
```
allure generate allure-results --clean
```
Note: Please install allure before generating results. 

Note2: In some machines, I'm facing a weird issue where the attachments and step methods are missing from the run report. This is a known issue and I'm trying to find a way around it.
I realised this problem far too late into development, would have used Extent reporting instead. That being said, I still stand by my belief that Allure is a great reporting tool.

## Running the build
I have used gradle as the build automation tool of choice. With this, integrating with Jenkins is also extremely easy.
The framework is CI ready and to run the build, merely pass the testng file name as the in the jenkins build script by either using 'Execute Shell' as the build mode or installing the gradle plugin and selecting 'Invoke Gradle Script'.
For the former, run the command:

```
./gradlew --refresh-dependencies clean build test -PtestSuite=<TESTNG_FILE_NAME.xml>
```

For the latter, run the command:
```
test
--refresh-dependencies
-PtestSuite=<TESTNG_FILE_NAME.xml>
```
Note: The tests have been configured so that the classes are running in parallel, i.e. the API tests, and the UI tests run simultaneously. This saves for some time.

With remote execution, we can also aim for parallel execution across tests instead of just classes thereby further reducing execution time.

## Additional Information
Enable annotation-processing for lombok to work smoothly in the IDE of your choice. I used Intellij IDEA.

## Further Reading
The design philosophy for this framework drew inspiration from this blogpost: 

```
https://johnfergusonsmart.com/page-objects-that-suck-less-tips-for-writing-more-maintainable-page-objects/
```

## Conclusion
Designing and developing this framework was fun. There are several areas where I feel like I can make improvements, but given that I was running against a rough deadline, coupled with some hectic work assignments I could only achieve as uch as I did, which admittedly I'm pretty proud of.

I would have gone for a cleaner approach for the CI pipeline where all information required for the testng build would've been picked up as build parameters, but, once again, lacking time I couldn't do it. Ironically I was the one who worked on this very feature for our framework.
I also wish I could add more test cases for negative scenarios. I'll try adding the scenarios after submission regardless.

I also observed that the weather displayed in the NDTV website is highly inaccurate, assuming the weather API's data is correct.
