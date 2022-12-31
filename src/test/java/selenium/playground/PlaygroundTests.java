package selenium.playground;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class PlaygroundTests extends Base {

	By answerSlot1 = By.id("answer1");
	By nameSlot = By.id("name");
	By sciFiAuthor = By.cssSelector("select[id='occupation'] option[value='scifiauthor']");
	By answerSlot4 = By.id("answer4");
	By blueBox = By.cssSelector("span[class='bluebox']");
	By clickMe = By.linkText("click me");
	By redBox = By.id("redbox");
	By answerSlot8 = By.id("answer8");
	By wroteBook = By.cssSelector("form[id='testform'] input[value='wrotebook']");
	By answerSlot6 = By.id("answer6");
	By answerSlot10 = By.id("answer10");
	By topBox = By.xpath("(//script[@type='text/javascript'])[4]");
	By answerSlot11 = By.id("answer11");
	By idAvailability = By.cssSelector("div[id='ishere']");
	By answerSlot13 = By.id("answer13");
	By purpleBoxVisible = By.id("purplebox");
	By answerSlot14 = By.id("answer14");
	By clickThenWait = By.linkText("click then wait");
	By clickAfterWait = By.linkText("click after wait");
	By submit = By.name("submit");
	By checkResultsButton = By.id("checkresults");
	By result = By.id("showresults");
	By bottomDIV = By.id("bottom");
	By topHead = By.id("tophead");

	@Test()
	public void playgroundTests() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1- Grab page title and place title text in answer slot #1
		String title = driver.getTitle();
		type(answerSlot1, title);

		// 2- Fill out name section of form to be Kilgore Trout
		// String name = "Kilgore Trout";
		type(nameSlot, "Kilgore Trout");

		// 3- Set occupation on form to Sci-Fi Author
		click(sciFiAuthor);

		// 4- Count number of blue_boxes on page after form and enter into answer box #4
		String stringifiedSize = Integer.toString(finds(blueBox).size());
		type(answerSlot4, stringifiedSize);

		// 5- Click link that says 'click me'
		click(clickMe);

		// 6- Find red box on its page find class applied to it, and enter into answer
		// box #6
		String redBoxClass = find(redBox).getAttribute("class");
		type(answerSlot6, redBoxClass);

		// 7- Run JavaScript function as: ran_this_js_function() from your Selenium
		// script
		js.executeScript("ran_this_js_function();");

		// 8- Run JavaScript function as: got_return_from_js_function() from your
		// Selenium script, take returned value and place it in answer slot #8
		String returnedValue = js.executeScript("return got_return_from_js_function();").toString();
		type(answerSlot8, returnedValue);

		// 9- Mark radio button on form for written book? to Yes
		click(wroteBook);

		// 10- Get the text from the Red Box and place it in answer slot #10
		String text = getText(redBox);
		type(answerSlot10, text);

		// 11- Which box is on top? orange or green -- place correct color in answer
		// slot #11
		String boxColor = js.executeScript("return box_order[0];", find(topBox)).toString();
		type(answerSlot11, boxColor);

		// 12- Set browser width to 850 and height to 650
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(850, 650));

		// 13- Type into answer slot 13 yes or no depending on whether item by id of
		// ishere is on the page
		if (finds(idAvailability).size() > 0) {

			type(answerSlot13, "yes");
		}

		else {

			type(answerSlot13, "no");

		}

		// 14- Type into answer slot 14 yes or no depending on whether item with id of
		// purplebox is visible
		if (find(purpleBoxVisible).getAttribute("style").contains("none")) {

			type(answerSlot14, "no");
		} else {

			type(answerSlot14, "yes");
		}
		;

		// 15- Waiting game: click the link with link text of 'click then wait' a random
		// wait will occur (up to 10 seconds) and then a new link will get added with
		// link text of 'click after wait'
		// click this new link within 500 ms of it appearing to pass this test
		click(clickThenWait);
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(clickAfterWait));
		click(clickAfterWait);

		// 16- Click OK on the confirm after completing task 15
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// 17- Click the submit button on the form
		click(submit);

		// Bonus!- Click Check Results button and Print result on console
		click(checkResultsButton);
		System.out.println(getText(result));

		// scroll to bottom DIV
		js.executeScript("arguments[0].scrollIntoView()", find(bottomDIV));

		// scroll back to top
		js.executeScript("arguments[0].scrollIntoView()", find(topHead));
		Thread.sleep(5000);

	}

	private WebElement find(By locator) {

		return driver.findElement(locator);
	}

	private List<WebElement> finds(By locator) {

		return driver.findElements(locator);
	}

	private void click(By locator) {

		find(locator).click();
	}

	private void type(By locator, String text) {

		find(locator).sendKeys(text);
	}

	private String getText(By locator) {

		String text = find(locator).getText();
		return text;
	}
}
