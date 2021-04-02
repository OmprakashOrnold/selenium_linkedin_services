package com.aldrich.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.LinkedinPostsDataDAO;
import com.aldrich.service.LinkedinPostsDataService;

@Service
public class LinkedinPostsDataServiceImpl implements LinkedinPostsDataService {

	
	@Autowired
	LinkedinPostsDataDAO linkedinPostsDataDAO;

	@Override
	public void runService() {
		int count = 0;
		String resp = "";
		int scrollLimit=5;
		BufferedWriter bufferedWriter = null;
		WebDriver loginDriver = null;
		WebDriver companyPage = null;
		List<LinkedinInputBO> linkedinBOList = null;
		try {
			linkedinBOList = this.linkedinPostsDataDAO.getLinkedinLinks();
			if (linkedinBOList != null && !linkedinBOList.isEmpty()) {
				loginDriver = this.getLoginDriver();
				if (loginDriver != null) {
					for (LinkedinInputBO linkedinBO : linkedinBOList) {
						try {
							Thread.sleep(5000);
							companyPage = this.getCompanyPage(loginDriver, linkedinBO.getLinkedinURL());
						
							Thread.sleep(5000);
							if (companyPage != null) {
								resp = companyPage.getPageSource();
								
								if (!resp.trim().toLowerCase().contains("oops!")
										|| !resp.trim().toLowerCase().contains("this page is not available")
										|| !resp.trim().toLowerCase().contains("page not found")
										|| !resp.trim().toLowerCase().contains("try again")) {
									
									Writer writer = new FileWriter(
											"C:\\posts_22.01\\" + linkedinBO.getPaseID() + ".html");
									bufferedWriter = new BufferedWriter(writer);
									bufferedWriter.write(resp);
									bufferedWriter.close();
								}
							
									
							}
							count = count + 1;
							if (count == 100) {
								loginDriver.navigate().to("https://www.linkedin.com/m/logout/");
								loginDriver.close();
								loginDriver = this.getLoginDriver();
								count = 0;
								Thread.sleep(900000);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public WebDriver setChromeDriver() {
		WebDriver driver = null;
		ChromeOptions options = null;
		try {
			options = new ChromeOptions();
			// options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--no-sandbox");
			// options.addArguments("--start-maximized");
			options.addArguments("--window-size=2000,6000");
			options.setCapability("acceptSslCerts", true);
			options.setCapability("acceptInsecureCerts", true);
			System.setProperty("webdriver.chrome.driver", "C:\\Om\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	@Override
	public WebDriver loginLinkedinWebsite(WebDriver driver) {
		String loginUrl = "https://www.linkedin.com/login";
		try {
			driver.get(loginUrl);
			driver.findElement(By.cssSelector("input[name='session_key']")).sendKeys("sputtala@cascadesresearch.com");
			driver.findElement(By.cssSelector("input[name='session_password']")).sendKeys("abcxyz@123");
			driver.findElement(By.cssSelector("button[aria-label='Sign in']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public WebDriver getCompanyPage(WebDriver driver, String linkedinURL) {
		int scrollLimit = 10;
		if (linkedinURL.contains("?"))
			linkedinURL = linkedinURL.substring(0, linkedinURL.indexOf("?"));
		// String page="";
		// String loginUrl =
		// "https://www.linkedin.com/company/"+linkedinURL+"/insights/";
		String loginUrl = "https://www.linkedin.com/company/" + linkedinURL + "/"+"posts/?feedView=all";
		// String loginUrl = linkedinURL + "/people/";
		try {
			driver.navigate().to(loginUrl);
			for (int i = scrollLimit; i >= 0; i--) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				// Wait to load the scrolled page
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 250);
		wait.until(pageLoadCondition);
	}

	@Override
	public WebDriver getLoginDriver() {
		WebDriver chromeDriver = null;
		WebDriver loginDriver = null;
		try {
			chromeDriver = this.setChromeDriver();
			if (chromeDriver != null) {
				loginDriver = this.loginLinkedinWebsite(chromeDriver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginDriver;
	}

}
