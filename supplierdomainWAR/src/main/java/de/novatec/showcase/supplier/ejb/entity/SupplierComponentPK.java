package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SupplierComponentPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String componentId;

	private int supplierId;

    public SupplierComponentPK() {
    }
    
	public SupplierComponentPK(String componentId, int supplierId) {
		super();
		this.componentId = componentId;
		this.supplierId = supplierId;
	}

	public String getComponentId() {
		return this.componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public int getSupplierId() {
		return this.supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SupplierComponentPK)) {
			return false;
		}
		SupplierComponentPK castOther = (SupplierComponentPK)other;
		return 
			this.componentId.equals(castOther.componentId)
			&& (this.supplierId == castOther.supplierId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.componentId.hashCode();
		hash = hash * prime + this.supplierId;
		
		return hash;
    }
}