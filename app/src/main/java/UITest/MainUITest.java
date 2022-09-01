package UITest;

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
			
			//avoid implicit wait and use expicit waits
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			//setup explicit wait
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			driver.get("https://www.otto.de/");
			String title = driver.getTitle();
			System.out.println("Title: "+title);
			
			//close Cookie Banner
			WebElement CookieBanner = driver.findElement(By.id("cookieBanner"));
			//System.out.println("CookieBanner: "+CookieBanner.getText());
			CookieBanner.findElement(By.cssSelector("button")).click();


			//find search field and Type 
			WebElement SearchJeans = driver.findElement(By.cssSelector("[data-qa-id=\"search-field\"]"));
			//WebElement SearchJeans = new WebDriverWait(driver, Duration.ofSeconds(5))
			//.until(driver.findElement(By.linkText("Wonach suchst du?")));

			SearchJeans.sendKeys("Jeans" + Keys.ENTER);

			//get the first product from the list
			//class "product sponsored", data-list-position=1, data-local-list-position=1, class "find_tile__container
			//WebElement firstResult = driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]"));
			//WebElement firstResult = driver.findElement(By.className("find_tile__container"));
			//WebElement firstResult = driver.findElement(By.cssSelector("[data-list-position=\"1\"]"));
			
			//wait until the search results are listed
			wait.until(ExpectedConditions.elementToBeClickable(By.className("find_tile__container")));
			
			//get the first product and click on it
			driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]")).click();
			
			//check if "Artikelbeschrischreibung" is part of the whole page
			boolean found = driver.getPageSource().contains("Artikelbeschreibung");
			System.out.println("Artikelbeschreibung on Whole Page "+found);
			
			//check if "Artikelbeschrischreibung" is part of the detailView area
			WebElement detailView = driver.findElement(By.id("detailviewWrapper"));
			//System.out.println("detailviewWrapper: "+detailView.getText());
			boolean foundDetailviewWrapper = detailView.getText().contains("Artikelbeschreibung");
			System.out.println("Artikelbeschreibung on Whole Description Area"+foundDetailviewWrapper);
			
			//check if "Artikelbeschrischreibung" is part of the prd detailsArea
			WebElement prdDetailArea = driver.findElement(By.cssSelector("[data-qa=\"detailsArea\"]"));
			//System.out.println("prdDetailArea: "+prdDetailArea.getText());
			boolean foundPrdDetailArea = prdDetailArea.getText().contains("Artikelbeschreibung");
			System.out.println("Artikelbeschreibung on Product Details"+foundPrdDetailArea);
			
			//check if "Artikelbeschrischreibung" is part of the prd details short info
			WebElement pdpDetails = driver.findElement(By.className("prd_detailShortInfo__wrapper"));
			//System.out.println("pdpDetails: "+pdpDetails.getText());
			boolean foundPdpDetails = pdpDetails.getText().contains("Artikelbeschreibung");
			System.out.println("Artikelbeschreibung on Products ShortInfo"+foundPdpDetails);
			
		}finally {

			System.out.println("End Testcase 1!");
			driver.quit();
		}

	}

}
