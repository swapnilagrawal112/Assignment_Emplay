package amazon;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
	
public class SearchPenDrive {
	WebDriver driver;
	
  @Test
  public void searchProduct()
  {
	  	String minimumPrice = "300";
	  	String maximumPrice = "1000";
	  	WebDriverWait wait = new WebDriverWait(driver,20);
	  	
	  	String searchText = "pen drive";
	  	
	    WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(searchText);
		
		
		WebElement searchIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit' and @value ='Go']")));
		searchIcon.click();
		
		WebElement sanDiskcheckBox = driver.findElement(By.xpath("//input[@name='s-ref-checkbox-SanDisk']"));
		sanDiskcheckBox.click();
		System.out.println("Filter applied to search for SanDisk.");
		
		WebElement userRating = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='a-link-normal s-ref-text-link']/i[@class='a-icon a-icon-star-medium a-star-medium-4']")));
		userRating.click();
		System.out.println("Filter applied to get user rating 4 and up.");
		
		WebElement minPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("low-price")));
		minPrice.sendKeys(minimumPrice);
		System.out.println("Entered Min price as "+minimumPrice);
		
		WebElement maxPrice = driver.findElement(By.id("high-price"));
		maxPrice.sendKeys(maximumPrice);
		System.out.println("Entered Max price as "+maximumPrice);
		
		WebElement goButton = driver.findElement(By.xpath("//input[@class='a-button-input' and @value='Go']"));
		goButton.click();
		
		List<WebElement> normalPrice = driver.findElements(By.xpath("//span[@class='a-size-base a-color-price s-price a-text-bold']"));
		int nSize = normalPrice.size();
		
		System.out.println("Size of Prime PriceList is "+nSize);
		
		WebElement relevance = driver.findElement(By.id("sort"));
		Select dropDown = new Select(relevance);
		dropDown.selectByVisibleText("Price: Low to High");
		
		WebElement selectPD = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title, 'SanDisk')][1]")));
		selectPD.click();
		
		//New Tab Navigation Handle
		ArrayList<Object> tabs = new ArrayList<Object> (driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window((String) tabs.get(1)); 
		
      	//Adding the pendrive to Cart.
    	WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='add-to-cart-button']")));
		addToCart.click();
		
		// Proceeding to checkout.
		WebElement proceedToCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hlb-ptc-btn-native")));
		String buttonText = proceedToCheckOut.getText();
		proceedToCheckOut.click();
		System.out.println("Clicked on "+buttonText);
		
		//Verifying if user is prompt to login before checkout.
		Assert.assertTrue("User is prompted for login", driver.getPageSource().contains("Login"));
		System.out.println("User is Prompted to Login before checkout.");
  }
  
 
  @BeforeMethod
  public void launchBrowser() 
  {
	  	//set path to chromedriver.exe: Use the path to chrome driver in your system.
		System.setProperty("webdriver.chrome.driver","path to chrome.exe");
		
		//create chrome instance
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Navigating to Amazon.in
		driver.get("https://www.amazon.in");
  }
  @AfterMethod
  public void closeBrowser() 
  {
	  driver.close();
  }

}
