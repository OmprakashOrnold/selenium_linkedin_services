package com.aldrich.service;

import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aldrich.entity.Jobs;

public interface LinkedinJobsService {

	public void runService();
	public WebDriver setChromeDriver();
	public WebDriver loginLinkedinWebsite(WebDriver driver);
	public WebDriver getLoginDriver();
	public boolean isElementPresent(WebDriver driver, By selector);
	public void scrollWebPage(WebDriver driver, int scrollLimit) throws InterruptedException;
	public WebDriver getCompanyPage(WebDriver driver, String linkedinURL);
	public String getJobLocation(Element element);
	public String getJobPostedDateType1(Element element);
	public String getPostedDateType2(Element element);
	public String getJobTitle(Element element);
	public String getJobUrl(Element element);
	public void processResponse(String resp, Long companyId);
	public boolean storeJobDetails(Jobs indeedJobs, Long companyId);
	
}
