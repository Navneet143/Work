package With_Yaml;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Temperature_yaml {
	WebDriver driver;
	Data data1 = new Data();
	Xpath xpath = new Xpath();

	@BeforeSuite
	public void read_data() throws Throwable {
		File file = new File("src/test/java/Data/data01.yml");
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		data1 = om.readValue(file, Data.class);
		// System.out.println("Details:\n"+data1.toString());

	}

	@BeforeClass
	public void OpenBrowser() {
		WebDriverManager.chromedriver().browserVersion(data1.getversion()).setup();
		driver = new ChromeDriver();
		driver.get(data1.getsite());
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void CheckTemperature() {
		xpath = data1.getXpath();
		Temperature temp1 = new Temperature(driver, xpath);
		WebElement more_option = driver.findElement(By.xpath(xpath.getmain_menu()));
		more_option.click();
		driver.findElement(By.xpath(xpath.getweather())).click();
		temp1.set_SelectedCites();
		// temp1.print_selectedcities();
		temp1.unSelectALL();
		for (String city : data1.getcity()) {
			if (temp1.all_cities.contains(city)) {
				if (!temp1.selected_cities.contains(city))
					driver.findElement(By.xpath(String.format(xpath.getcheckbox(), city))).click();	
				temp1.PrintTemp(city);
			}
			else
				System.out.println(city+" is not listed here!");	
		}
	}

	@AfterSuite
	public void CloseBroswer() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
