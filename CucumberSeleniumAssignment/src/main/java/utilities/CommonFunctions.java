package utilities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {
	public static WebDriver driver;

	// This will scroll the page till the element is found
	public static void moveToEle(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	// implicit wait
	public static void implicitWait(WebDriver driver, long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	// explicit wait
	public static void waitUntilClickable(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilVisible(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitUntilInvisible(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitUntilDefreezeUI(WebDriver driver, long time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("freeze-ui")));
	}

	public static void moveToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// fluent wait
	public static WebElement getElement(WebDriver driver, final String xpath, int time) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofNanos(20)).ignoring(NoSuchElementException.class);

		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpath));
			}
		});
		return ele;
	}

	public static boolean isElementAvailable(WebElement ele) {
		try {
			if (ele.isDisplayed())
				return true;
			else
				return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public static boolean isElementAvailable(WebDriver driver, String xpath) {
		try {
			if (driver.findElement(By.xpath(xpath)).isDisplayed())
				return true;
			else
				return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Select value from dropdown by value
	public static void SelectDropDownByValue(WebElement WE, String Value) {
		Select selObj = new Select(WE);
		selObj.selectByValue(Value);
	}

	// Select value from dropdown by Visible Text
	public static void SelectDropDownByVisibleText(WebElement WE, String VisibleText) {
		Select selObj = new Select(WE);
		selObj.selectByVisibleText(VisibleText);
	}

	// Select value from dropdown by Index Value
	public static void SelectDropDownByIndex(WebElement WE, int IndexValue) {
		Select selObj = new Select(WE);
		selObj.selectByIndex(IndexValue);
	}

	// Common function for appending unique timestamp
	public static String GetTimeStamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		return LocalDateTime.now().format(formatter);
	}

	public static boolean isEditable(WebDriver driver, WebElement ele) {
		try {
			ele.sendKeys("test");
			return true;
		}catch(ElementNotInteractableException e) {
			return false;
		}
		
	}

	public static void doubleClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele).build().perform();
	}

	public static void moveToElement(WebDriver driver, String xpath) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", xpath);
	}

	public static void enterText(WebDriver driver, WebElement ele, String str) {
		moveToElement(driver, ele);
		ele.clear();
		ele.sendKeys(str);
	}
}