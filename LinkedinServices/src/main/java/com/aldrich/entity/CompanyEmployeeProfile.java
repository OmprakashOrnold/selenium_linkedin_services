package com.aldrich.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lavu_employees")
public class CompanyEmployeeProfile {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "fk_company_id")
	private Long fk_company_id;

	@Setter
	@Getter
	@Column(name = "full_name")
	private String full_name;

	@Setter
	@Getter
	@Column(name = "title")
	private String title;

	@Setter
	@Getter
	@Column(name = "city")
	private String city;

	@Setter
	@Getter
	@Column(name = "state")
	private String state;

	@Setter
	@Getter
	@Column(name = "country")
	private String country;

	@Setter
	@Getter
	@Column(name = "profile_url")
	private String profileUrl;

	@Setter
	@Getter
	@Column(name = "person_image_url")
	private String personImageUrl;

	@Setter
	@Getter
	@Column(name = "degree_conn")
	private String degreeConn;

	@Setter
	@Getter
	@Column(name = "duration")
	private String duration;

	@Setter
	@Getter
	@Column(name = "share_conn_info")
	private String shareConnInfo;

	@Setter
	@Getter
	@Column(name = "fb_url")
	private String fbUrl;

	@Setter
	@Getter
	@Column(name = "tw_url")
	private String twUrl;

	@Setter
	@Getter
	@Column(name = "lnk_url")
	private String lnkUrl;
	
	@Setter
	@Getter
	@Column(name = "joining_date")
	private Date joiningDate;
	
	@Setter
	@Getter
	@Column(name="latitude")
	private Double latitude;
	
	@Setter
	@Getter
	@Column(name="longitude")
	private Double longitude;
	
	@Setter
	@Getter
	@Column(name="active")
	private int active;

	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activityDatetime;



}
