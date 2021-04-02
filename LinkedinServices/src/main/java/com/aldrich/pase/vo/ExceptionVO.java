package com.aldrich.pase.vo;

import lombok.Getter;
import lombok.Setter;

public class ExceptionVO {
	@Setter
	@Getter
	private Long fkCompanyId;

	@Setter
	@Getter
	private Long fkServiceId;

	@Setter
	@Getter
	private String methodName;

	@Setter
	@Getter
	private Long lineNumber;

	@Setter
	@Getter
	private String exceptionMessage;

	@Setter
	@Getter
	private String notes;
	
	@Setter
	@Getter
	private Integer exceptionCode;
	
	
	

	public ExceptionVO(Long fkCompanyId, Long fkServiceId, String methodName, Long lineNumber, String exceptionMessage,
			String notes, Integer exceptionCode) {
		super();
		this.fkCompanyId = fkCompanyId;
		this.fkServiceId = fkServiceId;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		this.exceptionMessage = exceptionMessage;
		this.notes = notes;
		this.exceptionCode = exceptionCode;
	}




	public ExceptionVO(Long fkCompanyId, Long fkServiceId, String methodName, Long lineNumber, String exceptionMessage,
			String notes) {
		this.fkCompanyId = fkCompanyId;
		this.fkServiceId = fkServiceId;
		this.methodName = methodName;
		this.lineNumber = lineNumber;
		this.exceptionMessage = exceptionMessage;
		this.notes = notes;
	}
}
