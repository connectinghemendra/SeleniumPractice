package com.TestNGDemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AnnotationDemo2 {
	
	WebDriver driver;
	String URLAfterLogin = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
	String DashboardTitle = "OrangeHRM";	
	
	@BeforeClass(groups = "sanity")
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver86.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test(description = "Login to Orange HRM..", groups = "sanity")
	public void loginTest() {
		driver.findElement(By.xpath("//input[@id=\"txtUsername\"]")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id=\"txtPassword\"]")).sendKeys("admin123");
		driver.findElement(By.xpath("//input[@id=\"btnLogin\"]")).click();
	}
	
	//@Test(description = "Test URL, Title and Image..", dependsOnMethods = "loginTest", alwaysRun = true)
	//@Test(description = "Test URL, Title and Image..", invocationCount = 3)
	//@Test(description = "Test URL, Title and Image..", enabled = false)
	//@Ignore
	@Test(description = "Test URL, Title and Image..")
	public void verifyLoginTitleURL()
	{
		Assert.assertEquals(driver.getCurrentUrl(), URLAfterLogin);
		Assert.assertEquals(driver.getTitle(), DashboardTitle);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"branding\"]/a[1]/img")).isDisplayed());
		
	}
	
	@Test(groups = "sanity")
	public void logout() throws Exception {
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}
	
	@AfterClass(groups = "sanity")
	public void tearDown() {
		
		driver.quit();
	}

}
