package com.aldrich.pase.vo;

import lombok.Getter;
import lombok.Setter;

public class CrunchbaseCompanyDetailsVO
{
	@Setter
	@Getter
	private Integer id;

	@Setter
	@Getter
	private String companyName;

	@Setter
	@Getter
	private String domainName;

	@Setter
	@Getter
	private String url;

	@Setter
	@Getter
	private String uniqueName;
}
