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
@Table(name = "company_confirmlocation")
public class CompanyConfirmLocation {
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
	@Column(name = "line1")
	private String line1;

	@Setter
	@Getter
	@Column(name = "line2")
	private String line2;

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
	@Column(name = "postal_code")
	private String postal_code;

	@Setter
	@Getter
	@Column(name = "country")
	private String country;

	@Setter
	@Getter
	@Column(name = "description")
	private String description;

	@Setter
	@Getter
	@Column(name = "headquarter")
	private String headquarter;

	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activity_datetime;
}
