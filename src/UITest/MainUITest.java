package UITest;

import java.awt.RenderingHints.Key;
import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainUITest {

	public static void main(String[] args) {

		//setup the System
		System.setProperty("webdriver.gecko.driver","/home/chris/bin/SeleniumWebDriver/geckodriver");
		WebDriver driver = new FirefoxDriver();

		System.out.println("Starting testcase 1...");

		try {
			
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.get("https://www.otto.de/");
			
			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			String title = driver.getTitle();

			System.out.println("Title: "+title);
			
			
			WebElement CookieBanner = driver.findElement(By.id("cookieBanner"));
			CookieBanner.findElement(By.cssSelector("button")).click();

			WebElement SearchJeans = driver.findElement(By.cssSelector("[data-qa-id=\"search-field\"]"));

			//WebElement SearchJeans = new WebDriverWait(driver, Duration.ofSeconds(5))
			//.until(driver.findElement(By.linkText("Wonach suchst du?")));

			System.out.println("SearchResult: "+SearchJeans.getText());

			SearchJeans.sendKeys("Jeans" + Keys.ENTER);
			
			System.out.println("SearchResult: "+SearchJeans.getText());
			
			//class "product sponsored", data-list-position=1, data-local-list-position=1, class "find_tile__container
			//WebElement firstResult = driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]"));
			//WebElement firstResult = driver.findElement(By.className("find_tile__container"));
			//WebElement firstResult = driver.findElement(By.cssSelector("[data-list-position=\"1\"]"));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.className("find_tile__container")));
			
			driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]")).click();
			
			boolean found = driver.getPageSource().contains("Artikelbeschreibung");
			System.out.println("Artikelbeschreibung "+found);
			
			//class pl-block-pdp-details-short-info
			WebElement detailView = driver.findElement(By.id("detailviewWrapper"));
			System.out.println("detailviewWrapper: "+detailView.getText());
			
			WebElement pdpDetails = driver.findElement(By.className("pdp_details-short-info__wrapper"));
			System.out.println("pdpDetails: "+pdpDetails.getText());
			
			

			
			
		}finally {

			System.out.println("End!");
			driver.quit();
		}

	}

}
