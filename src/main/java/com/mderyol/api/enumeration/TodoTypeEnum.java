package com.mderyol.api.enumeration;

public enum TodoTypeEnum {
	END_USER("EndUser"), //
	ADMIN("Admin"), //
	M2M("M2M");

	TodoTypeEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return this.value;
	}
}
