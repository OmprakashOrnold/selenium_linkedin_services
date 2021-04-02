package com.aldrich.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.JobsDAO;
import com.aldrich.dao.SocialMediaLinkDAO;
import com.aldrich.entity.Jobs;
import com.aldrich.service.LinkedinJobsService;
import com.aldrich.util.PASEConstants;

@Service
public class LinkedinJobsServiceImpl implements LinkedinJobsService{

	@Autowired
	SocialMediaLinkDAO socialMediaLinksDAO;
	
	@Autowired
	JobsDAO jobsDAO;


	final SimpleDateFormat activityDateSdf = new SimpleDateFormat("yyyy-MM-dd");
	final String activityDateStr = activityDateSdf.format(new Date());

	@Override
	public void runService() {

		final DateFormat utcFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		utcFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));


		WebDriver loginDriver=null;
		WebDriver chromeDriver = null;
		chromeDriver=setChromeDriver();
		if(chromeDriver!=null){
			loginDriver=loginLinkedinWebsite(chromeDriver);
			if(loginDriver!=null){		   
				List<LinkedinInputBO> linkedinBOList = null;
				try {
					linkedinBOList = this.socialMediaLinksDAO.getEmployeeUniqueNameAndFkCompanyId();
					if (linkedinBOList != null && !linkedinBOList.isEmpty()) {
						loginAndExtractData(loginDriver, linkedinBOList);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public void loginAndExtractData(WebDriver loginDriver, List<LinkedinInputBO> linkedinBOList) {
		WebDriver companyPage=null;
		try{
			if (loginDriver != null) {
				for (LinkedinInputBO linkedinBO : linkedinBOList) {
					try {
						companyPage=getCompanyPage(loginDriver,linkedinBO.getLinkedinURL());
						processCompanyPage(companyPage, linkedinBO);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void processCompanyPage(WebDriver companyPage, LinkedinInputBO linkedinBO) {

		String res=null;
		Long companyId=linkedinBO.getPaseID();
		try{
			By inputArea1 = By.xpath("//a[@class='link-without-hover-visited mt5 ember-view']");
			if(isElementPresent(companyPage,inputArea1))
			{
				WebElement element = companyPage.findElement(inputArea1);
				System.out.println(element.getAttribute("href"));
				element.click();
				Thread.sleep(5000);
				pageNations(companyPage);
				res=companyPage.getPageSource();
				if(!res.equals(""))
				{
					processResponse(res,companyId);
					
					Writer writer;
					try {
						writer = new FileWriter(
								"C:\\jobs_linkedin\\" +linkedinBO.getPaseID()+ ".html");
						BufferedWriter bufferedWriter = new BufferedWriter(writer);
						bufferedWriter.write(res);
						bufferedWriter.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				processNextPages(companyPage ,companyId,linkedinBO);
			}
		}catch(Exception e){
			e.printStackTrace();

		}
	}

	public void processNextPages(WebDriver companyPage,Long companyId,LinkedinInputBO linkedinBO)  {
		String res=null;
		try{
			By inputArea3 = By.xpath("//button[@aria-label='Page "+2+"']");
			if(isElementPresent(companyPage,inputArea3)){
				for (int j = 2; j <=10; j++) {
					By inputArea2 = By.xpath("//button[@aria-label='Page "+j+"']");
					if(isElementPresent(companyPage,inputArea2)){
						WebElement element2 = companyPage.findElement(inputArea2);
						element2.click();
						Thread.sleep(5000);
						pageNations(companyPage);
						res=companyPage.getPageSource();
						if(!res.equals(""))
						{		
							processResponse(res,companyId);
							Writer writer;
							try {
								writer = new FileWriter(
										"C:\\jobs_linkedin\\" +linkedinBO.getPaseID()+"-"+j+ ".html");
								BufferedWriter bufferedWriter = new BufferedWriter(writer);
								bufferedWriter.write(res);
								bufferedWriter.close();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void pageNations(WebDriver companyPage){
		try
		{
			for (int i =300; i <=3000; i=i+300) {
				EventFiringWebDriver eventFiringWebDriver=new EventFiringWebDriver(companyPage);
				eventFiringWebDriver.executeScript("document.querySelector('div[class=\"jobs-search-results display-flex flex-column\"]').scrollTop="+i);
				Thread.sleep(1000);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public WebDriver setChromeDriver() {
		WebDriver driver = null;
		ChromeOptions options=null;
		try{
			options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--allow-insecure-localhost");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-notifications");
			options.addArguments("--start-maximized");
			options.addArguments("--window-size=2000,6000");
			options.setCapability("acceptSslCerts", true);
			options.setCapability("acceptInsecureCerts", true);	
			System.setProperty("webdriver.chrome.driver", "C:\\Om\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window();
		}
		catch(Exception e){
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
			driver.findElement(By.cssSelector("input[name='session_key']")).sendKeys("mudassir.ah@gmail.com");	
			driver.findElement(By.cssSelector("input[name='session_password']")).sendKeys("AcP_987654@");			
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
	public  WebDriver getCompanyPage(WebDriver driver,String linkedinURL)
	{
		int scrollLimit = 10;
		if (linkedinURL.contains("?"))
			linkedinURL = linkedinURL.substring(0, linkedinURL.indexOf("?"));
		String loginUrl = "https://www.linkedin.com/company/" + linkedinURL + "/jobs/";
		try {
			driver.navigate().to(loginUrl);
			scrollWebPage(driver, scrollLimit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public  void scrollWebPage(WebDriver driver, int scrollLimit) throws InterruptedException {
		for (int i = scrollLimit; i >= 0; i--) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(1000);
		}
	}

	@Override
	public  boolean isElementPresent(WebDriver driver ,By selector)
	{
		return driver.findElements(selector).size()>0;
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
	public  String getJobUrl(Element element) {
		String job_url = "";
		try{
			Elements divElement = element.select("div[class=full-width artdeco-entity-lockup__title ember-view]");
			if (divElement != null) {
				for (Element nameElement : divElement) {
					try {
						if (nameElement.children().hasAttr("data-control-id")) {
							//job_title = nameElement.text().toString().trim();
							job_url = "https://www.linkedin.com"+ nameElement.children().attr("href");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job_url;
	}

	@Override
	public  void processResponse(String resp,Long companyId) {

		Document document=null;
		// read the content from file
		try {
			document = Jsoup.parse(resp, "UTF-8");
			Elements liElement = document.select("li[class=jobs-search-results__list-item occludable-update p0 relative ember-view]");
			if (liElement != null && liElement.size() > 0) {
				for (Element element : liElement) {
					try {
						String job_title = getJobTitle(element);
						String job_url =    getJobUrl(element);
						String job_location = getJobLocation(element);
						String posted_date_1 = getJobPostedDateType1(element);
						String posted_date_2 = getPostedDateType2(element);
						String posted_date = null;

						if (job_title != null) {
							Jobs jobsInfo = new Jobs();
							jobsInfo.setCompanyId(companyId);
							jobsInfo.setJobtitle(job_title);
							jobsInfo.setType("Yes");
							if (job_location != null) {
								jobsInfo.setLocation(job_location);
								if (job_location != null && job_location.contains(",")) {
									String[] arrSplit_3 = job_location.split(",");
									if (arrSplit_3.length == 1) {
										jobsInfo.setCity(arrSplit_3[0].trim());
										jobsInfo.setState("");
										jobsInfo.setCountry("");
									}
									if (arrSplit_3.length == 2) {
										jobsInfo.setCity(arrSplit_3[0].trim());
										jobsInfo.setState(arrSplit_3[1].trim());
										jobsInfo.setCountry("");
									}
									if (arrSplit_3.length == 3) {
										jobsInfo.setCity(arrSplit_3[0].trim());
										jobsInfo.setState(arrSplit_3[1].trim());
										jobsInfo.setCountry(arrSplit_3[2].trim());
									}
								}
							}
							posted_date = getPostedDate(posted_date_1, posted_date_2);

							if (posted_date != null&&!posted_date.isEmpty()) {
								Date timestamp = new SimpleDateFormat("yyyy-MM-dd").parse(posted_date);
								jobsInfo.setPosted_datetime(new Date(timestamp.getTime()));
								jobsInfo.setPosted_date(activityDateSdf.format(new Date(timestamp.getTime())));
								jobsInfo.setPosted_datetime_temp(activityDateSdf.parse(activityDateSdf.format(new Date(timestamp.getTime()))));
							}

							jobsInfo.setDescription("Not Available");
							jobsInfo.setUrl(job_url);
							jobsInfo.setJobkey("");
							jobsInfo.setExpired("false");
							jobsInfo.setConfidence_score(1.0);
							jobsInfo.setServer_response("");
							jobsInfo.setSource_id(PASEConstants.LINKEDIN_SOURCE_ID);
							jobsInfo.setJob_daily_status_id(1L);
							jobsInfo.setActivity_datetime(new Date());
							jobsInfo.setActivity_date(activityDateStr);
							jobsInfo.setActivity_datetime_temp(activityDateSdf.parse(activityDateStr));
							jobsInfo.setTrigger("");
							jobsInfo.setCompanyName("");
							this.storeJobDetails(jobsInfo, companyId);
							// display the values here
							System.out.println("---------------------------------------------");
							System.out.println("Title       --->" + job_title);
							System.out.println("Location    --->" + job_location);
							System.out.println("postedDate1  --->" + posted_date_1);
							System.out.println("postedDate2  --->" + posted_date_2);
							System.out.println("jobUrl      --->" + job_url);
							System.out.println("---------------------------------------------");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getPostedDate(String posted_date_1, String posted_date_2) {
		String posted_date=null;
		try{
			if (posted_date_1 != null) {
				posted_date = posted_date_1;
			} else if (posted_date_2 != null) {
				posted_date = posted_date_2;
			} else {
				posted_date = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posted_date;
	}

	@Override
	public  String getJobTitle(Element element) {
		String job_title="";
		try{
			Elements divElement = element.select("div[class=full-width artdeco-entity-lockup__title ember-view]");
			if (divElement != null) {
				for (Element nameElement : divElement) {
					try {
						if (nameElement.children().hasAttr("data-control-id")) {
							job_title = nameElement.text().toString().trim();
							//job_url = "https://www.linkedin.com"+ nameElement.children().attr("href");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job_title;
	}

	@Override
	public  String getPostedDateType2(Element element) {
		String posted_date_2="";
		try{
			Elements divElement3 = element.select("ul[class=job-card-list__footer-wrapper job-card-container__footer-wrapper flex-shrink-zero display-flex t-sans t-12 t-black--light t-normal t-roman]");
			if (divElement3 !=null) {
				for (Element timeElement : divElement3) {
					try {
						if (timeElement.children().hasAttr("class")) {
							Elements dateElement = timeElement.children().select(
									"li[class=job-card-container__listed-time job-card-container__footer-item job-card-container__footer-item--highlighted t-bold]");
							for (Element dateElement1 : dateElement) {
								if (dateElement1.children().hasAttr("datetime")) {
									posted_date_2 = dateElement1.children().attr("datetime");

								}
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
		return posted_date_2;
	}

	@Override
	public  String getJobPostedDateType1(Element element) {
		String posted_date_1="";
		try{
			Elements divElement2 = element.select("ul[class=job-card-list__footer-wrapper job-card-container__footer-wrapper flex-shrink-zero display-flex t-sans t-12 t-black--light t-normal t-roman]");
			if (divElement2 != null) {
				for (Element timeElement : divElement2) {
					try {
						if (timeElement.children().hasAttr("class")) {
							Elements dateElement = timeElement.children().select("li[class=job-card-container__listed-time job-card-container__footer-item ]");
							for (Element dateElement1 : dateElement) {
								if (dateElement1.children().hasAttr("datetime")) {
									posted_date_1 = dateElement1.children().attr("datetime");
								}
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
		return posted_date_1;
	}

	@Override
	public  String getJobLocation(Element element) {
		String job_location="";
		try {
			Elements divElement1 = element.select("div[class=artdeco-entity-lockup__caption ember-view]");
			if (divElement1 != null) {
				for (Element placeElement : divElement1) {
					try {
						if (placeElement.children()
								.hasClass("job-card-container__metadata-wrapper")) {
							job_location = placeElement.children().text().toString().trim();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job_location;
	}

	@Override
	public boolean storeJobDetails(Jobs indeedJobs, Long companyId) {
		boolean saveFlag = false;
		try {
			if (this.jobsDAO.checkForExistance(indeedJobs) == null) {
				this.jobsDAO.save(indeedJobs);
				saveFlag = true;
			} else {
				saveFlag = false;
			}
		} catch (Exception excp) {
			excp.printStackTrace();
		}
		return saveFlag;
	}



}
