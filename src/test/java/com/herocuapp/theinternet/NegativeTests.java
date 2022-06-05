package com.herocuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {

	
	
	
	
	
	
	/*
	 * @Test(priority=1, groups = { "negativeTests", "smokeTests" }) public void
	 * invalidUserNameTest() {
	 * 
	 * // System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); // WebDriver driver = new
	 * ChromeDriver();
	 * 
	 * System.setProperty("webdriver.gecko.driver",
	 * "src/main/resources/geckodriver.exe"); WebDriver driver = new
	 * FirefoxDriver();
	 * 
	 * // maximize browser window driver.manage().window().maximize();
	 * 
	 * // open test page String url = "https://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("Page is opened");
	 * 
	 * // enter username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("invalidUserName");
	 * 
	 * // enter password WebElement password =
	 * driver.findElement(By.name("password"));
	 * password.sendKeys("SuperSecretPassword!");
	 * 
	 * // click login button WebElement logInButton =
	 * driver.findElement(By.tagName("button")); logInButton.click();
	 * 
	 * // verifications: // new Url String expectedUrl =
	 * "https://the-internet.herokuapp.com/login"; String actualUrl =
	 * driver.getCurrentUrl(); Assert.assertEquals(actualUrl, expectedUrl,
	 * "Actual page URL is not the same as expected");
	 * 
	 * // Error flash message WebElement errorMessage =
	 * driver.findElement(By.id("flash")); String expectedMessage =
	 * "Your username is invalid!"; String actualMessage = errorMessage.getText();
	 * // Assert.assertEquals(actualMessage, expectedMessage,
	 * "Actual message is not the same as expected");
	 * Assert.assertTrue(actualMessage.contains(expectedMessage),
	 * "Actual message does not contain expected message.\nActualMessage: " +
	 * actualMessage + "\nExpected Message" + expectedMessage);
	 * 
	 * // Close browser driver.quit();
	 * 
	 * }
	 */

	
	/*
	 * @Test (priority=2, groups = { "negativeTests"}) public void
	 * invalidPasswordTest () { System.setProperty("webdriver.chrome.driver",
	 * "src/main/resources/chromedriver.exe"); WebDriver driver = new
	 * ChromeDriver();
	 * 
	 * // maximize browser window driver.manage().window().maximize();
	 * 
	 * // open test page String url = "https://the-internet.herokuapp.com/login";
	 * driver.get(url); System.out.println("Page is opened");
	 * 
	 * // enter username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith");
	 * 
	 * // enter password WebElement password =
	 * driver.findElement(By.name("password"));
	 * password.sendKeys("SuperSecretPassword");
	 * 
	 * // click login button WebElement logInButton =
	 * driver.findElement(By.tagName("button")); logInButton.click();
	 * 
	 * // verifications: // new Url String expectedUrl =
	 * "https://the-internet.herokuapp.com/login"; String actualUrl =
	 * driver.getCurrentUrl(); Assert.assertEquals(actualUrl, expectedUrl,
	 * "Actual page URL is not the same as expected");
	 * 
	 * // Error flash message WebElement errorMessage =
	 * driver.findElement(By.id("flash")); String expectedMessage =
	 * "Your password is invalid!"; String actualMessage = errorMessage.getText();
	 * // Assert.assertEquals(actualMessage, expectedMessage,
	 * "Actual message is not the same as expected");
	 * Assert.assertTrue(actualMessage.contains(expectedMessage),
	 * "Actual message does not contain expected message.\nActualMessage: " +
	 * actualMessage + "\nExpected Message" + expectedMessage);
	 * 
	 * // Close browser driver.quit();
	 * 
	 * 
	 * }
	 */
	
	
	
}
