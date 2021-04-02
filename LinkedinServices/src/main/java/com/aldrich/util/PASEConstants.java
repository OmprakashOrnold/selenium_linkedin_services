package com.aldrich.util;

import java.util.Date;

@SuppressWarnings({ "nls", "boxing" })
public interface PASEConstants {

	public static final String PR_ZOOM = "PR_ZOOM";
	public static final String PR_URGENT_NEWS = "PR_URGENT_NEWS";
	public static final String ONLINE_PR_MEDIA = "ONLINE_PR_MEDIA";
	public static final String PR_NEWS_WIRE = "PR_NEWS_WIRE";

	// OLD SOURCE IDS
	public static final Integer PR_ZOOM_SOURCE_ID = 13;
	public static final Integer PR_URGENT_NEWS_SOURCE_ID = 14;
	public static final Integer ONLINE_PR_MEDIA_SOURCE_ID = 15;
	public static final Integer PR_NEWS_WIRE_SOURCE_ID = 16;
	public static final Integer FT_SOURCE_ID = 22;
	public static final Integer REUTERS_SOURCE_ID = 21;
	public static final Integer WSJ_SOURCE_ID = 20;
	public static final Integer TECHCRUNCH_SOURCE_ID = 19;
	public static final Integer VENTUREBEAT_SOURCE_ID = 23;
	public static final Integer FORBES_SOURCE_ID = 17;
	public static final Integer FEED_BURNER_SOURCE_ID = 18;

	// EXCEPTION CODES
	public static final Integer SUCCESS_EXCEPTION_CODE = 0;
	public static final Integer SOCKET_TIMEOUT_EXCEPTION = 28;
	public static final Integer IO_EXCEPTION = 29;
	public static final Integer MOLFORMED_EXCEPTION_CODE = 30;
	public static final Integer NULL_POINTER_EXCEPTION_CODE = 31;
	public static final Integer ARRAY_INDEX_BOUNDS_EXCEPTION_CODE = 32;
	public static final Integer JSON_EXCEPTION = 33;
	public static final Integer ANY_OTHER_EXCEPTION = 99;
	public static final Integer MAX_ATTEMPT_COUNT = 3;

	// EMPLOYEE COUNT & COMPETITORS
	public static final String LINKED_IN_CODE = "LN";
	public static final String CRUNCH_BASE_CODE = "CB";
	public static final Float LINKED_IN_DEFAULT_CONFIDENCE_SCORE = 0.8F;
	public static final Float CRUCNH_BASE_DEFAULT_CONFIDENCE_SCORE = 0.7F;
	public static final Float USER_DEFAULT_CONFIDENCE_SCORE = 0.9F;

	// COMPANY TYPES
	public static final Long TRACKING_COMPANY_TYPE_ID = 1L;
	public static final Long GENERIC_API_COMPANY_TYPE_ID = 2L;
	public static final Long THIRD_PARTY_NEWS_COMPANY_TYPE_ID = 3L;
	public static final Long IGNORE_COMPANY_TYPE_ID = 4L;
	public static final Long GENERIC_WEBPAGE_COMPANY_TYPE_ID = 5L;
	public static final Long PARTNER_WEBPAGE_COMPANY_TYPE_ID = 6L;
	public static final Long CUSTOMER_WEBPAGE_COMPANY_TYPE_ID = 7L;
	public static final Long COMPETITOR__COMPANY_TYPE_ID = 8L;
	public static final Long INVESTMENT_TYPE_ID = 9L;
	public static final Long DUPLICATE_COMPANY_TYPE_ID = 10L;
	public static final Long OWN_ALGORITHM_TYPE_ID = 11L;
	public static final Long EMP_COMPANY_ID = 12L;

	// DATA SOURCE TYPES
	public static final Long DATA_SOURCE_ALL = 1L;
	public static final Long DATA_SOURCE_INC_500 = 2L;
	public static final Long DATA_SOURCE_CRUNCH_BASE = 3L;
	public static final Long DATA_SOURCE_INTERNAL_CRM_SYSTEM = 4L;
	public static final Long DATA_SOURCE_OTHERS = 5L;
	public static final Long DATA_SOURCE_LINKEDIN = 6L;
	public static final Long DATA_SOURCE_MOBILE_APPS = 7L;
	public static final Long DATA_SOURCE_ANGEL_LIST = 8L;
	public static final Long DATA_SOURCE_QC = 9L;
	public static final Long DATA_SOURCE_SEED_DB = 10L;
	public static final Long VENTURE_CAPITAL = 11L;
	public static final Long DISCOVER_ORG_LIST = 12L;
	public static final Long TRACXN_LIST = 13L;
	public static final Long EMERGING_TECH_SUMMIT = 14L;
	public static final Long LATKA = 15L;
	public static final Long GREASLIST = 16L;
	public static final Long LOGISTICS_TECH_COMPANIES = 17L;
	public static final Long PITCH_BOOK = 18L;
	public static final Long GCSE = 19L;
	public static final Long APS = 20L;
	public static final Long Growjo = 22L;
	public static final Long DATA_SOURCE_MANUAL_GOOGLE = 23L;
	public static final Long CAPTERRA = 24L;
	public static final Long SOFTWARE_ADVISE = 25L;
	public static final Long EANDY = 26L;
	public static final Long OWLER = 27L;
	public static final Long G2_CROWD = 30L;
	public static final Long INSART_DATA_SOURCE_ID = 34L;

