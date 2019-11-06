package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="S_RUN_CONFIG")
public class RunConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
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