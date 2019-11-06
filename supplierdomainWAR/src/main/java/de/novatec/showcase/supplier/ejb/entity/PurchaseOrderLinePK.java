package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PurchaseOrderLinePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer polNumber; 

	private Integer poNumber;

	public PurchaseOrderLinePK()
	{
		super();
	}
    
	public PurchaseOrderLinePK(Integer polNumber, Integer poNumber)
	{
		super();
		this.polNumber = polNumber;
		this.poNumber = poNumber;
	}
	public Integer getPolNumber() {
		return this.polNumber;
	}
	public void setPolNumber(Integer polNumber) {
		this.polNumber = polNumber;
	}
	public Integer getPoNumber() {
		return this.poNumber;
	}
	public void setPoNumber(Integer poNumber) {
		this.poNumber = poNumber;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PurchaseOrderLinePK)) {
			return false;
		}
		PurchaseOrderLinePK castOther = (PurchaseOrderLinePK)other;
		return 
			(this.polNumber == castOther.polNumber)
			&& (this.poNumber == castOther.poNumber);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.polNumber;
		hash = hash * prime + this.poNumber;
		
		return hash;
    }

	@Override
	public String toString() {
		return "PurchaseOrderLinePK [polNumber=" + polNumber + ", polPoId=" + poNumber + "]";
	}
}