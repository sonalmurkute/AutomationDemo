package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	Properties prop;
	
	public BasePage()
	{
		String fileName=null;
		try {
			fileName = System.getProperty("user.dir") + 
					"/src/test/resources/config.properties";
			FileInputStream fis = new FileInputStream(fileName);
			prop  = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("config.properties file not found - " + fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("config.properties could not load - " + fileName);
		}	
	}
	
	public void init()
	{
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("CHROME"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("EDGE"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("FIREFOX"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else
			throw new RuntimeException("Invalid browser - " + browser);
			
		driver.get(prop.getProperty("url"));
		boolean maximize = Boolean.parseBoolean(prop.getProperty("maximize"));
		if (maximize)
			driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("timeout"))));
	}
}
