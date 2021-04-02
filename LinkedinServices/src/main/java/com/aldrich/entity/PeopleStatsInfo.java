package com.aldrich.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people_stats_info")
public class PeopleStatsInfo {
	@Id
	@GeneratedValue
	@Setter
	@Getter
	@Column(name = "id")
	private BigInteger id;
	@Setter
	@Getter
	@Column(name = "fk_company_id")
	private Integer fk_company_id;
	@Setter
	@Getter
	@Column(name = "total_emp_count")
	private Integer total_emp_count;
	@Getter
	@Setter
	@Column(name = "name")
	private String name;
	@Getter
	@Setter
	@Column(name = "value")
	private Integer value;
	@Getter
	@Setter
	@Column(name = "emp_size")
	private String emp_size;
	@Getter
	@Setter
	@Column(name = "type")
	private String type;
	@Getter
	@Setter
	@Column(name = "source_name")
	private String source_name;
	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activity_datetime;
	@Setter
	@Getter
	@Column(name = "group_name")
	private String group_name;
	@Setter
	@Getter
	@Column(name = "per_value")
	private Double per_value;
}
