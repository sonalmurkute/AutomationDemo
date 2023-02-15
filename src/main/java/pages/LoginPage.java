package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

	By txtUserName = By.name("username");
	By txtpassword = By.name("password");
	By btnLogin    = By.cssSelector("button[type='submit']");
	By txtError    = By.xpath("(//div[@class='oxd-form-row']//span)[1]");
	By txtInvalidError = By.xpath("(//div[@class='orangehrm-login-form']//p)[1]");
	
	public LoginPage()
	{
		super();
	}
	
	public  void enterUserName(String un)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement userName = wait.until(ExpectedConditions.presenceOfElementLocated(txtUserName));
		userName.sendKeys(un);
	}
	public void enterPassword(String pwd)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(txtpassword));
		password.sendKeys(pwd);
	}
	public  void clicOnLoginBtn()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(btnLogin));
		loginBtn.click();
	}
	public LandingPage LoginToAppl(String un, String pwd)
	{
		enterUserName(un);
		enterPassword(pwd);
		clicOnLoginBtn();
		return new LandingPage();
	}
	
	public String getErrorMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(txtError)).getText();
	}
	
	public String getErrorMsgForInvalidCredentials()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(txtInvalidError)).getText();
	}
	
	
	
}
