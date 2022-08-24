package UITest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


class UITestCase {
	
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeAll
    public static void setDriver() {
    	
    	//setup Webdriver for Firefox
    	
    	//either using a Webdriver Manager 
    	//(possible enhancement for getting always the latest and greatest Driver)
        //WebDriverManager.firefoxdriver().setup();
    	
    	//setting up the Driver via fixed path -> quick and dirty solution for my system only
    	System.setProperty("webdriver.gecko.driver","/home/chris/bin/SeleniumWebDriver/geckodriver");
    }

    @BeforeEach
    public void setup() {
        driver = new FirefoxDriver();
        
        driver.get("https://www.otto.de/");
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

	@Test
	void OpenURL() {
		//check if URL could be reached and check Title
		assertEquals("OTTO - Mode, Möbel & Technik » Zum Online-Shop",driver.getTitle());
	}
	
	@Test
	void CookieBanner() {
		
		//check if Cookie Banner was displayed as expected 
		WebElement CookieBanner = driver.findElement(By.id("cookieBanner"));
		assertTrue(CookieBanner.isDisplayed());
		
		//check if CookieBanner could be closed as expected
		//needed for other TestCase to run smoothly
		CookieBanner.findElement(By.cssSelector("button")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cookieBanner")));
		assertFalse(CookieBanner.isDisplayed());
	}
	
	void CloseCookieBanner() {
		//needed for other TestCase to run smoothly
		WebElement CookieBanner = driver.findElement(By.id("cookieBanner"));
		CookieBanner.findElement(By.cssSelector("button")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cookieBanner")));
	}
	
	@Test
	void TestCase1() {
		
		CloseCookieBanner();
		
		//find search field and Type 
		WebElement SearchJeans = driver.findElement(By.cssSelector("[data-qa-id=\"search-field\"]"));
		SearchJeans.sendKeys("Jeans" + Keys.ENTER);

		//wait until the search results are listed
		wait.until(ExpectedConditions.elementToBeClickable(By.className("find_tile__container")));
		
		//get the first product and click on it
		driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]")).click();
		
		//check if "Artikelbeschrischreibung" is part of the whole page
		assertTrue(driver.getPageSource().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the detailView area
		WebElement detailView = driver.findElement(By.id("detailviewWrapper"));
		//System.out.println("detailviewWrapper: "+detailView.getText());
		assertTrue(detailView.getText().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the prd detailsArea
		WebElement prdDetailArea = driver.findElement(By.cssSelector("[data-qa=\"detailsArea\"]"));
		//System.out.println("prdDetailArea: "+prdDetailArea.getText());
		assertTrue(prdDetailArea.getText().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the prd details short info
		WebElement pdpDetails = driver.findElement(By.className("prd_detailShortInfo__wrapper"));
		//System.out.println("pdpDetails: "+pdpDetails.getText());
		assertTrue(pdpDetails.getText().contains("Artikelbeschreibung"));
		
	}
	
	@Test
	void TestCase2() {
		
		CloseCookieBanner();
		
		//find Navigation Menu and click on Multimedia
		WebElement NavigationMenu = driver.findElement(By.id("nav_menu"));
		NavigationMenu.findElement(By.xpath("//span[.='Multimedia']")).click();

		//wait until Multimedia Page opened and click on BluRayPlayer
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Blu-ray-Player']")));
		driver.findElement(By.xpath("//span[.='Blu-ray-Player']")).click();
		
		//wait until the search results are listed
		wait.until(ExpectedConditions.elementToBeClickable(By.className("find_tile__container")));
		
		//get the first product and click on it
		driver.findElement(By.cssSelector("[data-qa=\"ftfind-product-1\"]")).click();
		
		//check if "Artikelbeschrischreibung" is part of the whole page
		assertTrue(driver.getPageSource().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the detailView area
		WebElement detailView = driver.findElement(By.id("detailviewWrapper"));
		//System.out.println("detailviewWrapper: "+detailView.getText());
		assertTrue(detailView.getText().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the prd detailsArea
		WebElement prdDetailArea = driver.findElement(By.cssSelector("[data-qa=\"detailsArea\"]"));
		//System.out.println("prdDetailArea: "+prdDetailArea.getText());
		assertTrue(prdDetailArea.getText().contains("Artikelbeschreibung"));
		
		//check if "Artikelbeschrischreibung" is part of the prd details short info
		WebElement pdpDetails = driver.findElement(By.className("prd_detailShortInfo__wrapper"));
		//System.out.println("pdpDetails: "+pdpDetails.getText());
		assertTrue(pdpDetails.getText().contains("Artikelbeschreibung"));
		
	}


}
