package ndtv;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Temperature {

	WebDriver driver;
	List<String> selected_cities = new ArrayList<>();

	public Temperature(WebDriver driver) {
		this.driver = driver;
	}

	public void set_SelectedCites() {
		List<WebElement> inputs = driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
		for (WebElement x : inputs) {
			if (x.isSelected())
				selected_cities.add(x.getAttribute("id"));
		}
	}

	public String get_temp(String str1) {
		Pattern p = Pattern.compile("\\b[0-9]+\\b");
		Matcher m = p.matcher(str1);
		// System.out.println(m.find()+" "+m.group(0));
		if (m.find())
			return m.group(0);
		return "Not Found";
	}

	public void PrintTemp(String city) {
		int i = 0;
		List<WebElement> Temp = driver
				.findElements(By.xpath(String.format("//div[@title=\"%s\"]//span[@class]", city)));// Xpath-//div[@title="Mumbai"]//span[@class]
		System.out.print("The Tempareture of " + city + " is:- ");
		for (WebElement temperature : Temp) {
			if (i == 0) {
				System.out.print(get_temp(temperature.getText()) + "\u00B0" + "C" + " ");
				i = 1;
			} else
				System.out.print(get_temp(temperature.getText()) + "\u00B0" + "F");

			// System.out.println(temperature.toString());
		}
		System.out.println();
	}

	public void print_selectedcities() {
		for (String city : selected_cities)
			PrintTemp(city);
	}

	public void unSelectALL() {
		for (String city : selected_cities) {
			driver.findElement(By.xpath(String.format("//input[@id=\"%s\"]", city))).click();
		}
	}
}