	// LINK TYPE INFO
	public static final Long LINK_TYPE_FB = 1L;
	public static final Long LINK_TYPE_TW = 2L;
	public static final Long LINK_TYPE_GOOGLE_PLUS = 3L;
	public static final Long LINK_TYPE_LINKED_IN = 4L;
	public static final Long LINK_TYPE_CRUNCH_BASE = 5L;
	public static final Long LINK_TYPE_YOUTUBE = 6L;
	public static final Long LINK_TYPE_RSS_FEED = 7L;
	public static final Long LINK_TYPE_GOOGLE_PLAY_STORE = 8L;
	public static final Long LINK_TYPE_FB_APPS_PLAY_STORE = 9L;

	// PATHS
	public static final String PATENT_LITIGATION_FILE_PATH = "/home/ubuntu/aldrich/excel_files/patent_litigation";
	public static final String INVESTORS_FILE_PATH = "/home/ubuntu/aldrich/excel_files/crunchbase/investors-04052016.xls";
	public static final String EMPLOYEE_CAREER_PATH = "/home/ubuntu/aldrich/excel_files/emp-career-data-set1.xls";
	public static final String IMPORT_CB_URL_PATH = "C:\\Satyam\\Others\\cb_links_2.xlsx";
	public static final String EMPLOYESS_FILE_PATH = "/home/ubuntu/aldrich/excel_files/emp-data-173-cos.xls";
	public static final String KEYWORDS_FILE_PATH = "/home/ubuntu/aldrich/excel_files/keywords.xml";
	public static final String OPENCALSIS_PARTNERS_FILE_PATH = "/home/ubuntu/aldrich/partners.txt";
	public static final String OPENCALSIS_CUSTOMERS_FILE_PATH = "/home/ubuntu/aldrich/customers.txt";
	public static final String OPENCALSIS_BOARD_MEMBERS_FILE_PATH = "/home/ubuntu/aldrich/boardmembers.txt";
	public static final String OPENCALSIS_TEST_FILE_PATH = "D:/test.txt";
	public static final String EMPLOYEE_LINKEDIN_INFORMATION_FILE_PATH = "/home/ubuntu/aldrich/university/";
	// public static final String EMPLOYEE_LINKEDIN_INFORMATION_FILE_PATH =
	// "C:\\Users\\Satyam\\Desktop\\uni\\";
	public static final String PUBLICATION_FILE_PATH = "/home/ubuntu/aldrich/excel_files/pub-data-set1.xls";

	// public static final String KEYWORDS_FILE_PATH = "D:\\keywords.xml";

	// public static final String NEW_COMPANY_SHEET_PATH =
	// "/home/ubuntu/softwares/new-company-list.xls";
	// public static final String NEW_COMPANY_SHEET_PATH =
	// "/home/satyam/pase_config/new-leads.xls";
	public static final String NEWS_ALERTS_SHEET_PATH = "C:\\Satyam\\News Triggers Info\\news_alerts_import.xls";
	public static final String NEW_COMPANY_SHEET_PATH = "C:\\Satyam\\Others\\add_company\\new_company_new.xls";
	public static final String SOCIAL_MEDIA_LINK_SHEET_PATH = "C:\\Satyam\\Others\\sm_links\\linkedin_links.xls";
	public static final String TAXONOMY_INFO_SHEET_PATH = "C:\\Satyam\\Taxonomy\\taxonomy_set_7.xls";
	public static final String INTEREST_CHECK_SHEET_PATH = "C:\\PASE\\Interest Check\\interest_check.xls";
	public static final String CONFERENCE_SHEET_PATH = "C:\\Satyam\\Others\\conf_info\\lpecsv.csv";
	public static final String COMPETITORS_SHEET_PATH = "C:\\Satyam\\Others\\competitor_info.xls";
	// public static final String KEYWORDS_FILE_PATH = "E:\\keywords.xml";

