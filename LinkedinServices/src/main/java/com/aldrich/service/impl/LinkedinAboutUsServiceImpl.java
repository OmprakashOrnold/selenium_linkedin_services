package com.aldrich.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.BO.LocationDetailsBO;
import com.aldrich.dao.CategoryDAO;
import com.aldrich.dao.CompanyConfirmLocationDAO;
import com.aldrich.dao.CompanyDAO;
import com.aldrich.dao.CompanyProfileDAO;
import com.aldrich.dao.SocialMediaLinkDAO;
import com.aldrich.entity.Category;
import com.aldrich.entity.CompanyConfirmLocation;
import com.aldrich.entity.CompanyProfile;
import com.aldrich.entity.SocialMediaLink;
import com.aldrich.service.LinkedinAboutUsService;


@Service
public class LinkedinAboutUsServiceImpl implements LinkedinAboutUsService {

	@Autowired
	SocialMediaLinkDAO socialMediaLinkDAO;

	@Autowired
	CompanyDAO companyDAO;

	@Autowired
	CompanyConfirmLocationDAO companyConfirmLocationDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	CompanyProfileDAO companyProfileDAO;


	@Override
	public void runService() {
		WebDriver chromeDriver=null;
		WebDriver loginDriver=null;
		WebDriver aboutPage=null;

		String resp=null;
		List<LinkedinInputBO> linkedinBOList = null;
		try
		{
			linkedinBOList=this.socialMediaLinkDAO.getLinkedinLinks();
			if(linkedinBOList!=null && !linkedinBOList.isEmpty()){
				chromeDriver=this.setChromeDriver();
				if(chromeDriver!=null){
					loginDriver=this.loginLinkedinWebsite(chromeDriver);
					if(loginDriver!=null){
						for(LinkedinInputBO linkedinBO : linkedinBOList){
							try{
								aboutPage=this.getLinkedinAboutPage(loginDriver,linkedinBO.getLinkedinURL());
								if(aboutPage!=null){
									Long paseId=linkedinBO.getPaseID();
									String cDomain=linkedinBO.getDomain();
									resp=aboutPage.getPageSource();							
									processResponse(paseId,cDomain,resp);							
								}	
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public WebDriver setChromeDriver() {	
		WebDriver driver = null;
		ChromeOptions options =null;
		try	{
			options = new ChromeOptions();
			//options.addArguments("--headless");	
			options.addArguments("--disable-gpu");
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--no-sandbox");
			options.addArguments("--start-maximized");
			options.addArguments("--window-size=2000,2000");
			options.setCapability("acceptSslCerts", true);
			options.setCapability("acceptInsecureCerts", true);	
			System.setProperty("webdriver.chrome.driver", "C:\\Om\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public WebDriver loginLinkedinWebsite(WebDriver driver) {
		String loginUrl = "https://www.linkedin.com/login";				
		try
		{
			driver.get(loginUrl);
			driver.findElement(By.cssSelector("input[name='session_key']")).sendKeys("pakersam32@gmail.com");	
			driver.findElement(By.cssSelector("input[name='session_password']")).sendKeys("aldrich4u@");			
			driver.findElement(By.cssSelector("button[aria-label='Sign in']")).click();	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public WebDriver getLinkedinAboutPage(WebDriver driver, String linkedinURL) {

		//String page="";
		int scrollLimit = 5;
		String loginUrl = linkedinURL+"/about";			
		try
		{
			driver.navigate().to(loginUrl);
			for (int i = scrollLimit; i >= 0; i--) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				// Wait to load the scrolled page
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return driver;
	}

	@Override
	public  void processResponse(Long paseId ,String domain ,String resp) {

		Document document=null;
		String logo = null;

		// read the content from file
		try {
			document = Jsoup.parse(resp);
			Elements image_elements = document.select("img");
			if (image_elements != null && image_elements.size() > 0) {
				for (Element element: image_elements) {
					try {
						if (element.hasAttr("class") && element.hasAttr("src") &&
								element.attr("class").equals("lazy-image ember-view org-top-card-primary-content__logo")) {
							logo = element.attr("src").trim();
							if (logo != null && logo != "") {
								logo=logo.toString().trim();
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			Elements code_elements = document.select("code");
			if (code_elements != null && code_elements.size() > 0) {
				for (Element element : code_elements) {
					try {
						processJsonElement(paseId,domain,element,logo);
					} catch (Exception ex) {
						System.out.println(ex.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public  void processJsonElement(Long paseId ,String domain ,Element element,String logo) {
		try {
			if (element.hasAttr("style") && element.hasAttr("id")) {
				if (element.attr("style").equals("display: none")&& element.attr("id").startsWith("bpr-guid-")) {
					String pageContent = element.text();
					if (pageContent != null && pageContent != "" && pageContent.contains("included")&& pageContent.contains("companyPageUrl")) {
						JSONObject jsonObject = new JSONObject(pageContent);
						JSONArray includeArray = jsonObject.getJSONArray("included");
						int arraySize = includeArray.length();
						if (arraySize > 0) {
							processJSONObject(jsonObject,paseId,logo);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public  void processJSONObject(JSONObject jsonObject,long paseId ,String logo) {

		String company_url=null;
		String staff_count=null;
		String description=null;
		String companyId=null;
		String company_type=null;
		String founded_on=null;
		String specialities=null;
		String start_count=null;
		String end_count=null;
		String categoryName=null;
		String empSize = null;
		String empCode = null;
		Long categoryId=0L;

		String companyDomainfrompage = null;
		String companyDomainfromdatabase = null;


		CompanyProfile companyProfile = new CompanyProfile();

		company_url=getCompanyUrl(jsonObject);
		try {

			if (company_url != null) 
			{
				companyDomainfrompage = this.getDomainNameForURL(company_url);
				companyDomainfromdatabase = this.socialMediaLinkDAO.getCompanyDomainById(paseId);
				if (companyDomainfrompage.equals(companyDomainfromdatabase)) {

					String destinationFile = "C:\\Om\\company_logos\\" +companyId + ".jpg";
					this.saveImage(logo, destinationFile);
					companyProfile.setLogoURL(logo);

					staff_count=getStaffCount(jsonObject);
					companyProfile.setEmpCount(Integer.parseInt(staff_count));	

					categoryName=getCategoryID(jsonObject);					
					categoryId = this.checkForCategoryDetails(categoryName);
					companyProfile.setFkCategoryId(categoryId);

					description=getDescription(jsonObject);
					companyProfile.setDescription(description);

					companyId=getLinkedinCompanyID(jsonObject);		
					SocialMediaLink socialMediaLinkInfo = new SocialMediaLink();
					socialMediaLinkInfo.setFkCompanyId(paseId);
					socialMediaLinkInfo.setUniqueId(companyId);

					company_type=getCompanyType(jsonObject);
					this.socialMediaLinkDAO.updateCompanyType(paseId, company_type);

					founded_on=getFoundedOn(jsonObject);
					companyProfile.setFoundedYear(founded_on.trim());

					specialities=getSpecialities(jsonObject);
					companyProfile.setSpecialties(specialities.replace("null,", "").trim());

					start_count=getStaffRanageStartCount(jsonObject);
					end_count=getStaffRanageEndCount(jsonObject);				
					empSize = getEmpSize(start_count, end_count);
					companyProfile.setEmployeeSize(empSize.trim());
					empCode = getEmpCode(empSize, empCode);
					companyProfile.setEmpCode(empCode);


					List<LocationDetailsBO> hHocation= getHeadquaterLocations(jsonObject) ;			
					for (LocationDetailsBO locationDetailsBO : hHocation) {
						try{
							companyProfile.setLocation(locationDetailsBO.getLine1()+", "+locationDetailsBO.getLine2());
							companyProfile.setState(locationDetailsBO.getGeographicArea());
							companyProfile.setCity(locationDetailsBO.getCity());
							companyProfile.setCountry(locationDetailsBO.getCountry());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					System.out.println("--------------------------");

					saveConfirmedLocations(jsonObject, paseId);

					if (companyProfile.getFkCategoryId() == null) {
						companyProfile.setFkCategoryId(categoryId);
					}
					// save the data
					if (companyProfile.getSpecialties() != null) {
						//method missing
						this.socialMediaLinkDAO.updateCompanyProfileFromLinkedin(paseId,companyProfile);
					}

				}
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveConfirmedLocations(JSONObject jsonObject, long paseId) {

		List<LocationDetailsBO> location= getConfirmedLocations(jsonObject) ;			
		for (LocationDetailsBO locationDetailsBO : location) {		
			try {
				CompanyConfirmLocation locinfo = new CompanyConfirmLocation();

				locinfo.setLine1(locationDetailsBO.getLine1());
				locinfo.setLine2(locationDetailsBO.getLine2());
				locinfo.setState(locationDetailsBO.getGeographicArea());
				locinfo.setCity(locationDetailsBO.getCity());
				locinfo.setCountry(locationDetailsBO.getCountry());

				locinfo.setActivity_datetime(new Date());
				locinfo.setFk_company_id((int) paseId);
				// save
				// locations
				List < Long > loclist = this.socialMediaLinkDAO.checkForExistance((int) paseId,locinfo);
				if (loclist.size() == 0) {
					this.socialMediaLinkDAO.save(locinfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getEmpSize(String start_count, String end_count) {
		String empSize;
		if (start_count != null && end_count != null) {
			empSize = start_count + "-" + end_count;
		} else if (start_count != null) {
			empSize = start_count + "+";
		} else {
			empSize = null;
		}
		return empSize;
	}

	public String getEmpCode(String empSize, String empCode) {
		if (empSize.trim().equals("1-10")) {
			empCode = "B";
		}
		if (empSize.trim().equals("11-50")) {
			empCode = "C";
		}
		if (empSize.trim().equals("51-200")) {
			empCode = "D";
		}
		if (empSize.trim().equals("201-500")) {
			empCode = "E";
		}
		if (empSize.trim().equals("501-1000")) {
			empCode = "F";
		}
		if (empSize.trim().equals("1001-5000")) {
			empCode = "G";
		}
		if (empSize.trim().equals("5001-10000")) {
			empCode = "H";
		}
		if (empSize.trim().equals("10000+")) {
			empCode = "I";
		}
		return empCode;
	}

	@Override
	public  List<LocationDetailsBO> getHeadquaterLocations(JSONObject jsonObject) {

		JSONArray includeArray = jsonObject.getJSONArray("included");
		List<LocationDetailsBO> locationDetailsList = new ArrayList<LocationDetailsBO>();
		int arraySize = includeArray.length();
		if (arraySize > 0) {
			for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
				try {
					JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
					LocationDetailsBO locationDetails=new LocationDetailsBO();
					try {
						if (isValid(innerObject, "headquarter")) {
							JSONObject headquarterObject = (JSONObject) innerObject.getJSONObject("headquarter");
							if (isValid(headquarterObject, "country")) {
								locationDetails.setCountry(headquarterObject.get("country").toString().trim());
							}
							if (isValid(headquarterObject, "geographicArea")) {
								locationDetails.setGeographicArea(headquarterObject.get("geographicArea").toString().trim());
							}
							if (isValid(headquarterObject, "city")) {
								locationDetails.setCity(headquarterObject.get("city").toString());
							}
							if (isValid(headquarterObject, "postalCode")) {
								locationDetails.setPostalCode(headquarterObject.get("postalCode").toString().trim());
							}
							if (isValid(headquarterObject, "line1")) {
								locationDetails.setLine1(headquarterObject.get("line1").toString());
							}
							if (isValid(headquarterObject, "line2")) {
								locationDetails.setLine2(headquarterObject.get("line2").toString());
							}
							locationDetailsList.add(locationDetails);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return locationDetailsList;
	}

	@Override
	public  List<LocationDetailsBO> getConfirmedLocations(JSONObject jsonObject) {

		JSONArray includeArray = jsonObject.getJSONArray("included");
		List<LocationDetailsBO> locationDetailsList = new ArrayList<LocationDetailsBO>();
		int arraySize = includeArray.length();
		if (arraySize > 0) {
			for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
				try {
					JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
					try {
						if (isValid(innerObject, "confirmedLocations")) {	
							JSONArray confirmedLocationsArray = innerObject.getJSONArray("confirmedLocations");
							if (confirmedLocationsArray.length() > 0) {
								for (int index = 0; index < confirmedLocationsArray.length(); index++) {
									LocationDetailsBO locationDetails=new LocationDetailsBO();
									JSONObject innerObject1 = confirmedLocationsArray.getJSONObject(index);
									if (isValid(innerObject1, "country")) {
										locationDetails.setCountry(innerObject1.get("country").toString().trim());
									}
									if (isValid(innerObject1, "geographicArea")) {
										locationDetails.setGeographicArea(innerObject1.get("geographicArea").toString().trim());
									}
									if (isValid(innerObject1, "city")) {
										locationDetails.setCity(innerObject1.get("city").toString());
									}
									if (isValid(innerObject1, "postalCode")) {
										locationDetails.setPostalCode(innerObject1.get("postalCode").toString().trim());
									}
									if (isValid(innerObject1, "line1")) {
										locationDetails.setLine1(innerObject1.get("line1").toString());
									}
									if (isValid(innerObject1, "line2")) {
										locationDetails.setLine2(innerObject1.get("line2").toString());
									}
									locationDetailsList.add(locationDetails);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return locationDetailsList;
	}



	public  String getSpecialities(JSONObject jsonObject) {
		String specialities="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "specialities")) {
							JSONArray specialitiesArray = innerObject.getJSONArray("specialities");
							if (specialitiesArray.length() > 0) {
								for (int index = 0; index < specialitiesArray.length(); index++) {
									specialities = specialities + ", "+ specialitiesArray.getString(index);
								}
								specialities= specialities.replace("null,", "").trim().replaceFirst(",", "").trim();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialities;
	}

	@Override
	public  String getStaffRanageStartCount(JSONObject jsonObject) {
		String start_count="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "staffCountRange")) {
							JSONObject staffCountRangeObject = (JSONObject) innerObject
									.getJSONObject("staffCountRange");
							if (isValid(staffCountRangeObject, "start")) {
								start_count= staffCountRangeObject.get("start").toString();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return start_count;
	}

	@Override
	public  String getStaffRanageEndCount(JSONObject jsonObject) {
		String end_count="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "staffCountRange")) {
							JSONObject staffCountRangeObject = (JSONObject) innerObject
									.getJSONObject("staffCountRange");
							if (isValid(staffCountRangeObject, "end")) {
								end_count=staffCountRangeObject.get("end").toString();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return end_count;
	}


	@Override
	public  String getDescription(JSONObject jsonObject) {
		String description="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "description")) {
							description=innerObject.get("description").toString().replaceAll("[\r\n]+", " ").trim();;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return description;
	}

	@Override
	public  String getFoundedOn(JSONObject jsonObject) {
		String founded_on="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "foundedOn")) {
							JSONObject foundedOnObject = (JSONObject) innerObject.getJSONObject("foundedOn");
							if (isValid(foundedOnObject, "year")) {
								founded_on=foundedOnObject.get("year").toString().trim();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return founded_on;
	}

	@Override
	public  String getCompanyType(JSONObject jsonObject) {
		String company_type="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "companyType")) {
							JSONObject companyTypeObject = (JSONObject) innerObject.getJSONObject("companyType");
							if (isValid(companyTypeObject, "localizedName")) {
								company_type=companyTypeObject.get("localizedName").toString();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company_type;
	}

	@Override
	public  String getLinkedinCompanyID(JSONObject jsonObject) {
		String companyId="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "entityUrn")&& isValid(innerObject, "companyPageUrl")) {
							companyId=innerObject.get("entityUrn").toString().replace("urn:li:fs_normalized_company:", "").trim();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyId;
	}

	@Override
	public  String getStaffCount(JSONObject jsonObject) {
		String staff_count="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "staffCount")) {
							staff_count=innerObject.get("staffCount").toString();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return staff_count;
	}

	@Override
	public  String getCompanyUrl(JSONObject jsonObject) {
		String company_url="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "companyPageUrl")) {
							company_url=innerObject.get("companyPageUrl").toString();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return company_url;
	}

	@Override
	public  boolean isValid(JSONObject jsonObject, String propertyName) {
		boolean valid = false;
		try {
			if (propertyName != null) {
				if (jsonObject.has(propertyName) && !jsonObject.get(propertyName).toString().equalsIgnoreCase("null")
						&& !jsonObject.get(propertyName).toString().equalsIgnoreCase(""))
					valid = true;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			valid = false;

		}
		return valid;
	}

	@Override
	public String getDomainNameForURL(String url) {
		String updatedURL = null;
		try {
			updatedURL = url.replace("///", "//").replace(",", ".");

			URI uri = new URI(url);
			String domain = uri.getHost();

			if (domain != null) {
				updatedURL = domain.startsWith("www.") ? domain.substring(4) : domain;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return updatedURL;
	}

	@SuppressWarnings("nls")
	@Override
	public long checkForCategoryDetails(String categoryName) {
		long categoryId = 25L;
		try {
			if (!categoryName.equals("") && categoryName != null) {
				String lowerCatName = categoryName.toLowerCase();
				lowerCatName = lowerCatName.replace(" ", "-");
				List<Category> categoriesList = this.socialMediaLinkDAO.checkForExistance(lowerCatName);
				if (categoriesList != null && categoriesList.size() > 0)
					categoryId = categoriesList.get(0).getId();
				else {
					Category category = new Category();
					Long defaultParentId = 0L;
					category.setParentId(defaultParentId);
					category.setName(categoryName);
					category.setCode(lowerCatName);
					categoryId = (Long) this.socialMediaLinkDAO.save(category);
				}
			} else {
				categoryId = 25L;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryId;
	}

	@SuppressWarnings("nls")
	@Override
	public boolean saveSocialMediaLinks(SocialMediaLink socialMediaLinkInfo) {
		boolean processed = false;
		try {
			List < SocialMediaLink > links = this.socialMediaLinkDAO.checkForExistanceByCompanyIdAndLinkTypeId(
					socialMediaLinkInfo.getFkCompanyId(), socialMediaLinkInfo.getFkLinkTypeInfoId());
			if (links != null && links.isEmpty()) {
				this.socialMediaLinkDAO.save(socialMediaLinkInfo);
			} else {
				// this.socialMediaLinkDAO.UpdateSocialMediaLink(socialMediaLinkInfo.getFkCompanyId(),
				// socialMediaLinkInfo);
			}
			processed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processed;
	}

	@Override
	public void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();
	}

	@Override
	public  String getCategoryID(JSONObject jsonObject) {
		String categoryId="";
		try {
			JSONArray includeArray = jsonObject.getJSONArray("included");
			int arraySize = includeArray.length();
			if (arraySize > 0) {
				for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
					try {
						JSONObject innerObject = includeArray.getJSONObject(arrayIndex);
						if (isValid(innerObject, "localizedName")) {
							String categoryName = innerObject.get("localizedName").toString();
							if (categoryName != null &&categoryName != "") {
								categoryId =categoryName;
							} 
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryId;
	}

	@SuppressWarnings("nls")
	@Override
	public boolean saveSocialMediaLinks1(SocialMediaLink socialMediaLinkInfo) {
		boolean processed = false;
		try {
			List < SocialMediaLink > links = this.socialMediaLinkDAO.checkForExistanceByCompanyIdAndLinkTypeId(
					socialMediaLinkInfo.getFkCompanyId(), socialMediaLinkInfo.getFkLinkTypeInfoId());
			if (links != null && links.isEmpty()) {
				this.socialMediaLinkDAO.save(socialMediaLinkInfo);
			} else {
				// this.socialMediaLinkDAO.UpdateSocialMediaLink(socialMediaLinkInfo.getFkCompanyId(),
				// socialMediaLinkInfo);
			}
			processed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processed;
	}

}
