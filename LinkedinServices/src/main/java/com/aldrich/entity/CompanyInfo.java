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
@Table(name = "company_info_1")
public class CompanyInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "fk_company_id")
	private Long FkCompanyId;

	@Setter
	@Getter
	@Column(name = "company_url")
	private String companyUrl;

	@Setter
	@Getter
	@Column(name = "domian")
	private String domian;

	@Setter
	@Getter
	@Column(name = "staff_count")
	private int staffCount;

	@Setter
	@Getter
	@Column(name = "description")
	private String description;

	@Setter
	@Getter
	@Column(name = "companu_id")
	private String companyId;

	@Setter
	@Getter
	@Column(name = "start")
	private int start;

	@Setter
	@Getter
	@Column(name = "end")
	private int end;

	@Setter
	@Getter
	@Column(name = "company_type")
	private String companyType;

	@Setter
	@Getter
	@Column(name = "founded_on")
	private String foundedOn;
	
	
	@Setter
	@Getter
	@Column(name = "city")
	private String city;
	
	@Setter
	@Getter
	@Column(name = "country")
	private String country;
	
	@Setter
	@Getter
	@Column(name = "geographic_area")
	private String geographicArea;
	
	@Setter
	@Getter
	@Column(name = "postal_code")
	private String postalCode;
	
	@Setter
	@Getter
	@Column(name = "line1")
	private String line1;
	
	@Setter
	@Getter
	@Column(name = "line2")
	private String line2;
	
	@Setter
	@Getter
	@Column(name = "hcountry")
	private String hcountry;
	
	@Setter
	@Getter
	@Column(name = "hgeographicArea")
	private String hgeographicArea;
	
	@Setter
	@Getter
	@Column(name = "hcity")
	private String hcity;
	
	@Setter
	@Getter
	@Column(name = "hpostal_code")
	private String hpostalCode;
	
	@Setter
	@Getter
	@Column(name = "hline1")
	private String hline1;
	
	
	@Setter
	@Getter
	@Column(name = "hline2")
	private String hline2;
	
	@Setter
	@Getter
	@Column(name = "specialities")
	private String specialities;
	

	
	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activityDateTime;

}