	public static final String CRUNCHBASE_ACQUISITION_PATH = "/home/satyam/pase_config/cb_data/acquisitions.csv";
	public static final String CRUNCHBASE_ORGANIZATION_PATH = "/home/satyam/pase_config/cb_data/organizations.csv";
	public static final String CRUNCHBASE_INVESTMENT_PATH = "/home/satyam/pase_config/cb_data/investments.csv";
	public static final String CRUNCHBASE_FUNDING_ROUND_PATH = "/home/satyam/pase_config/cb_data/funding_rounds.csv";
	public static final String CRUNCHBASE_CATEGORYGROUP_PATH = "/home/satyam/pase_config/cb_data/category_groups.csv";
	public static final String CRUNCHBASE_DEGREES_PATH = "/home/satyam/pase_config/cb_data/degrees.csv";
	public static final String CRUNCHBASE_EVENTAPPEARANCES_PATH = "/home/satyam/pase_config/cb_data/event_appearances.csv";
	public static final String CRUNCHBASE_EVENTS_PATH = "/home/satyam/pase_config/cb_data/events.csv";
	public static final String CRUNCHBASE_FUNDS_PATH = "/home/satyam/pase_config/cb_data/funds.csv";
	public static final String CRUNCHBASE_INVESTMENTPARTNERS_PATH = "/home/satyam/pase_config/cb_data/investment_partners.csv";
	public static final String CRUNCHBASE_INVESTOR_PATH = "/home/satyam/pase_config/cb_data/investors.csv";
	public static final String CRUNCHBASE_IPOS_PATH = "/home/satyam/pase_config/cb_data/ipos.csv";
	public static final String CRUNCHBASE_Jobs_PATH = "/home/satyam/pase_config/cb_data/jobs.csv";
	public static final String CRUNCHBASE_PEOPLE_PATH = "/home/satyam/pase_config/cb_data/people.csv";
	public static final String CRUNCHBASE_PEOPLE_DESCRIPTIONS_PATH = "/home/satyam/pase_config/cb_data/people_descriptions.csv";
	public static final String CRUNCHBASE_ORGANIZATION_DESCRIPTIONS_PATH = "/home/satyam/pase_config/cb_data/organization_descriptions.csv";

	public static final String PRODUCTS_EXCEL_SHEET_PATH = "C:\\Satyam\\import_templates\\import_product_info.xls";

	public static final String PRODUCT_SERVICE_PATH = "C:\\Satyam\\Product vs Service\\product_service_set1.csv";

	public static final String ACTIVE_STATUS_YES = "Y";

	public static final String CRUNCH_BASE_DOMAIN = "crunchbase.com";
	public static final String CRUNCH_BASE_NAME = "crunchbase";
	public static final String LINKED_IN_DOMAIN = "linkedin.com";
	public static final String LINKED_IN_NAME = "linkedin";

	public static final Long DEFAULT_USER_ID = 1L;
	public static final Long UNKNOWN_CATEGORY_TYPE_ID = 25L;

	// SOURCE IDS - STATIC
	public static final Long INDEED_SOURCE_ID = 267529L;
	public static final Long LINKEDIN_SOURCE_ID = 267530L;
	public static final Long PATENT_FILINGS_SOURCE_ID = 267699L;
	public static final Long ALDRICH_ALGORITHM = 270149L;
	public static final Long ZOOMINFO_REVENUE = 270150L;
	public static final Long INC5000_REVENUE = 270151L;
	public static final Long CRUNCHBASE_API_SOURCE_ID = 263976L;
	public static final Long GOOGLE_CSE_WBPAGE_SOURCE_ID = 270176L;
	public static final Long GOOGLE_APP_STORE_SOURCE_ID = 263968L;
	public static final Long ITUNES_APP_STORE_SOURCE_ID = 263969L;
	public static final Long TWITTER_SOURCE_ID = 400000L;
	public static final Long FACEBOOK_SOURCE_ID = 400001L;
	public static final Long EXHIBITRAC_SOURCE_ID = 400002L;
	public static final Long CONFERENCE_MANAGEMENT_SYSTEM_SOURCE_ID = 400004L;
	public static final Long NTRADE_SHOWS_SOURCE_ID = 400005L;
	public static final Long EVENTS_IN_AMERICA_SOURCE_ID = 400006L;
	public static final Long GOOGLE_SCHOLAR_SOURCE_ID = 426538L;
	public static final Long GLASS_DOOR_SOURCE_ID = 519813L;
	public static final Long WORKABLE_JOBS_SOURCE_ID = 369300L;
	public static final Long GCSE_SOURCE_ID = 19L;
	public static final Long PRNEWSWIRE_SOURCE_ID = 28L;
	public static final Long RESPECTIVE_WEBSITE_SOURCE_ID = 29L;
	public static final Long PRURGENT_SOURCE_ID = 31L;
	public static final Long BUSINESSWIRE_SOURCE_ID = 32L;
	public static final Long PRCOM_SOURCE_ID = 33L;

	public static final Long DEFENDANT_ID = 1L;
	public static final Long PLAINTIFF_ID = 2L;
	public static final Long UNKNOWN_COMPANY_ID = 316088L;
	public static final Long LINKEDIN_API_SOURCE_ID = 266374L;

	// STATIC URLS
	public static final String CRUNCHBASE_URL = "https://api.crunchbase.com/v3.1/organizations/";
	public static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=";
	public static final String LINKEDKIN_URL = "https://www.linkedin.com/company/";
	public static final String CRUNCHBASE_FUNDINGROUNDS_URL = "https://api.crunchbase.com/v/3/funding-rounds/";
	public static final String YOUTUBE_STAS_URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet%2CcontentDetails%2Cstatistics&id=";
	public static final String BING_WEB_SEARCH_URL = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%%27%s%%27&$format=JSON";

