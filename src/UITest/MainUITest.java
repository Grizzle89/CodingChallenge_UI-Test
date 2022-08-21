package UITest;

import java.awt.RenderingHints.Key;
import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainUITest {

	public static void main(String[] args) {

		//setup the System
		System.setProperty("webdriver.gecko.driver","/home/chris/bin/SeleniumWebDriver/geckodriver");
		WebDriver driver = new FirefoxDriver();

		System.out.println("Starting testcase 1...");

		try {

			driver.get("https://www.otto.de/");
			String title = driver.getTitle();

			System.out.println("Title: "+title);

			WebElement SearchJeans = driver.findElement(By.cssSelector("[data-qa-id=\"search-field\"]"));

			//WebElement SearchJeans = new WebDriverWait(driver, Duration.ofSeconds(5))
			//.until(driver.findElement(By.linkText("Wonach suchst du?")));

			System.out.println("SearchResult: "+SearchJeans.getText());

			SearchJeans.sendKeys("Jeans" + Keys.ENTER);
			
			System.out.println("SearchResult: "+SearchJeans.getText());
			
			//class "product sponsored", data-list-position=1, data-local-list-position=1, class "find_tile__container
			WebElement firstResult = driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]"));
			firstResult.click();
			
			//class pl-block-pdp-details-short-info
			WebElement detailView = driver.findElement(By.id("detailviewWrapper"));
			System.out.println("SearchResult: "+detailView.getText());
			
			//class pl-block-pdp-details-short-info


		}finally {

			System.out.println("End!");
			driver.quit();
		}

	}

}
