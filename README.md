# playground Java

Just playing around with **Selenium WebDriver Java** using [timvroom playground](http://timvroom.com/selenium/playground/). The test will run the below test steps taken from the playground page.

1. Grab page title and place title text in answer slot #1
2. Fill out name section of form to be Kilgore Trout
3. Set occupation on form to Sci-Fi Author
4. Count number of blue_boxes on page after form and enter into answer box #4
5. Click link that says 'click me'
6. Find red box on the page, find its class, and enter it into answer box #6
7. Run JavaScript function as: ran_this_js_function() from your Selenium script
8. Run JavaScript function as: got_return_from_js_function() from your Selenium script, take returned value and place it in answer slot #8
9. Mark radio button on form for written book? to Yes
10. Get the text from the Red Box and place it in answer slot #10
11. Which box is on top? orange or green -- place correct color in answer slot #11
12. Set browser width to 850 and height to 650
13. Type into answer slot 13 yes or no depending on whether item by id of ishere is on the page
14. Type into answer slot 14 yes or no depending on whether item with id of purplebox is visible
15. Waiting game: click the link with link text of 'click then wait' a random wait will occur (up to 10 seconds) and then a new link will get added with link text of 'click after wait' - click this new link within 500 ms of it appearing to pass this test
16. Click OK on the confirm after completing task 15
17. Click the submit button on the form

### Getting Started
To run the test on your local machine parallelly on both Chrome and Firefox browsers:

- Download and install [Java](https://www.oracle.com/java/technologies/downloads/).
- Download and install [Maven](https://maven.apache.org/download.cgi).
- Set Java and Maven User variables.
- Clone the Repo to your local machine.
- From the project root directory (i.e cd-ing into .../playground), run the following command
```
mvn test -PParallel
```
note: 
- driver.close and driver.quit are sometimes generating a SocketException, Connection reset unexpected issue, so i decided to leave the browser open after test completion to prevent the error logs.
- Firefox browser will leave a bunch of logs on the console; so just discard them!

Thanks!