	// OPEN CALAIS
	public static final String CALAIS_URL = "https://api.thomsonreuters.com/permid/calais";

	// LINKEDIN PREMIUM ACCOUNT COOKIES
	public static final String LINKEDIN_PREMIUM_INSIGHTS_COOKIES = "lang='v=2&lang=en-us'; JSESSIONID='ajax:6545974309894766468'; bcookie='v=2&746f1fc7-97ba-4322-8b21-f2a410b39bed'; bscookie='v=1&20170116181829214d8291-18c6-43b2-8c53-f23a00999311AQGTiwNPFSrTNcgtnBONlzhLM4pUBdkB'; lidc='b=SGST03:g=3:u=1:i=1484590771:t=1484677171:s=AQF3YpDIIvi9V9pFF6RnzAAamUrJ34-T'; visit='v=1&M'; RT=s=1484590782656&r=https%3A%2F%2Fwww.linkedin.com%2Fuas%2Flogout%3Fsession_full_logout%3D%26csrfToken%3Dajax%253A0232219789825257743%26trk%3Dnav_account_sub_nav_signout; oz_props_fetch_size1_179163=15; wutan=HYxmee5Nq9X/1+nS21tjn3iUBfwvVm3V+d7zBPRA4lc=; share_setting=PUBLIC; sdsc=1%3A1SZM1shxDNbLt36wZwCgPgvN58iw%3D; sl='v=1&msTvD'; li_at=AQEDAQACu9sEObp7AAABWaiB4WYAAAFZqjlVZlEAcDLwJYDSxwMXr75P-37J8-GNzy27x3acd15rsbsrbXEeLBF77dTZKVclmWkih4eFg8JP8fBXlQxLBlXoys3ih-oEmoKaUDEVzSk2C-T2f6hF8Vim; liap=true";
	public static final String LINKEDIN_COOKIES = "'JSESSIONID='ajax:4120916639348474879'; bcookie='v=2&159eca75-ecb2-4344-8f3e-f070df445769'; bscookie='v=1&20170127101933721c387f-f850-4f47-8ad3-c132e2af3b14AQHqpplBmR97wKEYofJ3sQ1fCo7QmE3k'; visit='v=1&M'; lang='v=2&lang=en-us'; lidc='b=SB17:g=36:u=13:i=1485851397:t=1485858406:s=AQGmBk7eqducw7aWbhloepqnBSqenmrH'; _ga=GA1.2.2012556296.1485759283; wutan=q64DGOp/fiXTBjlLpzQlJbhTNvvEIGoGypCZ3RpO1wE=; sdsc=1%3A1SZM1shxDNbLt36wZwCgPgvN58iw%3D; share_setting=PUBLIC; _lipt=0_US4gxHw6tL3sOPU3lUmv3MwjCckPPmo5jUU1_tj4tu2228-FvC3FZwopNfJRvIXjpw8Wnh4AosOWsuSO5TDCbdBlV2lIJM_iK7HCmOuI-WwCwpU2kXu6ViGVbKA-cdBN8_JAoskuvId_wUUisqR9IBy6W-fMnfWgcCRmpB6CnhRMqHKkS_T-Y-wvQtwg8MQj; _gat=1; sl='v=1&G64vT'; li_at=AQEDAR9utw0CtLJBAAABWfOlCtkAAAFZ9Vx-2VEABLa36xcLDILG-0AHAQ5cETAdwqI9OCxJbVdz0-naG_nlA9nHvMQJI6Iq077MnP7s4KeJBMTMYkqb_Aje9VGQbw5f7Qvgx1PB2Cv4fObzNQR-S1_n; liap=true; RT=s=1485851393675&r=https%3A%2F%2Fwww.linkedin.com%2F; oz_props_fetch_size1_undefined=undefined'";

