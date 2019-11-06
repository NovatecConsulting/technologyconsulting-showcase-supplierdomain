package de.novatec.showcase.supplier.dto;

import java.io.Serializable;

public class RunConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private int val;

	public RunConfig() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVal() {
		return this.val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}