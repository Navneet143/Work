package ndtv;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Temperature_Param {

	WebDriver driver;

	@BeforeTest
	public void OpenBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.ndtv.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void Click_Weather() {
		WebElement more_option = driver.findElement(By.xpath("//a[@id=\"h_sub_menu\"]"));
		more_option.click();
		driver.findElement(By.xpath("//a[text()=\"WEATHER\"]")).click();
	}

	@Test
	@Parameters("city")
	public void temperature(String city) throws Throwable {
		Temperature temp1 = new Temperature(driver);
		temp1.set_SelectedCites();
		//temp1.print_selectedcities();
		if (!temp1.selected_cities.contains(city))
			driver.findElement(By.xpath(String.format("//input[@id=\"%s\"]", city))).click();
		temp1.PrintTemp(city);
		temp1.unSelectALL();

	}

	@AfterTest
	public void Close_Browser() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}

}
