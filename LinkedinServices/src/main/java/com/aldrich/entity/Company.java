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
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "legacy_id")
	private Long legacyId;

	@Setter
	@Getter
	@Column(name = "name")
	private String name;

	@Setter
	@Getter
	@Column(name = "url")
	private String url;

	@Setter
	@Getter
	@Column(name = "domain")
	private String domain;

	@Setter
	@Getter
	@Column(name = "redirected_url")
	private String redirectedUrl;

	@Setter
	@Getter
	@Column(name = "redirected_domain")
	private String redirectedDomain;

	@Setter
	@Getter
	@Column(name = "notes")
	private String notes;

	@Setter
	@Getter
	@Column(name = "tracking")
	private String tracking;

	@Setter
	@Getter
	@Column(name = "relevant")
	private String relevant;

	@Setter
	@Getter
	@Column(name = "status")
	private String status;

	@Setter
	@Getter
	@Column(name = "score")
	private Double score;

	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activityDateTime;

}
