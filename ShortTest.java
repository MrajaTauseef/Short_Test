import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ShortTest {
	
	
	public AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void Launch_Application() throws MalformedURLException
	{
		
		// *This Will Create Session On Appium Server And Will Launch App*
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("deviceName", "Samsung Galaxy S7");
		capabilities.setCapability("platformVersion", "8.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.mopub.simpleadsdemo");                
		capabilities.setCapability("appActivity", ".MoPubSampleActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.2:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TestCase() throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='ALLOW']")).click();
		driver.findElement(By.id("com.mopub.simpleadsdemo:id/banner_ad_unit_id")).click();
		Thread.sleep(3000);
		AndroidElement ele= driver.findElement(By.xpath("//android.view.View[@text=' Success! Tap to test this ad.']"));
		Thread.sleep(3000);
		if(ele.isDisplayed())
		{
			System.out.println("AD Displayed");
			ele.click();
			Thread.sleep(10000);
		}
		Thread.sleep(10000);
		Set<String> contextNames = driver.getContextHandles();
        System.out.println(contextNames.size());
        for (String contextName : contextNames) {
            System.out.println(contextName);
            if (contextName.contains("WEBVIEW")){
                driver.context(contextName);
            }
        }
		WebElement ele1=driver.findElement(By.xpath("/html/body/section[2]/div/div/div/h2"));
		if(ele1.getText().equals("Looking for MoPub resources?"))
		{
			System.out.println("Success");
		}
		else
		{
			System.out.println("System has failed you");

		}
		
		Thread.sleep(5000);
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