	// SERVICE IDs
	public static final Long TWITTER_SERVICE_ID = 1L;
	public static final Long FACEBOOK_SERVICE_ID = 2L;
	public static final Long YOUTUBE_SERVICE_ID = 3L;
	public static final Long YOUTUBE_VIDEO_STATS_SERVICE_ID = 4L;
	public static final Long LINKEDIN_FOLLOWERS_SERVICE_ID = 5L;
	public static final Long COMPETITOR_SERVICE_ID = 6L;
	public static final Long INVESTOR_PROFILE_SERVICE_ID = 7L;
	public static final Long INVESTOR_SCORE_CAL_SERVICE_ID = 8L;
	public static final Long INVESTOR_SYNDICATE_INDEX_SERVICE_ID = 9L;
	public static final Long INVESTOR_SYNDICATE_GROWTH_SERVICE_ID = 10L;
	public static final Long FUNDING_INDEX_SERVICE_ID = 11L;
	public static final Long REVENUE_SERVICE_ID = 12L;
	public static final Long INDEED_JOBS_SERVICE_ID = 13L;
	public static final Long LINKEDIN_JOBS_SERVICE_ID = 14L;
	public static final Long EMPLOYEE_COUNT_SERVICE_ID = 15L;
	public static final Long ACQUISITION_DETAILS_SERVICE_ID = 16L;
	public static final Long GOOGLE_APPS_LINKS_COLLECTION_SERVICE_ID = 17L;
	public static final Long GOOGLE_APPS_DATA_EXTRACTION_SERVICE_ID = 18L;
	public static final Long ITUNES_APPS_LINKS_COLLECTION_SERVICE_ID = 19L;
	public static final Long ITUNES_APPS_DATA_EXTRACTION_SERVICE_ID = 20L;
	public static final Long PATENTS_SERVICE_ID = 21L;
	public static final Long PATENT_LITIGATION_SERVICE_ID = 22L;
	public static final Long COMPANY_RSS_FEED_SERVICE_ID = 23L;
	public static final Long CRUNCHBASE_NEWS_SERVICE_ID = 24L;
	public static final Long COMPANY_NEWS_SERVICE_ID = 25L;
	public static final Long GSCE_NEWS_SERVICE_ID = 26L;
	public static final Long PR_NEWS_WIRE_SERVICE_ID = 27L;
	public static final Long ONLINE_PR_MEDIA_SERVICE_ID = 28L;
	public static final Long PRURGET_NEWS_SERVICE_ID = 29L;
	public static final Long PR_ZOOM_SERVICE_ID = 30L;
	public static final Long FEED_BURNER_SERVICE_ID = 31L;
	public static final Long FORBES_SERVICE_ID = 32L;
	public static final Long FT_SERVICE_ID = 33L;
	public static final Long REUTERS_SERVICE_ID = 34L;
	public static final Long WSJ_SERVICE_ID = 35L;
	public static final Long VENTUREBEAT_SERVICE_ID = 36L;
	public static final Long TECHCRUNCH_SERVICE_ID = 37L;
	public static final Long BOARD_MEMBERS_SERVICE_ID = 39L;
	public static final Long JOB_GROWTH_SERVICE_ID = 40L;
	public static final Long FUNDING_GROWTH_SERVICE_ID = 41L;
	public static final Long COMPETITOR_GROWTH_SERVICE_ID = 42L;
	public static final Long PATENT_LITIGATION_INDEX_SERVICE_ID = 43L;
	public static final Long PARTNER_LINK_EXTRACTION_SERVICE_ID = 44L;
	public static final Long CLIENT_LINK_EXTRACTION_SERVICE_ID = 45L;
	public static final Long FORM_D_DATA_EXTRACTION_SERVICE_ID = 46L;
	public static final Long APP_INDEX_SERVICE_ID = 47L;
	public static final Long PARTNER_DATA_EXTRACTION_SERVICE_ID = 48L;
	public static final Long CUSTOMER_DATA_EXTRACTION_SERVICE_ID = 49L;
	public static final Long TWITTER_GROWTH_CALCULATION_SERVICE_ID = 50L;
	public static final Long FACEBOOK_GROWTH_CALCULATION_SERVICE_ID = 51L;
	public static final Long LINKEDIN_GROWTH_CALCULATION_SERVICE_ID = 52L;
	public static final Long YOUTUBE_GROWTH_CALCULATION_SERVICE_ID = 53L;
	public static final Long EXTRACT_COMPANY_DETAILAS_FROM_LINKEDIN_SERVICE_ID = 54L;
	public static final Long BOARD_MEMBERS_LINK_EXTRACTION_SERVICE = 55L;
	public static final Long PRODUCT_EXTRACTION_FROM_CRUNCHBASE_SERVICE_ID = 56L;
	public static final Long LINKEDIN_NEWS_SERVICE_ID = 57L;
	public static final Long URL_REDIRECTION_VERIFICATION_SERVICE_ID = 58L;
	public static final Long EXTRACT_SOCIAL_MEDIA_LINKS_SERVICE_ID = 59L;
	public static final Long EMPLOYEE_INFO_FROM_LINKEDIN_SERVICE_ID = 60L;
	public static final Long PRODUCT_LINK_EXTRACTION_SERVICE_ID = 61L;
	public static final Long PARTNER_INDEX_SERVICE_ID = 62L;
	public static final Long CUSTOMER_INDEX_SERVICE_ID = 63L;
	public static final Long PATENT_INDEX_SERVICE_ID = 64L;
	public static final Long ADD_NEW_COMPANY_SERVICE_ID = 65L;
	public static final Long ESTIMATED_REVENUE_CAL_SERVICE_ID = 66L;
	public static final Long PRODUCT_DATA_EXTRACTION_SERVICE_ID = 67L;
	public static final Long OFFICE_SPACE_LINK_EXTRACTION_SERVICE_ID = 68L;
	public static final Long OFFICE_SPACE_DATA_EXTRACTION_SERVICE_ID = 69L;
	public static final Long GOOGLE_URL_REQUESTS_SERVICE_ID = 70L;
	public static final Long EMPLOYEE_COUNT_EXTRACTION_SERVICE_ID = 71L;
	public static final Long NEWS_TRIGGER_SERVICE_ID = 72L;
	public static final Long BOARD_MEMBERS_DATA_EXTRACTION_FROM_WEBSITE_SERVICE = 73L;
	public static final Long PRODUCT_INDEX_SERVICE_ID = 74L;
	public static final Long OFFICE_SPACE_INDEX_SERVICE_ID = 75L;
	public static final Long BOARD_MEMBERS_INDEX_SERVICE = 76L;
	public static final Long CRUNCHBASE_OFFICE_SPACE_SERVICE_ID = 77L;
	public static final long EVENT_EXTRACTION_TWITTER_SERVICE_ID = 78L;
	public static final Long JAVASCRIPT_BASED_WEBSITES_SERVICE = 79L;
	public static final Long DATABASE_CLEAN_UP_SERVICE_ID = 80L;
	public static final long EVENT_EXTRACTION_FB_SERVICE_ID = 81L;
	public static final long EXHIBIT_RAC_SERVICE_ID = 82L;
	public static final long EVENT_EXTRACTION_CRUNCHBASE_SERVICE_ID = 83L;
	public static final long EVENT_EXTRACTION_COMS_SERVICE_ID = 84L;
	public static final long EVENT_EXTRACTION_NTRADESHOWS_SERVICE_ID = 85L;
	public static final long EVENT_EXTRACTION_EVENTS_IN_AMERICA_SERVICE_ID = 87L;
	public static final long EXTRACT_PUBLICATION_SERVICE_ID = 88L;
	public static final long PUBLICATION_INDEX_SERVICE_ID = 90L;
	public static final long EVENT_EXTRACTION_WEBSITES_SERVICE_ID = 91L;
	public static final long EMPLOYEE_DETAILS_FROM_CRUNCHBASE_SERVICE_ID = 92L;
	public static final long BUILT_WITH_SERVICE_SERVICE_ID = 93L;
	public static final long EMPLOYEE_WEBSITE_INFO_SERVICE_ID = 94L;
	public static final long EMPLOYEE_NEWS_INFO_SERVICE_ID = 95L;
	public static final long EMPLOYEE_VIDEO_INFO_SERVICE_ID = 96L;
	public static final long EMPLOYEE_INVESTMENT_INFO_SERVICE_ID = 97L;
	public static final long AUTOMATIC_RANKING_DATA_COLLECTION_SERVICE_ID = 98L;
	public static final long IMPORT_LINKEDIN_EMPLOYEE_DETAILS_SERVICE_ID = 99L;
	public static final long EMPLOYEE_EDUCATION_INFO__FROM_CRUNCHBASE_SERVICE_ID = 100L;
	public static final long EMPLOYEE_JOBS_INFO_SERVICE_ID = 101L;
	public static final long IMPORT_LINKEDIN_EMPLOYEE_CAREER_DETAILS_SERVICE_ID = 102L;
	public static final long EMPLOYEE_TWITTER_INFO_SERVICE_ID = 103L;
	public static final long EDUCATION_SCORING_SERVICE_ID = 104L;
	public static final long LINKEDIN_PREMIUM_DATA_EXTRACTION_SERVICE_ID = 105L;
	public static final long EMPLOYEE_EDUCATION_MAPPING_SERVICE_ID = 106L;
	public static final long IMPORT_LINKEDIN_EMPLOYEE_EDUCATION_DETAILS_SERVICE = 107L;
	public static final long IMPORT_LINKEDIN_EMPLOYEE_PUBLICATION_DETAILS_SERVICE_ID = 108L;
	public static final long JOB_Index_SERVICE_ID = 109L;
	public static final long EMPLOYEE_EDUCATION_RANKING_SERVICE_ID = 110L;
	public static final long TWITTER_GROWTH_INDEX_SERVICE_ID = 111L;
	public static final long EMPLOYEE_GROWTH_SERVICE_ID = 112L;
	public static final long EMPLOYEE_NETWORK_RANKING_SERVICE_ID = 113L;
	public static final long IMPORT_CRUNCHBASE_LINK_DETAILS_SERVICE_ID = 114L;
	public static final long EMPLOYEE_FACEBOOK_VIDEO_DATA_EXTRACTION_SERVICE_ID = 115L;
	public static final long COMPANY_SCORING_SERVICE_ID = 116L;
	public static final long CONFERENCE_INDEX_SERVICE_ID = 117L;
	public static final long EMPLOYEE_FACEBOOK_EVENT_DATA_EXTRACTION_SERVICE_ID = 118L;
	public static final long EMPLOYEE_CAREER_SCORING_SERVICE_ID = 119L;
	public static final long EXTRACT_LINKEDIN_LINK_FROM_GCSE_SERVICE_ID = 120L;
	public static final long EXTRACT_TWITTER_LINK_FROM_GCSE_SERVICE_ID = 121L;
	public static final long EXTRACT_FACEBOOK_LINK_FROM_GCSE_SERVICE_ID = 122L;
	public static final long EXTRACT_YOUTUBE_LINK_FROM_GCSE_SERVICE_ID = 123L;
	public static final long ADS_SPENT_SERVICE_ID = 125L;
	public static final long PRODUCT_OR_SERVICE_COMPANY_IDENTIFICATION_SERVICE = 131L;
	public static final long EXTRACT_SOCIAL_MEDIA_LINKS_FROM_GCSE_SERVICE_ID = 134L;
	public static final long UPDATE_SOCIAL_MEDIA_LINKS_FROM_EXCEL_SERVICE_ID = 137L;
	public static final long EXTRACT_NEWS_FROM_GCSE_SERVICE_ID = 138L;
	public static final long EXTRACT_ALL_SM_LINK_FROM_GCSE_SERVICE_ID = 139L;
	public static final long COMPANY_ALTERNATIVE_URL_SERVICE_ID = 140L;
	public static final long UPDATE_COMPANY_DETAILS_FROM_LINKEDIN_SERVICE_ID = 141L;
	public static final long EXTRACT_SOCIAL_MEDIA_LINKS_FROM_WEBSITE_SERVICE_ID = 142L;
	public static final long EXTRACT_URL_SERVICE_ID = 143L;
	public static final long LEAD_GENERATION_FROM_TECH_SUMMIT_SERVICE_ID = 144L;
	public static final long IMPORT_EXISTING_CONFERENCE_INFO_SERVICE_ID = 145L;
	public static final long IMPORT_CRUNCHBASE_ACQUISITION_SERVICE_ID = 146L;
	public static final long IMPORT_CRUNCHBASE_ORGANIZATION_SERVICE_ID = 147L;
	public static final long IMPORT_CRUNCHBASE_INVESTMENT_SERVICE_ID = 148L;
	public static final long IMPORT_CRUNCHBASE_FUNDING_ROUND_SERVICE_ID = 149L;
	public static final long DOWNLOAD_COMPANY_LOGO_SERVICE_ID = 150L;
	public static final long IMPORT_CRUNCHBASE_CATEGORYGROUP_SERVICE_ID = 151L;
	public static final long IMPORT_CRUNCHBASE_DEGREES_SERVICE_ID = 152L;
	public static final long IMPORT_CRUNCHBASE_EVENTAPPEARANCES_SERVICE_ID = 153L;
	public static final long IMPORT_CRUNCHBASE_EVENTS_SERVICE_ID = 154L;
	public static final long IMPORT_CRUNCHBASE_FUNDS_SERVICE_ID = 155L;
	public static final long IMPORT_CRUNCHBASE_INVESTMENTPARTNERS_SERVICE_ID = 156L;
	public static final long EXTRACT_PEOPLE_STATS_SERVICE_ID = 157L;
	public static final long UPDATE_EMPLOYEE_COUNT_SERVICE_ID = 158L;
	public static final long CLUSTER_RANKING_DATA_COLLECTION_SERVICE_ID = 160L;
	public static final long IMPORT_TAXONOMY_INFO_SERVICE_ID = 161L;
	public static final long EXTRACT_LINKEDIN_JOBS_FROM_FILES_SERVICE_ID = 162L;
	public static final long EXTRACT_LINKEDIN_POSTS_FROM_FILES_SERVICE_ID = 163L;
	public static final long EXTRACT_SIMILAR_PAGES_FROM_FILES_SERVICE_ID = 164L;
	public static final long COMPANY_META_INFO_EXTRACTION_SERVICE_ID = 165L;
	public static final long LINKEDIN_DECISION_MAKERS_LEADS_SERVICE_ID = 166L;
	public static final long COMPANY_ADDRESS_SERVICE_ID = 168L;
	public static final long NEWS_TRIGGERS_JOBS_DATA_SERVICE_ID = 170L;
	public static final long LAYOFF_NEWS_EXTRACTION__SERVICE_ID = 173L;
	public static final long MOBILE_APP_LINK_FROM_GCSE_EXTRACTION_SERVICE_ID = 174L;
	public static final long GOOGLE_PLAY_STORE_APP_DATA_EXTRACTION_SERVICE_ID = 175L;
	public static final long APPLE_PLAY_STORE_APP_DATA_EXTRACTION_SERVICE_ID = 176L;
	public static final long UPDATE_URL_SERVICE_ID = 177L;
	public static final long IMPORT_INTEREST_CHECK_INFO_SERVICE_ID = 179L;
	public static final Long INDEED_JOBS_VALIDATION_SERVICE_ID = 180L;
	public static final Long CONFIDENCE_SCORE_CAL_SERVICE_ID = 181L;
	public static final long IMPORT_CRUNCHBASE_INVESTOR_SERVICE_ID = 182L;
	public static final long IMPORT_CRUNCHBASE_IPOS_SERVICE_ID = 183L;
	public static final long IMPORT_CRUNCHBASE_JOBS_SERVICE_ID = 184L;
	public static final long IMPORT_CRUNCHBASE_PEOPLE_SERVICE_ID = 185L;
	public static final long IMPORT_CRUNCHBASE_PEOPLE_DESCRIPTIONS_SERVICE_ID = 186L;
	public static final long UPDATE_TAGS_INFO_SERVICE_ID = 187L;
	public static final long IMPORT_CRUNCHBASE_ORGANIZATION_DESCRIPTIONS_SERVICE_ID = 188L;
	public static final long WEEKLY_NEWS_ALERTS_SERVICE_ID = 190L;
	public static final long IMPORT_COMPETITORS_SERVICE_ID = 191L;
	public static final long UPDATE_PRODUCT_SERVICE_SERVICE_ID = 195L;
	public static final long GENERIC_SERVICE_ID = 196L;
	public static final long UPDATE_COUNTY_INFO_SERVICE_ID = 202L;
	public static final long COMPANY_ADDRESS_FROM_WEBSITE_SERVICE_ID = 203L;
	public static final long CLEANUP_COMPANY_URL_SERVICE_ID = 204L;
	public static final long PRESS_RELEASES_NEWS_EXTRACTION_SERVICE_ID = 205L;
	public static final long WEBSITE_SCORE_CAL_SERVICE_ID = 206L;
	public static final long COMBINE_ALL_PRODUCTS_SERVICE_ID = 207L;
	public static final long PRESS_RELEASES_PRURGENT_NEWS_EXTRACTION_SERVICE_ID = 211L;
	public static final long PRESS_RELEASES_BUSINESS_WIRE_NEWS_EXTRACTION_SERVICE_ID = 212L;
	public static final long PRESS_RELEASES_PRCOM_NEWS_EXTRACTION_SERVICE_ID = 213L;
	public static final long LEAD_GENERATION_GCSE_SERVICE_ID = 214L;
	public static final long EXTRACT_NEWS_FROM_WEBSITE_SERVICE_ID = 219L;
	public static final long UPDATE_COVID_IMPACT_INFO_SERVICE_ID = 222L;
	public static final long IMPORT_SBA_PAYCHECK_DATA_FROM_EXCEL_SERVICE_ID = 223L;
	public static final long UPDATE_ENCRYPTED_COMPANY_ID_SERVICE_ID = 224L;	
	public static final long EXTRACT_G2CROWD_PRODUCT_INFO_SERVICE_ID = 311L;	

