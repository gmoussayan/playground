package selenium.playground;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaygroundTests {

	@Test
	public void playgroundTests() throws InterruptedException {

		WebDriver driver;
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().clearDriverCache().setup();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		driver = new ChromeDriver(chromeOptions);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("http://timvroom.com/selenium/playground/");

		// 1- Grab page title and place title text in answer slot #1
		String title = driver.getTitle();
		driver.findElement(By.id("answer1")).sendKeys(title);

		// 2- Fill out name section of form to be Kilgore Trout
		driver.findElement(By.id("name")).sendKeys("Kilgore Trout");

		// 3- Set occupation on form to Sci-Fi Author
		driver.findElement(By.cssSelector("option[value='scifiauthor']")).click();

		// 4- Count number of blue_boxes on page after form and enter into answer box #4
		List<WebElement> blueBoxes = driver.findElements(By.cssSelector("span[class='bluebox']"));
		int blueBoxesSize = blueBoxes.size();
		String str = Integer.toString(blueBoxesSize);
		driver.findElement(By.id("answer4")).sendKeys(str);

		// 5- Click link that says 'click me'
		driver.findElement(By.linkText("click me")).click();

		// 6- Find red box on its page find class applied to it, and enter into answer
		// box #6
		WebElement redBox = driver.findElement(By.id("redbox"));
		String className = redBox.getAttribute("class");
		driver.findElement(By.id("answer6")).sendKeys(className);

		// 7- Run JavaScript function as: ran_this_js_function() from your Selenium
		// script
		js.executeScript("ran_this_js_function();");

		// 8- Run JavaScript function as: got_return_from_js_function() from your
		// Selenium script, take returned value and place it in answer slot #8
		String returnedValue = js.executeScript("return got_return_from_js_function();").toString();
		driver.findElement(By.id("answer8")).sendKeys(returnedValue);

		// 9- Mark radio button on form for written book? to Yes
		driver.findElement(By.cssSelector("input[value='wrotebook']")).click();

		// 10- Get the text from the Red Box and place it in answer slot #10
		String redBoxText = redBox.getText();
		driver.findElement(By.id("answer10")).sendKeys(redBoxText);

		// 11- Which box is on top? orange or green -- place correct color in answer
		// slot #11
		WebElement element = driver.findElement(By.xpath("(//script[@type='text/javascript'])[4]"));
		String boxColor = js.executeScript("return box_order[0];", element).toString();

		if (boxColor.equalsIgnoreCase("orange")) {

			driver.findElement(By.id("answer11")).sendKeys(boxColor);

		} else {

			driver.findElement(By.id("answer11")).sendKeys("green");

		}

		// 12- Set browser width to 850 and height to 650
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(850, 650));

		// 13- Type into answer slot 13 yes or no depending on whether item by id of
		// ishere is on the page
		int ishereOrNot = driver.findElements(By.cssSelector("div[id='ishere']")).size();
		if (ishereOrNot > 0) {

			driver.findElement(By.id("answer13")).sendKeys("yes");
		}

		else {

			driver.findElement(By.id("answer13")).sendKeys("no");

		}

		// 14- Type into answer slot 14 yes or no depending on whether item with id of
		// purplebox is visible
		String purpleAvailability = driver.findElement(By.id("purplebox")).getAttribute("style");
		if (purpleAvailability.contains("none")) {

			driver.findElement(By.id("answer14")).sendKeys("no");
		} else {

			driver.findElement(By.id("answer14")).sendKeys("yes");

		}
		;

		// 15- Waiting game: click the link with link text of 'click then wait' a random
		// wait will occur (up to 10 seconds) and then a new link will get added with
		// link text of 'click after wait'
		// click this new link within 500 ms of it appearing to pass this test
		driver.findElement(By.linkText("click then wait")).click();
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("click after wait")));
		driver.findElement(By.linkText("click after wait")).click();

		// 16- Click OK on the confirm after completing task 15
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// 17- Click the submit button on the form
		driver.findElement(By.name("submit")).click();

		// Bonus!- Printing results on console
		driver.findElement(By.id("checkresults")).click();
		String passedTests = driver.findElement(By.id("showresults")).getText();
		System.out.println(passedTests);

		// scroll to bottom DIV
		WebElement bottomDIV = driver.findElement(By.id("bottom"));
		js.executeScript("arguments[0].scrollIntoView()", bottomDIV);

		// scroll back to top
		WebElement topHead = driver.findElement(By.id("tophead"));
		js.executeScript("arguments[0].scrollIntoView()", topHead);

		Thread.sleep(5000);
		driver.quit();

	}

}
