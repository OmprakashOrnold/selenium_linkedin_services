package com.aldrich.service;

import org.openqa.selenium.WebDriver;

public interface LinkedinPostsDataService {

	public void runService();

	public WebDriver setChromeDriver();

	public WebDriver loginLinkedinWebsite(WebDriver driver);

	public WebDriver getCompanyPage(WebDriver driver, String linkedinURL);

	public void waitForLoad(WebDriver driver);

	public WebDriver getLoginDriver();

}
