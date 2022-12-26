package selenium.playground;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

		driver.get("http://timvroom.com/selenium/playground/");

		String title = driver.getTitle();

		driver.findElement(By.id("answer1")).sendKeys(title);

		Thread.sleep(3000);

		driver.quit();

	}

}
