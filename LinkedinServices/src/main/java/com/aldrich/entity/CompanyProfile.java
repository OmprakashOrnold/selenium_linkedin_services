package com.aldrich.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_profile")
public class CompanyProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "emp_code")
	private String empCode;

	@Setter
	@Getter
	@Column(name = "employee_size")
	private String employeeSize;

	@Setter
	@Getter
	@Column(name = "emp_count")
	private Integer empCount;

	@Setter
	@Getter
	@Column(name = "revenue")
	private String revenue;

	@Setter
	@Getter
	@Column(name = "location")
	private String location;

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
	@Column(name = "zip_code")
	private String zipCode;

	@Setter
	@Getter
	@Column(name = "phone_number")
	private String phoneNumber;

	@Setter
	@Getter
	@Column(name = "email_id")
	private String emailId;

	@Setter
	@Getter
	@Column(name = "logo_url")
	private String logoURL;

	@Setter
	@Getter
	@Column(name = "description")
	private String description;

	@Setter
	@Getter
	@Column(name = "specialties")
	private String specialties;

	@Setter
	@Getter
	@Column(name = "founded_year")
	private String foundedYear;

	@Setter
	@Getter
	@Column(name = "fk_company_id")
	private Long fkCompanyId;

	@Setter
	@Getter
	@Column(name = "fk_category_id")
	private Long fkCategoryId;

	@Setter
	@Getter
	@Column(name = "fk_sub_category_id")
	private Long fkSubCategoryId;

	@Setter
	@Getter
	@Column(name = "fk_data_source_id")
	private Long fkDataSourceId;

	@Setter
	@Getter
	@Column(name = "fk_user_id")
	private Long fkUserId;

	@Setter
	@Getter
	@Column(name = "notes")
	private String notes;

	@Setter
	@Getter
	@Column(name = "stage")
	private String stage;

	@Setter
	@Getter
	@Column(name = "legacy_activity_datetime")
	private Date legacy_activity_datetime;

	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activityDatetime;

	@Setter
	@Getter
	@Column(name = "latitude")
	private Double latitude;

	@Setter
	@Getter
	@Column(name = "longitude")
	private Double longitude;

	@Setter
	@Getter
	@Column(name = "cb_tags")
	private String cbTags;

	@Setter
	@Getter
	@Column(name = "pb_tags")
	private String pbTags;

	@Setter
	@Getter
	@Column(name = "txn_tags")
	private String txnTags;

	@Setter
	@Getter
	@Column(name = "meta_tags")
	private String metaTags;

	@Setter
	@Getter
	@Column(name = "sf_tags")
	private String sfTags;

	@Setter
	@Getter
	@Column(name = "menu_info")
	private String menuInfo;

	@Setter
	@Getter
	@Column(name = "cap_tags")
	private String capTags;

	@Setter
	@Getter
	@Column(name = "county_id")
	private String countyId;
	
	@Setter
	@Getter
	@Column(name = "all_tags")
	private String all_tags;
	
	@Setter
	@Getter
	@Column(name = "short_desc")
	private String short_desc;
	
	@Setter
	@Getter
	@Column(name = "comp_title")
	private String comp_title;
	
	@Setter
	@Getter
	@Column(name = "status_code")
	private String status_code;
	
	@Setter
	@Getter
	@Column(name = "updated_at")
	private Date updated_at;

	@Setter
	@Getter
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;

}
