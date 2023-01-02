package selenium.playground;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	protected WebDriver driver;

	@Parameters("Browser")
	@BeforeMethod
	public void setUp(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().browserVersion("107").clearDriverCache().setup();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);

		} else {

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().clearDriverCache().setup();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new FirefoxDriver(firefoxOptions);

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("http://timvroom.com/selenium/playground/");
	}

	@AfterMethod
	public void tearDown() {

		System.out.println(
				"It seems that driver.close and driver.quit are sometimes generating a Connection reset (SocketException) issue, which is why i decided to leave the browser open after test completion");

		// driver.close();

	}

	@AfterSuite
	public void afterSuite() {

		System.out.println("\nA bientot");
	}

}
