/**
 * 
 */
package webUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author omar totangy
 *
 */
public class WebUI {
	public static WebDriver driver;

	public static void openBrowserHeadless(String browser) {
		driver = browser.equals("Chrome") || browser == null? new ChromeDriver(new ChromeOptions().setHeadless(true)) : 
			new FirefoxDriver(new FirefoxOptions().setHeadless(true));
		driver.get("https://google.com");
		driver.manage().window().maximize();
	}

	public static void openBrowser(String browser) {
		driver = browser.equals("Chrome") || browser == null? new ChromeDriver() : new FirefoxDriver();
		driver.get("https://google.com");
		driver.manage().window().maximize();
	}

	public static void navigateToURL(String url) {
		try {
			@SuppressWarnings("unused")
			URL uri = new URL(url);
			driver.get(url);
		} catch (MalformedURLException e) {
			System.err.println("Fix your url!");
		}
	}

	public static String takescreenshot(String filename, HttpServletRequest request) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		ServletContext sc = request.getServletContext();
		filename = sc.getRealPath("Pics")+ "/"+filename + LocalDateTime.now() + ".png";
		File destFile = new File(sc.getRealPath("Pics")+ "/"+filename + ".png");
		FileUtils.copyFile(srcFile,destFile);
		return filename;
	}
	
	public static void delay(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	public static void closeBrowser() {
		driver.quit();
	}
	
	public static void closeTab() {
		driver.close();
	}
	
	public static void openRemoteBrowserHeadless() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		options.addArguments("--no-sandbox");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		driver.get("https://google.com");
		driver.manage().window().maximize();
	}
}
