package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;
import java.util.Objects;

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


	@Override
	public int hashCode() {
		return Objects.hash(poNumber, polNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PurchaseOrderLinePK)) {
			return false;
		}
		PurchaseOrderLinePK other = (PurchaseOrderLinePK) obj;
		return Objects.equals(poNumber, other.poNumber) && Objects.equals(polNumber, other.polNumber);
	}

	@Override
	public String toString() {
		return "PurchaseOrderLinePK [polNumber=" + polNumber + ", polPoId=" + poNumber + "]";
	}
}