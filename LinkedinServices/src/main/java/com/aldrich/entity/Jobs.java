package com.aldrich.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

public class Jobs {

	@Id
	private String Id;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Field("company_Name")
	@Getter
	@Setter
	private String companyName;
	
	@Field("fk_company_id")
	private long companyId;

	@Field("title")
	private String jobtitle;

	@Field("type")
	private String type;

	@Field("location")
	private String location;

	@Field("city")
	private String city;

	@Field("state")
	private String state;

	@Field("country")
	private String country;

	@Field("description")
	private String description;

	@Field("url")
	private String url;

	@Field("jobkey")
	private String jobkey;

	@Field("expired")
	private String expired;

	@Field("posted_datetime")
	private Date posted_datetime;

	@Field("posted_datetime_temp")
	private Date posted_datetime_temp;

	@Field("posted_date")
	private String posted_date;

	@Field("confidence_score")
	private Double confidence_score;

	@Field("server_response")
	private String server_response;

	@Field("fk_source_id")
	private Long source_id;

	@Field("fk_job_daily_status_id")
	private long job_daily_status_id;

	@Field("trigger")
	private String trigger;

	@Field("activity_datetime")
	private Date activity_datetime;

	@Field("activity_datetime_temp")
	private Date activity_datetime_temp;

	@Field("activity_date")
	private String activity_date;

	public String getExpired() {
		return expired;
	}

	public void setExpired(String expired) {
		this.expired = expired;
	}

	public Date getPosted_datetime_temp() {
		return posted_datetime_temp;
	}

	public void setPosted_datetime_temp(Date posted_datetime_temp) {
		this.posted_datetime_temp = posted_datetime_temp;
	}

	public String getPosted_date() {
		return posted_date;
	}

	public void setPosted_date(String posted_date) {
		this.posted_date = posted_date;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getPosted_datetime() {
		return posted_datetime;
	}

	public void setPosted_datetime(Date posted_datetime) {
		this.posted_datetime = posted_datetime;
	}

	public Double getConfidence_score() {
		return confidence_score;
	}

	public void setConfidence_score(Double confidence_score) {
		this.confidence_score = confidence_score;
	}

	public String getServer_response() {
		return server_response;
	}

	public void setServer_response(String server_response) {
		this.server_response = server_response;
	}

	public Long getSource_id() {
		return source_id;
	}

	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}

	public long getJob_daily_status_id() {
		return job_daily_status_id;
	}

	public void setJob_daily_status_id(long job_daily_status_id) {
		this.job_daily_status_id = job_daily_status_id;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public Date getActivity_datetime() {
		return activity_datetime;
	}

	public void setActivity_datetime(Date activity_datetime) {
		this.activity_datetime = activity_datetime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJobkey() {
		return jobkey;
	}

	public void setJobkey(String jobkey) {
		this.jobkey = jobkey;
	}

	public Date getActivity_datetime_temp() {
		return activity_datetime_temp;
	}

	public void setActivity_datetime_temp(Date activity_datetime_temp) {
		this.activity_datetime_temp = activity_datetime_temp;
	}

	public String getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(String activity_date) {
		this.activity_date = activity_date;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	@Override
	public String toString() {

		String printableString = "CompanyId :" + companyId + " Job Title : " + jobtitle + " type :" + type
				+ " location :" + location + " city :" + city + " state :" + state + " country :" + country
				+ " posted_datetime :" + posted_datetime + " confidence_score :" + confidence_score + " source_id :"
				+ source_id + " url :" + url + " description :" + description + " jobkey:" + jobkey + " trigger:"
				+ trigger;

		return printableString;
	}

}
