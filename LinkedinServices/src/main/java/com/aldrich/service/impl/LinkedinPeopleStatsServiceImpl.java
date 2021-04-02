package com.aldrich.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.aldrich.dao.LinkedinPeopleStatsDAO;
import com.aldrich.dao.SocialMediaLinkDAO;
import com.aldrich.entity.PeopleStatsInfo;
import com.aldrich.service.LinkedinPeopleStatsService;

@Service
public class LinkedinPeopleStatsServiceImpl implements LinkedinPeopleStatsService {

	@Autowired
	SocialMediaLinkDAO socialMediaLinkDAO;

	@Autowired
	LinkedinPeopleStatsDAO linkedinPeopleStatsDAO;

	@Override
	public void runService() {
		int count = 0;
		String resp = "";
		WebDriver loginDriver = null;
		WebDriver companyPage = null;
		List<LinkedinInputBO> linkedinBOList = null;
		try {
			linkedinBOList = this.linkedinPeopleStatsDAO.getLinkedinLinks();
			if (linkedinBOList != null && !linkedinBOList.isEmpty()) {
				loginDriver = this.getLoginDriver();
				if (loginDriver != null) {
					for (LinkedinInputBO linkedinBO : linkedinBOList) {
						try {

							Thread.sleep(3000);
							companyPage = this.getCompanyPage(loginDriver, linkedinBO.getLinkedinURL());
							if (companyPage != null) {
								resp = companyPage.getPageSource();
								if (!resp.trim().toLowerCase().contains("this page is not available")) {
									processResponse(resp,linkedinBO.getPaseID());			
								}							
							}
							count = count + 1;
							if (count == 100) {
								loginDriver.navigate().to("https://www.linkedin.com/m/logout/");
								loginDriver.close();
								loginDriver = this.getLoginDriver();
								count = 0;
								Thread.sleep(9000);
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
			driver.findElement(By.cssSelector("input[name='session_key']")).sendKeys("sathyamputtala2005@gmail.com");
			driver.findElement(By.cssSelector("input[name='session_password']")).sendKeys("s@tyam007");
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

		String loginUrl = "https://www.linkedin.com/company/" + linkedinURL + "/people/";
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
	@Override
	public  void processResponse(String res,Long companyId) {

		Document document=null;
		String employeecount = null;
		try{		
			document = Jsoup.parse(res, "UTF-8");
			employeecount=getEmployeeCount(document);
			getNameValueTyoe(document,employeecount,companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public  void getNameValueTyoe(Document document,String employeecount,Long companyId) {
		String name = "";
		String value = "";
		String type ="";
		System.out.println("******************************************************************************");
		Elements div_elements = document.select("div");
		if (div_elements != null && div_elements.size() > 0) {
			for (Element element : div_elements) {
				try {
					if (element.hasAttr("class") && element.attr("class").equals("insight-container")) {
						if (element.childNodes().size() > 0) {
							for (int count = 0; count < element.childNodes().size(); count++) {
								try {
									if (element.child(count).hasAttr("class") && element.child(count)
											.attr("class").equals("insight-container__title")) {
										type = element.child(count).text().replace("Add", "").trim();
									}
						
									if (element.child(count).hasAttr("class")
											&& element.child(count).attr("class").equals(
													"org-people-bar-graph-element mt4 org-people-bar-graph-element--is-selectable ")) {
										if (element.child(count).childNodes().size() > 0) {
											Element inner = element.child(count).child(0);
											if (inner.nodeName().equals("div") && inner.hasAttr("class")
													&& inner.attr("class").equals(
															"org-people-bar-graph-element__percentage-bar-info truncate full-width mt2 mb1 t-14 t-black--light t-normal")) {

												if (inner.childNodes().size() > 0) {
													for (int innercount = 0; innercount < inner.childNodes()
															.size(); innercount++) {
														try {
															if (inner.child(innercount).text() != null) {
																if (inner.child(innercount).nodeName()
																		.equals("strong")) {
																	value = inner.child(innercount).text();
																}
																if (inner.child(innercount).nodeName()
																		.equals("span")) {
																	name = inner.child(innercount).text();
																}
															}
														} catch (Exception e) {
															// e.printStackTrace();
														}
													}
												}
											}

											System.out.println(value);
											System.out.println(name);
											System.out.println(type);
											System.out.println(employeecount);

											saveInfo(value,name,type,employeecount,companyId);
										}
									}	
								} catch (Exception ex) {
									//ex.printStackTrace();
								}
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}	
		System.out.println("******************************************************************************");
	}
	
	@Override
	public  void saveInfo(String value, String name, String type, String employeecount,long companyId) {

		long categoryId=0;
		PeopleStatsInfo statsInfo = new PeopleStatsInfo();
		statsInfo.setFk_company_id((int)companyId);
		if (employeecount != null) {
			statsInfo.setTotal_emp_count(
					Integer.parseInt(employeecount));
		}
		if (name != null) {
			statsInfo.setName(name);
		}
		if (value != null) {
			String empSize = "N/A";
			statsInfo.setValue(Integer.parseInt(value));
			if (categoryId == 1) {
				double empProj = 0;
				if (Integer.parseInt(value) > 100) {
					empProj = Integer.parseInt(value) * 1.25;
				} else {
					empProj = Integer.parseInt(value) * 2.50;
				}
				if (empProj > 50.0) {
					int empProjInt = (int) empProj;
					int interval = empProjInt / 50;
					int minValue = interval * 50;
					int maxvalue = minValue + 50;
					empSize = String.valueOf(minValue + 1) + "-"+ String.valueOf(maxvalue);
				} else {
					empSize = getEmpSize(empProj);
				}
			} else {
				double empProj = 0;
				empProj = Integer.parseInt(value) * 1.20;
				if (empProj > 50.0) {
					int empProjInt = (int) empProj;
					int interval = empProjInt / 50;
					int minValue = interval * 50;
					int maxvalue = minValue + 50;
					empSize = String.valueOf(minValue + 1) + "-"
							+ String.valueOf(maxvalue);
				} else {
					empSize = getEmpSize(empProj);
				}
			}
			statsInfo.setEmp_size(empSize);
		}
		if (type != null) {
			statsInfo.setType(type);
		}
		statsInfo.setSource_name("linkedin");
		statsInfo.setActivity_datetime(new Date());

		if (statsInfo.getType().equals("Where they live")) {
			if (statsInfo.getName().equals("United States")|| statsInfo.getName().equals("United Kingdom")|| statsInfo.getName().equals("Canada")|| statsInfo.getName().equals("US")) {
				statsInfo.setGroup_name("us_canada");
			} else if (statsInfo.getName().equals("Pakistan")
					|| statsInfo.getName().equals("India")
					|| statsInfo.getName().equals("Bangladesh")
					|| statsInfo.getName().equals("Nepal")) {
				statsInfo.setGroup_name("indian_sub_continent");
			} else if (statsInfo.getName().equals("Indonesia")
					|| statsInfo.getName().equals("Philippines")
					|| statsInfo.getName().equals("China")) {
				statsInfo.setGroup_name("south_east_asia");
			} else if (statsInfo.getName().equals("Romania")
					|| statsInfo.getName().equals("Ukraine")
					|| statsInfo.getName().equals("Russian Federation")
					|| statsInfo.getName().equals("Moldova")
					|| statsInfo.getName().equals("Montenegro")
					|| statsInfo.getName().equals("Belarus")
					|| statsInfo.getName().equals("Bulgaria")
					|| statsInfo.getName().equals("Greece")
					|| statsInfo.getName().equals("Serbia")
					|| statsInfo.getName().equals("Georgia")) {
				statsInfo.setGroup_name("eastern_europe");
			} else {
				boolean status = this.getAvailStatus(statsInfo.getName().trim());
				if (status == true) {
					statsInfo.setGroup_name("others");
				} else {
					statsInfo.setGroup_name("N/A");
				}
			}
		}
		double empl_count = 0.0;
		double value_count = 0.0;
		double per_value = 0.0;
		if (employeecount != null && value != null) {
			empl_count = Double.parseDouble(employeecount);
			value_count = Double.parseDouble(value);
			per_value = (value_count / empl_count) * 100;
			statsInfo.setPer_value(per_value);
		}

		List<PeopleStatsInfo> list = this.socialMediaLinkDAO.checkForExistanceByCompanyId((int)companyId, name, type);
		if (list.size() == 0) {
			this.socialMediaLinkDAO.save(statsInfo);
		} else {
			this.socialMediaLinkDAO.updateDetails(statsInfo);
		}

	}
	
	@Override
	public  String getEmpSize(double empProj) {
		String empSize;
		if (empProj <= 5.0) {
			empSize = "1" + "-" + "5";
		} else if (empProj > 5.0 && empProj <= 10.0) {
			empSize = "6" + "-" + "10";
		} else if (empProj > 10.0 && empProj <= 25.0) {
			empSize = "11" + "-" + "25";
		} else if (empProj > 25.0 && empProj <= 50.0) {
			empSize = "26" + "-" + "50";
		} else {
			empSize = "N/A";
		}
		return empSize;
	}

	

	@Override
	public  String  getEmployeeCount(Document document) {
		String employeecount="";
		Elements span_elements = document.select("span");
		if (span_elements != null && span_elements.size() > 0) {
			for (Element element : span_elements) {
				try {
					if (element.hasAttr("class") && element.attr("class").equals("t-20 t-black t-bold")) {
						employeecount = element.text().replace("employees", "").replace(",", "")
								.replace("employee", "").replace(" alumni", "").trim();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return employeecount;
	}


	@Override
	public  boolean getAvailStatus(String name) {
		boolean processed = false;
		try {
			ArrayList<String> arr = new ArrayList<String>(146);
			arr.add("Afghanistan");
			arr.add("Albania");
			arr.add("Algeria");
			arr.add("Andorra");
			arr.add("Angola");
			arr.add("Antigua and Barbuda");
			arr.add("Argentina");
			arr.add("Armenia");
			arr.add("Australia");
			arr.add("Austria");
			arr.add("Azerbaijan");
			arr.add("Bahrain");
			arr.add("Barbados");
			arr.add("Belgium");
			arr.add("Belize");
			arr.add("Benin");
			arr.add("Bhutan");
			arr.add("Bolivia");
			arr.add("Bosnia and Herzegovina");
			arr.add("Botswana");
			arr.add("Brazil");
			arr.add("Burkina Faso");
			arr.add("Burundi");
			arr.add("Cambodia");
			arr.add("Cameroon");
			arr.add("Central African Republic");
			arr.add("Chad");
			arr.add("Chile");
			arr.add("Colombia");
			arr.add("Costa Rica");
			arr.add("Croatia");
			arr.add("Cuba");
			arr.add("Cyprus");
			arr.add("Czech Republic");
			arr.add("Denmark");
			arr.add("Djibouti");
			arr.add("Dominica");
			arr.add("Dominican Republic");
			arr.add("Ecuador");
			arr.add("Egypt");
			arr.add("El Salvador");
			arr.add("Equatorial Guinea");
			arr.add("Estonia");
			arr.add("Ethiopia");
			arr.add("Fiji");
			arr.add("Finland");
			arr.add("France");
			arr.add("Gabon");
			arr.add("Germany");
			arr.add("Ghana");
			arr.add("Grenada");
			arr.add("Guatemala");
			arr.add("Guinea");
			arr.add("Guyana");
			arr.add("Haiti");
			arr.add("Honduras");
			arr.add("Hungary");
			arr.add("Iceland");
			arr.add("Iran");
			arr.add("Iraq");
			arr.add("Ireland");
			arr.add("Israel");
			arr.add("Italy");
			arr.add("Jamaica");
			arr.add("Japan");
			arr.add("Jordan");
			arr.add("Kazakhstan");
			arr.add("Kenya");
			arr.add("Kosovo");
			arr.add("Kuwait");
			arr.add("Kyrgyzstan");
			arr.add("Laos");
			arr.add("Latvia");
			arr.add("Lebanon");
			arr.add("Lesotho");
			arr.add("Liberia");
			arr.add("Libya");
			arr.add("Lithuania");
			arr.add("Luxembourg");
			arr.add("Madagascar");
			arr.add("Malawi");
			arr.add("Malaysia");
			arr.add("Maldives");
			arr.add("Mali");
			arr.add("Malta");
			arr.add("Mauritania");
			arr.add("Mauritius");
			arr.add("Monaco");
			arr.add("Mongolia");
			arr.add("Morocco");
			arr.add("Mozambique");
			arr.add("Namibia");
			arr.add("Netherlands");
			arr.add("New Zealand");
			arr.add("Nicaragua");
			arr.add("Niger");
			arr.add("Nigeria");
			arr.add("Norway");
			arr.add("Panama");
			arr.add("Papua New Guinea");
			arr.add("Paraguay");
			arr.add("Peru");
			arr.add("Poland");
			arr.add("Portugal");
			arr.add("Qatar");
			arr.add("Rwanda");
			arr.add("Saint Kitts and Nevis");
			arr.add("Saint Lucia");
			arr.add("Saint Vincent and the Grenadines");
			arr.add("Saudi Arabia");
			arr.add("Senegal");
			arr.add("Seychelles");
			arr.add("Sierra Leone");
			arr.add("Singapore");
			arr.add("Slovenia");
			arr.add("Solomon Islands");
			arr.add("Somalia");
			arr.add("South Africa");
			arr.add("Spain");
			arr.add("Sri Lanka");
			arr.add("Sudan");
			arr.add("Suriname");
			arr.add("Sweden");
			arr.add("Switzerland");
			arr.add("Syria");
			arr.add("Taiwan");
			arr.add("Tajikistan");
			arr.add("Tanzania");
			arr.add("Thailand");
			arr.add("Togo");
			arr.add("Tonga");
			arr.add("Trinidad and Tobago");
			arr.add("Tunisia");
			arr.add("Turkey");
			arr.add("Turkmenistan");
			arr.add("Uganda");
			arr.add("United Arab Emirates");
			arr.add("Uruguay");
			arr.add("Uzbekistan");
			arr.add("Vanuatu");
			arr.add("Venezuela");
			arr.add("Vietnam");
			arr.add("Yemen");
			arr.add("Zambia");
			arr.add("Zimbabwe");
			processed = arr.contains(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processed;
	}

}