	// server details
	// public static final String DEV_SERVER_NAME = "worker1-server";
	// public static final String TEST_SERVER_NAME = "test-server";
	// public static final String DEV_SERVER_IP_ADDRESS = "54.87.254.43";
	// public static final String TEST_SERVER_IP_ADDRESS = "192.168.90.71";

	public static final String DEV_SERVER_NAME = "local-server";
	public static final String TEST_SERVER_NAME = "test-server";
	// public static final String DEV_SERVER_IP_ADDRESS = "192.168.90.70";
	public static final String DEV_SERVER_IP_ADDRESS = "10.100.10.9";
	public static final String TEST_SERVER_IP_ADDRESS = "192.168.90.71";

	// OPEN CALAIS
	public static final String CALAIS_KEY = "SGWU8XSeYdwwGq7qAxARJdPct4BlU88Y";
	public static final String BING_API_KEY = "GSPdGNHXNrZcUM0CT4RCoPEiL8taR2DkC9zGiLrNuoI";

	// User Agent
	public final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0";

	public static final String VALID_TRACKING_COMPANY = "yes-tracking";
	public static final String VALID_NOT_TRACKING_COMPANY = "yes-not-tracking";

	public static final long TIME_INTERVAL_BETWEEN_REQUESTS = 10000;
	public static final int CONNECTION_TIMEOUT = 10000;

