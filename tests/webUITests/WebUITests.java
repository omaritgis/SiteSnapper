package webUITests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webUI.objects.TestObject;

public class WebUITests {
  public static WebDriver driver;
	
  @Test
  @Parameters("browser")
  public static void openBrowserHeadless(String browser) {
	driver = browser.equals("Chrome") || browser == null? new ChromeDriver(new ChromeOptions().setHeadless(true)) : 
		new FirefoxDriver(new FirefoxOptions().setHeadless(true));
	driver.get("https://google.com");
	driver.manage().window().maximize();
  }
  
  @Test
  @Parameters("browser")
  public static void openBrowser(String browser) {
	  driver = browser.equals("Chrome") || browser == null? new ChromeDriver() : new FirefoxDriver();
	  driver.get("https://google.com");
	  driver.manage().window().maximize();
  }
  
  @Test
  public static void openRemoteBrowserHeadless() throws MalformedURLException {
	  ChromeOptions options = new ChromeOptions();
	  options.setHeadless(true);
	  options.addArguments("--no-sandbox");
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
	  driver.get("https://google.com");
	  driver.manage().window().maximize();
  }
  
  @Test
  @Parameters("url")
  public static void navigateToURL(String url) {
	  try {
		@SuppressWarnings("unused")
		URL uri = new URL(url);
		driver.get(url);
	} catch (MalformedURLException e) {
		System.err.println("Fix your url!");
	}
  }
  
  @Test
  @Parameters("url")
  public static void takescreenshot(String filename) throws IOException {
	  TakesScreenshot scrShot = ((TakesScreenshot)driver);
	  File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
	  filename = filename + LocalDateTime.now();
	  File destFile = new File(System.getProperty("user.dir") + "/Pics/"+filename + ".png");
	  FileUtils.copyFile(srcFile,destFile);
  }
  
  @Test
  @Parameters("time")
  public static void delay(int time) {
	  driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
  }
  
  @Test
  @Parameters({"testobject","time"})
  public static void waitForElementVisible(TestObject to, int time) {
	  driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
  }
  
  
  @Test
  public static void closeBrowser() {
	  driver.quit();
  }
  
  @Test
  public static void closeTab() {
	  driver.close();
  }
  
}
