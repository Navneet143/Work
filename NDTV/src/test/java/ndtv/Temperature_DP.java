package ndtv;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Temperature_DP {

	
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		//System.out.println("in beforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.ndtv.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
//			Thread.sleep(5000);
//			driver.switchTo().alert().dismiss();
		WebElement more_option = driver.findElement(By.xpath("//a[@id=\"h_sub_menu\"]"));
		more_option.click();
		driver.findElement(By.xpath("//a[text()=\"WEATHER\"]")).click();
	}

	@DataProvider(name = "test1")
	public static Object[][] cities() {
		return new Object[][] { { "Ajmer" } , { "Bhiwandi" },{ "Bhilai" }};
	}

	@Test(dataProvider = "test1")
	public void temperature(String city){
		Temperature temp1 = new Temperature(driver);
		temp1.set_SelectedCites();
		//print_selectedcities();
		if (!temp1.selected_cities.contains(city))
			driver.findElement(By.xpath(String.format("//input[@id=\"%s\"]", city))).click();
		temp1.unSelectALL();
		temp1.PrintTemp(city);
		
	}

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}

}
