package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TaskunMainMemuTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://code.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTaskunMainMemu() throws Exception {
		driver.get("http://127.0.0.1:8080/maxur/home");
		assertEquals("Taskun (Simple Task Manager)", driver.getTitle());
		driver.findElement(By.linkText("About")).click();
		assertEquals("Maxur Taskun Web Application (Version : 0.1)", driver.findElement(By.xpath("//section/div/div/ul/li")).getText());
		driver.findElement(By.linkText("Welcome")).click();
		assertEquals("Add support for YAML 4", driver.findElement(By.xpath("//section/div/div/span/span")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
