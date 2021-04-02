package com.aldrich.service;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public interface LinkedinPeopleStatsService {

	public void runService();

	public WebDriver setChromeDriver();

	public WebDriver loginLinkedinWebsite(WebDriver driver);

	public WebDriver getCompanyPage(WebDriver driver, String linkedinURL);

	public String getEmpSize(double empProj);

	public String getEmployeeCount(Document document);

	public boolean getAvailStatus(String name);

	public void waitForLoad(WebDriver driver);

	public WebDriver getLoginDriver();

	public void processResponse(String res, Long companyId);

	public void getNameValueTyoe(Document document, String employeecount, Long companyId);

	public void saveInfo(String value, String name, String type, String employeecount, long companyId);

}
