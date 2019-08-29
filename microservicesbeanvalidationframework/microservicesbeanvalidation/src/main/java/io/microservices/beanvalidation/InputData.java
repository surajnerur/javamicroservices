package io.microservices.beanvalidation;

import javax.validation.constraints.NotNull;

import io.microservices.beanvalidation.service.OnCreate;

public class InputData {

	private String numid;
	
	@NotNull(groups=OnCreate.class)
	private String name;

	public String getNumid() {
		return numid;
	}

	public void setNumid(String numid) {
		this.numid = numid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