	// relevant variables
	public static final String RELEVANT_NO = "no";
	public static final String RELEVANT_YES_TRACKING = "yes-tracking";
	public static final String RELEVANT_YES_NOT_TRACKING = "yes-not-tracking";

	public static final Date CURRENT_DATE = new Date();

	public static final long NO_URL_COMPANY_ID = 21235L;

	// public static final String ACP_USER_NAME = "paseteam@aldrichcap.com";
	// public static final String ACP_PASSWORD = "Nanialdrich@123";

	public static final String ACP_USER_NAME = "paseteam@aldrichcap.com";
	public static final String ACP_PASSWORD = "ghghYG%^*%&76776";

	// ADS SPENT API KEY
	public static final String ADS_SPENT_API_KEY = "EVEEVLUR";

	/*
	 * public static final Long ACQUISITION_DETAILS_SERVICE_ID = 1L; public
	 * static final Long YOUTUBE_SERVICE_ID = 1L; public static final Long
	 * LINKEDIN_FOLLOWERS_SERVICE_ID = 1L; public static final Long
	 * EMPLOYEE_COUNT_SERVICE_ID = 1L; public static final Long
	 * PATENT_LITIGATION_SERVICE_ID = 1L; public static final Long
	 * FUNDING_INDEX_SERVICE_ID = 1L; public static final Long
	 * FUNDING_GROWTH_SERVICE_ID = 1L; public static final Long
	 * YOUTUBE_VIDEO_STATS_SERVICE_ID = 1L; public static final Long
	 * COMPETITOR_GROWTH_SERVICE_ID = 1L; public static final Long
	 * PATENT_LITIGATION_INDEX_SERVICE_ID = 1L; public static final Long
	 * COMPETITOR_SERVICE_ID = 1L;
	 */

}
