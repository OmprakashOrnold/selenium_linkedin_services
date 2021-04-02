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
@Table(name="socialmedia_link")
public class SocialMediaLink {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name="id")
	private Integer id;
	
	
	
	@Setter
	@Getter
	@Column(name="fk_company_id")
	private Long fkCompanyId;
	
	
	@Setter
	@Getter
	@Column(name="unique_id")
	private String uniqueId;
	
	
	@Setter
	@Getter
	@Column(name="unique_name")
	private String uniqueName;
	
	
	@Setter
	@Getter
	@Column(name="fk_link_type_info_id")
	private Long fkLinkTypeInfoId;
	
	
	@Setter
	@Getter
	@Column(name="relevant")
	private String relevant;
	
	
	@Setter
	@Getter
	@Column(name="exception_codes")
	private String exceptionCodes;
	
	
	@Setter
	@Getter
	@Column(name="exception_code")
	private Integer exceptionCode;
	
	
	@Setter
	@Getter
	@Column(name="fk_source_id")
	private Integer fkSourceId;
	
	
	@Setter
	@Getter
	@Column(name="notes")
	private String notes;
	
	
	
	@Setter
	@Getter
	@Column(name="activity_datetime")
	private Date activityDateTime;
	
}
