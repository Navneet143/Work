package With_Yaml;

import java.util.ArrayList;
import java.util.List;

public class Data {
	
	private String site;
	private String version;
	private Xpath xpath;
	private List<String> city= new ArrayList<>();
	
	
	public Data(){}
	
	Data(String site,String version,Xpath xpath,List<String> city){
		this.site=site;
		this.version=version;
		this.xpath=xpath;
		this.city=city;
		
	}
	
	public String getsite() {
		return site;
	}
	
	public String getversion() {
		return version;
	}
	
	public Xpath getXpath() {
		return xpath;
	}
	
	public List<String> getcity() {
		return city;
	}
	
	
	@Override
	public String toString() {
		return "site: "+site+"\nversion"+version+"\nxpath: "+xpath+"\ncity: "+city;
	}

}

class Xpath{
	private String main_menu;
	private String weather;
	private String listofcities;
	private String temperature;
	private String checkbox;
	
	public Xpath() {}
	public Xpath(String main_menu,String weather,String listofcities,String temperature,String checkbox) {
		this.main_menu = main_menu;
		this.weather= weather;
		this.listofcities= listofcities;
		this.temperature= temperature;
		this.checkbox=checkbox;
	}
	
	public String getmain_menu() {
		return main_menu;
	}
	
	public String getweather() {
		return weather;
	}
	
	public String getlistofcities() {
		return listofcities;
	}
	
	public String gettemperature() {
		return temperature;
	}
	
	public String getcheckbox() {
		return checkbox;
	}
	
	@Override
	public String toString() {
		return "\nmain_menu: "+main_menu+"\nweather: "+weather+"\nlistofcities: "+listofcities+"\ntemperature: "+temperature;
	}
}