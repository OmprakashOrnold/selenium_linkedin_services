package com.aldrich.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class LinkedinFollowersIndex {

	@Id
	private String id;

	@Field("fk_company_id")
	private Long fkCompanyId;

	@Field("no_of_followers")
	private Long noOfFoloowers;

	@Field("exception_code")
	private Integer exceptionCode;

	@Field("attempt_count")
	private Integer attemptCount;

	@Field("activity_datetime")
	private Date activityDateTime;

	@Field("activity_datetime_temp")
	private Date activityDateTimeTemp;

	@Field("activity_date")
	private String activityDate;

	public Date getActivityDateTime() {
		return this.activityDateTime;
	}

	public void setActivityDateTime(Date activityDateTime) {
		this.activityDateTime = activityDateTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getFkCompanyId() {
		return this.fkCompanyId;
	}

	public void setFkCompanyId(Long fkCompanyId) {
		this.fkCompanyId = fkCompanyId;
	}

	public Long getNoOfFoloowers() {
		return this.noOfFoloowers;
	}

	public void setNoOfFoloowers(Long noOfFoloowers) {
		this.noOfFoloowers = noOfFoloowers;
	}

	public Integer getExceptionCode() {
		return this.exceptionCode;
	}

	public void setExceptionCode(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public Integer getAttemptCount() {
		return this.attemptCount;
	}

	public void setAttemptCount(Integer attemptCount) {
		this.attemptCount = attemptCount;
	}

	public Date getActivityDateTimeTemp() {
		return this.activityDateTimeTemp;
	}

	public void setActivityDateTimeTemp(Date activityDateTimeTemp) {
		this.activityDateTimeTemp = activityDateTimeTemp;
	}

	public String getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

}
