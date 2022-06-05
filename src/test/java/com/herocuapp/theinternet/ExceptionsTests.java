package com.herocuapp.theinternet;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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

public class ExceptionsTests {
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
			System.out.println("Do not know how to start " + browser + "starting chrome instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		// maximize browser window
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void notVisibleTest() {

		System.out.println("Starting notVisibleTest");

		String url = "https://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		System.out.println("Page is opened");
		WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

		WebElement finishElement = driver.findElement(By.xpath("//div[@id='finish']/h4"));
		String expectedText = "Hello World!";
		String finishText = finishElement.getText();

		Assert.assertTrue(finishText.contains(expectedText),
				"Actual message does not contain expected message.\nActualMessage: " + finishText + "\nExpected Message"
						+ expectedText);

	}

	@Test(priority = 2)
	public void timeoutTest() {

		System.out.println("Starting notVisibleTest");

		String url = "https://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		System.out.println("Page is opened");
		WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		} catch (TimeoutException e) {
			System.out.println("Exception catched: " + e.getMessage());
			sleep(3000);
		}

		WebElement finishElement = driver.findElement(By.xpath("//div[@id='finish']/h4"));
		String expectedText = "Hello World!";
		String finishText = finishElement.getText();

		Assert.assertTrue(finishText.contains(expectedText),
				"Actual message does not contain expected message.\nActualMessage: " + finishText + "\nExpected Message"
						+ expectedText);

	}

	@Test(priority = 3)
	public void noSuchElementTest() {

		System.out.println("Starting noSuchElementTest");
		String url = "https://the-internet.herokuapp.com/dynamic_loading/2";
		driver.get(url);
		System.out.println("Page is opened");
		WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
				"Could not verife expected text");

//		WebElement finishElement =wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
//		String expectedText = "Hello World!";
//		String finishText = finishElement.getText();
//		Assert.assertTrue(finishText.contains(expectedText),
//				"Actual message does not contain expected message.\nActualMessage: " + finishText + "\nExpected Message"
//						+ expectedText);

	}

	@Test
	public void staleElementTest() {

		System.out.println("Starting staleElementTest");

		driver.get("https://the-internet.herokuapp.com/dynamic_controls");
		System.out.println("Page is opened");

		WebElement checkbox = driver.findElement(By.id("checkbox"));

		WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(),'Remove')]"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		removeButton.click();

//		wait.until(ExpectedConditions.invisibilityOf(checkbox));
//		Assert.assertFalse(checkbox.isDisplayed(), "Checkbox is visible");

		// option 1 for assert
		// Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)),
		// "Checkbox is visible, but it should not be");

		// option 2 for assert
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)),
				"Checkbox is visible, but it should not be");

		WebElement addButton = driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
		addButton.click();
		checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
		Assert.assertTrue(checkbox.isDisplayed(), "Checkbox is not visible, but it should be");
	}

	@Test
	public void disabledElementTest() {

		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(),'Enable')]"));
		WebElement textField = driver.findElement(By.xpath("input[@type='text']"));
		enableButton.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(textField));
		textField.sendKeys("I can type!");
		Assert.assertEquals(textField.getAttribute("value"), "I can type!");

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
