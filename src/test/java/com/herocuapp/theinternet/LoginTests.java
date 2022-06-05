package com.herocuapp.theinternet;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
	private WebDriver driver;
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome ") String browser) {
		// Create driver
switch (browser) {
case "chrome":
	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	driver = new ChromeDriver();
	break;
case "firefox":
	System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	break;
default:
	System.out.println("Do not know how to start "+browser+"starting chrome instead");
	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
	driver = new ChromeDriver();
	break;
}
		
		
		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		// implicit wait
	//	driver.manage().timeouts().implicitlyWait(T); new WebDriverWait(driver, Duration.ofSeconds(10);

	}

	@Test(priority = 1, groups = { "positiveTests", "smokeTests" })
	public void positiveloginTest() {
		System.out.println("Starting loginTest");

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened");

//		enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
//		click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		
		wait.until(ExpectedConditions.elementToBeClickable(logInButton));
		logInButton.click();
		

//		verifications:
//			new Url
		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected");

//			logout button is visible

		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "Log out Button is invisible");

//			succesful login message
		WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActualMessage: " + actualMessage
						+ "\nExpected Message" + expectedMessage);

		sleep(3000);

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 2, groups = { "negativeTests", "smokeTests" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

		System.out.println("Starting negativeLoginTest with " + username + " and " + password);

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("Page is opened");

//	enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);

		// enter password
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.sendKeys(password);

//	click login button
		WebElement logInButton = driver.findElement(By.tagName("button"));
		logInButton.click();

//	verifications:
//		new Url
		String expectedUrl = "https://the-internet.herokuapp.com/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual page URL is not the same as expected");

//		Error flash message
		WebElement errorMessage = driver.findElement(By.id("flash"));

		String actualMessage = errorMessage.getText();
//	Assert.assertEquals(actualMessage, expectedErrorMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedErrorMessage),
				"Actual message does not contain expected message.\nActualMessage: " + actualMessage
						+ "\nExpected Message" + expectedErrorMessage);

	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		// Close browser
		driver.quit();
	}

}
