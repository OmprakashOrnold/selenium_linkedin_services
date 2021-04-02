package com.aldrich.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldrich.BO.LinkedinInputBO;
import com.aldrich.dao.SocialMediaLinkDAO;
import com.aldrich.entity.CompanyEmployeeProfile;
import com.aldrich.service.LinkedinEmployeeService;

@Service
public class LinkedinEmployeeServiceImpl implements LinkedinEmployeeService {
	@Autowired
	SocialMediaLinkDAO socialMediaLinkDAO;


	@Override
	public void runService() {
		int count = 1;
		WebDriver chromeDriver = null;
		WebDriver loginDriver = null;
		WebDriver companyPage = null;
		int scrollLimit = 10;
		List<LinkedinInputBO> linkedinBOList = null;
		try {
			linkedinBOList = this.socialMediaLinkDAO.getEmployeeUniqueIdAndFkCompanyId();
			if (linkedinBOList != null && !linkedinBOList.isEmpty()) {
				chromeDriver = this.setChromeDriver();
				if (chromeDriver != null) {
					loginDriver = this.loginLinkedinWebsite(chromeDriver);
					if (loginDriver != null) {

						for (LinkedinInputBO linkedinBO : linkedinBOList) {

							try {
								companyPage = this.getCompanyPage(loginDriver, linkedinBO.getUnique_id());

								processCompanyPages(companyPage, scrollLimit, linkedinBO);

							} catch (Exception e) {
								e.getMessage();
							}
							System.out.println(count++ + " File is downloaded Succussfully");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void processCompanyPages(WebDriver companyPage, int scrollLimit, LinkedinInputBO linkedinBO){

		String resp=null;
		try {
			if (companyPage != null) {

				CompanyEmployeeProfile companyEmployeeProfile = new CompanyEmployeeProfile();
				companyEmployeeProfile.setActive(0);
				this.socialMediaLinkDAO.updateActiveStatus(companyEmployeeProfile ,linkedinBO.getPaseID());


				List<WebElement> myIput = companyPage.findElements(
						By.xpath("//ol[@class='search-results__pagination-list']//li"));


				if (!myIput.isEmpty()) {
					for (int i = 0; i < myIput.size() + 10; i++) {
						Long pasId = linkedinBO.getPaseID();

						if (i >= 1) {
							int j = i + 1;

							String value = "Navigate to page " + j + "";
							WebElement element = companyPage.findElement(By.cssSelector("button[aria-label='" + value + "']"));
							Actions actions = new Actions(companyPage);

							actions.moveToElement(element).click().build().perform();

							// scroll to page to load
							scrollPage(companyPage, scrollLimit);

							// after load page get response
							resp = companyPage.getPageSource();
							if (!resp.equals("")) {
								processHtmlText(resp, pasId);
							}
						} else {
							Thread.sleep(1000);
							scrollPage(companyPage, scrollLimit);

							// after load page get response
							resp = companyPage.getPageSource();
							if (!resp.equals("")) {

								processHtmlText(resp, pasId);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void scrollPage(WebDriver companyPage, int scrollLimit)  {
		
		try {
			for (int k = scrollLimit; k >= 0; k--) {
				JavascriptExecutor js = (JavascriptExecutor) companyPage;
				js.executeScript("window.scrollBy(0,350)", "");
				Thread.sleep(500);
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
			options.addArguments("--window-size=2000,6000");
			System.setProperty("webdriver.chrome.driver", "C:\\Om\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	// running
	@Override
	public WebDriver loginLinkedinWebsite(WebDriver driver) {
		String loginUrl = "https://www.linkedin.com/login";
		try {
			driver.get(loginUrl);
			driver.findElement(By.cssSelector("input[name='session_key']")).sendKeys("nazeer.md20@gmail.com");
			driver.findElement(By.cssSelector("input[name='session_password']")).sendKeys("141265518");
			driver.findElement(By.cssSelector("button[aria-label='Sign in']")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public WebDriver getCompanyPage(WebDriver driver, String unique_id) {

		String loginUrl = "https://www.linkedin.com/sales/search/people/list/employees-for-account/" + unique_id;
		try {
			driver.navigate().to(loginUrl);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	@Override
	public void processHtmlText(String resp, Long paseID) {

		Document doc = null;
		doc = Jsoup.parse(resp);
		int count = 1;

		if (doc != null) {

			CompanyEmployeeProfile companyEmployeeProfile = new CompanyEmployeeProfile();
			Elements div_element = doc.select("article");
			for (Element element : div_element) {
				try {

					String person_name = getPersonName(element).replace("'", "");
					String profile_url = getProfileUrl(element);
					String img_url = getPersonImageUrl(element);
					// String comapny_name=getCompanyName(element).replace("'",
					// "");
					String duration = getDuration(element);
					Date joining_date = getDateFromYearMonthText(duration);
					String degree_conn = getDegreeConnection(element);
					String designstion = getDesgination(element).replace("'", "");
					String location = getLocation(element);


					String[] arrOfStr = location.split(", ");

					String city = "";
					String State = "";
					String country = "";

					if (arrOfStr.length == 3) {
						if (!arrOfStr[0].equals("")) {
							city = arrOfStr[0].trim();
						}
						if (!arrOfStr[1].equals("")) {
							State = arrOfStr[1].trim();
						}
						if (!arrOfStr[2].equals("")) {
							country = arrOfStr[2].trim();
						}
					} else {
						if (!arrOfStr[0].equals("")) {
							country = arrOfStr[0].trim();
						}
					}

					System.out.println("---------------------------------------------");
					System.out.println(count++ + "-->" + "person_name       :   " + person_name);
					System.out.println("---------------------------------------------");


					companyEmployeeProfile.setFk_company_id(paseID);
					companyEmployeeProfile.setFull_name(person_name);
					companyEmployeeProfile.setTitle(designstion);
					companyEmployeeProfile.setCity(city);
					companyEmployeeProfile.setState(State);
					companyEmployeeProfile.setCountry(country);
					companyEmployeeProfile.setProfileUrl("https://www.linkedin.com" + profile_url);
					companyEmployeeProfile.setPersonImageUrl(img_url);
					companyEmployeeProfile.setDegreeConn(degree_conn);
					companyEmployeeProfile.setDuration(duration);
					companyEmployeeProfile.setJoiningDate(joining_date);
					companyEmployeeProfile.setActivityDatetime(new Date());

					List<Object[]> empNameInfoList = this.socialMediaLinkDAO.getEmployeeFullName(person_name, paseID,designstion
							);
					if (empNameInfoList.size() > 0) {
						companyEmployeeProfile.setActive(1);
						this.socialMediaLinkDAO.updateActiveStatusByMemberLevel(companyEmployeeProfile,paseID,person_name,designstion);
					} else {
						long employeeId = 0;
						employeeId = (Long) this.socialMediaLinkDAO.save(companyEmployeeProfile);
						String logourl = "N/A";
						if (companyEmployeeProfile.getPersonImageUrl() != null) {
							logourl = companyEmployeeProfile.getPersonImageUrl();
							if (!logourl.equals("N/A") && !logourl.equals("")) {
								String destinationFile = "C:\\OmPrakash\\employee_logo\\" + employeeId + ".jpg";
								this.saveImage(logourl, destinationFile);
							}
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public String getPersonName(Element element) {
		String personName = "";
		try {
			Elements pNameElments = element.select("dt[class='result-lockup__name']");
			for (Element pNameElment : pNameElments) {
				try {
					personName = pNameElment.text();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personName;
	}

	@Override
	public String getProfileUrl(Element element) {
		String profile_url = "";
		try {
			Elements pNameElments = element.select("dt[class='result-lockup__name']");
			for (Element pNameElment : pNameElments) {
				try {
					profile_url = pNameElment.getElementsByTag("a").attr("href");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile_url;
	}

	@Override
	public String getPersonImageUrl(Element element) {
		String image_url = "";
		try {
			Elements imageElments = element.select("div[class='presence-entity--size-4 relative mr2']");
			for (Element imageElment : imageElments) {
				try {
					image_url = imageElment.getElementsByTag("img").attr("src");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image_url;
	}

	@Override
	public String getCompanyName(Element element) {
		String comapny_name = "";
		try {
			Elements cNameElments = element.select("span[aria-hidden='true']");
			for (Element cNameElment : cNameElments) {
				try {
					if (!cNameElment.text().contains("3rd") || !cNameElment.text().contains("2rd")
							|| !cNameElment.text().contains("1rd")) {
						comapny_name = cNameElment.text();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comapny_name;
	}

	@Override
	public String getDesgination(Element element) {
		String designstion = "";
		try {
			Elements designstionElments = element.select("span[class='t-14 t-bold']");
			for (Element designstionElment : designstionElments) {
				try {
					designstion = designstionElment.text();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return designstion;
	}

	@Override
	public String getDuration(Element element) {
		String duration = "";
		try {
			Elements durationElments = element.select("span[class='t-12 t-black--light']");
			for (Element durationElment : durationElments) {
				try {
					String durationArray[] = durationElment.text().split("in");
					duration = durationArray[0].trim();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duration;
	}

	@Override
	public String getLocation(Element element) {
		String location = "";
		Elements locationElments = element.select("li[class='result-lockup__misc-item']");
		try {
			for (Element locationElment : locationElments) {
				try {
					location = locationElment.text();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	@Override
	public String getDegreeConnection(Element element) {
		String degreeConn = "";
		try {
			Elements degreeConnElments = element.select("span[class='label-16dp block']");
			for (Element degreeConnElment : degreeConnElments) {
				try {
					degreeConn = degreeConnElment.text();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return degreeConn;
	}

	@Override
	public Date getDateFromYearMonthText(String yearMonthText) {
		Date date = null;
		try {
			long spaces = yearMonthText.trim().chars().filter(c -> c == ' ').count();
			if (spaces == 1) {
				date = getSingleMonthYearConvertedDate(yearMonthText);
			}

			if (spaces == 3) {
				date = getMonthYearCombineConvertedDate(yearMonthText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Date getMonthYearCombineConvertedDate(String yearMonthText) {
		Date date = null;
		String months_info = null;
		String years_info = null;
		String yearWithSorWithoutS = null;
		try {

			if (yearMonthText.contains("months") || yearMonthText.contains("month") || yearMonthText.contains("years")
					|| yearMonthText.contains("year")) {
				months_info = yearMonthText;
				years_info = yearMonthText;
				months_info = months_info.replace("months", "").trim().replace("month", "").trim();
				String yearTextArray[] = months_info.split(" ");
				yearWithSorWithoutS = yearTextArray[1].trim();
				if (months_info.contains(yearWithSorWithoutS)) {
					date = getConvertedMonthYearFromCalender(months_info, years_info, yearWithSorWithoutS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Date getSingleMonthYearConvertedDate(String yearMonthText) {
		Date date = null;
		String monthWithSorWithoutS = null;
		try {
			String monthTextArray[] = yearMonthText.split(" ");
			monthWithSorWithoutS = monthTextArray[1].trim();

			if (yearMonthText.contains("month")) {
				if (yearMonthText.contains(monthWithSorWithoutS)) {

					date = getConvertedMonthFromCalender(yearMonthText, monthWithSorWithoutS);
				}
			}

			if (yearMonthText.contains("year")) {
				if (yearMonthText.contains(monthWithSorWithoutS)) {
					date = getConvertedYearFromCalender(yearMonthText, monthWithSorWithoutS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public Date getConvertedMonthYearFromCalender(String months_info, String years_info, String yearWithSorWithoutS) {

		int months_count = 0;
		int years_count = 0;
		Date currentDate = null;
		Date actualDate = null;

		try {
			String months[] = months_info.split(yearWithSorWithoutS);
			months_count = Integer.parseInt(months[1].trim());
			years_info = years_info.replace("years", "").trim().replace("year", "").trim();
			years_count = Integer.parseInt(months[0].trim());

			currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.YEAR, -years_count);
			c.add(Calendar.MONTH, -months_count);
			actualDate = c.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualDate;
	}

	@Override
	public Date getConvertedYearFromCalender(String yearMonthText, String monthWithSorWithoutS) {

		String month_info = null;
		Date currentDate = null;
		Date actualDate = null;
		try {

			month_info = yearMonthText;
			month_info = month_info.replace(monthWithSorWithoutS, "").trim();
			int month_count = Integer.parseInt(month_info);
			currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.YEAR, -month_count);
			actualDate = c.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualDate;
	}

	@Override
	public Date getConvertedMonthFromCalender(String yearMonthText, String monthWithSorWithoutS) {

		String year_info = null;
		Date currentDate = null;
		Date actualDate = null;

		try {
			year_info = yearMonthText;
			year_info = year_info.replace(monthWithSorWithoutS, "").trim();
			int months_count = Integer.parseInt(year_info);
			currentDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.MONTH, -months_count);
			actualDate = c.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualDate;
	}

	@Override
	public void saveImage(String imageUrl, String destinationFile) throws Exception {
		try {
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
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

}
