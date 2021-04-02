package com.aldrich.service;

import java.util.Date;

import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import com.aldrich.BO.LinkedinInputBO;

public interface LinkedinEmployeeService 
{
	//......................................Selenium methods.............................................................
		public void runService();
		public WebDriver setChromeDriver();
		public WebDriver loginLinkedinWebsite(WebDriver driver);
		public WebDriver getCompanyPage(WebDriver driver,String unique_id);
		public void processCompanyPages(WebDriver companyPage, int scrollLimit, LinkedinInputBO linkedinBO);
		
		//......................................Jsoup methods.............................................................		
		public String getPersonName(Element element);
		public Date getConvertedMonthFromCalender(String yearMonthText, String monthWithSorWithoutS);
		public Date getConvertedYearFromCalender(String yearMonthText, String monthWithSorWithoutS);
		public Date getConvertedMonthYearFromCalender(String months_info, String years_info, String yearWithSorWithoutS);
		public Date getSingleMonthYearConvertedDate(String yearMonthText);
		public Date getMonthYearCombineConvertedDate(String yearMonthText);
		public Date getDateFromYearMonthText(String yearMonthText);
		public String getDegreeConnection(Element element);
		public String getLocation(Element element);
		public String getCompanyName(Element element);
		public String getProfileUrl(Element element);
		public String getDuration(Element element);
		public String getDesgination(Element element);
		public String getPersonImageUrl(Element element);
		public void processHtmlText(String resp, Long paseID);
		public void saveImage(String imageUrl, String destinationFile) throws Exception;
		
		

}
