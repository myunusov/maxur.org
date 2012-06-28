package com.example.tests;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

public class TaskunMainMemuTest extends SeleneseTestCase {
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://code.google.com/");
		selenium.start();
	}

	@Test
	public void testTaskunMainMemu() throws Exception {
		selenium.open("http://127.0.0.1:8080/maxur/home");
		assertEquals("Taskun (Simple Task Manager)", selenium.getTitle());
		selenium.click("link=About");
		selenium.waitForPageToLoad("30000");
		assertEquals("Maxur Taskun Web Application (Version : 0.1)", selenium.getText("//section/div/div/ul/li"));
		selenium.click("link=Welcome");
		selenium.waitForPageToLoad("30000");
		assertEquals("Add support for YAML 4", selenium.getText("//section/div/div/span/span"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
