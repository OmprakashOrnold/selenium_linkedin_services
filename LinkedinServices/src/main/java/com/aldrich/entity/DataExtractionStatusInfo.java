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
@Table(name = "linkedin_data_extraction_status_info")
public class DataExtractionStatusInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "fk_company_id")
	private Integer fk_company_id;

	@Setter
	@Getter
	@Column(name = "type")
	private String type;
	
	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activity_datetime;
}
