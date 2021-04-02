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
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	@Column(name = "id")
	private Long id;

	@Setter
	@Getter
	@Column(name = "parent_id")
	private Long parentId = new Long(0);

	@Setter
	@Getter
	@Column(name = "name")
	private String name;

	@Setter
	@Getter
	@Column(name = "code")
	private String code;

	@Setter
	@Getter
	@Column(name = "activity_datetime")
	private Date activityDateTime;

}
