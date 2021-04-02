package com.aldrich.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

import com.aldrich.BO.LocationDetailsBO;
import com.aldrich.entity.SocialMediaLink;

public interface LinkedinAboutUsService {

	public WebDriver getLinkedinAboutPage(WebDriver driver, String linkedinURL);

	public WebDriver loginLinkedinWebsite(WebDriver driver);

	public WebDriver setChromeDriver();

	public void runService();

	public void processResponse(Long paseId, String domain, String resp);

	public boolean isValid(JSONObject jsonObject, String propertyName);

	public String getCompanyUrl(JSONObject jsonObject);

	public String getStaffCount(JSONObject jsonObject);

	public String getLinkedinCompanyID(JSONObject jsonObject);

	public String getCompanyType(JSONObject jsonObject);

	public String getFoundedOn(JSONObject jsonObject);

	public String getDescription(JSONObject jsonObject);

	public String getStaffRanageEndCount(JSONObject jsonObject);

	public String getStaffRanageStartCount(JSONObject jsonObject);

	public List<LocationDetailsBO> getConfirmedLocations(JSONObject jsonObject);

	public List<LocationDetailsBO> getHeadquaterLocations(JSONObject jsonObject);

	//public void processJSONObject(JSONObject jsonObject);

	public String getDomainNameForURL(String url);

	public long checkForCategoryDetails(String categoryName);

	public boolean saveSocialMediaLinks(SocialMediaLink socialMediaLinkInfo);

	public void saveImage(String imageUrl, String destinationFile) throws IOException;

	public String getCategoryID(JSONObject jsonObject);

	//public void processJSONObject(JSONObject jsonObject, long paseId);

	public boolean saveSocialMediaLinks1(SocialMediaLink socialMediaLinkInfo);

	public void processJsonElement(Long paseId, String domain, Element element, String logo);

	public void processJSONObject(JSONObject jsonObject, long paseId, String logo);


	

}
