package com.aldrich.pase.vo;

import lombok.Getter;
import lombok.Setter;

public class UrlRedirectionVO
{
	@Setter
	@Getter
	private Long id;

	@Setter
	@Getter
	private String url;

	@Setter
	@Getter
	private String redirectedDomain;

	@Setter
	@Getter
	private String redirectedUrl;

	@Setter
	@Getter
	private String websiteStatus;
}
