package de.novatec.showcase.supplier.dto;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="PurchaseOrderLinePK", description="POJO that represents a purchase order primary key.")
public class PurchaseOrderLinePK {

	@Schema(required=true)
	private Integer polNumber;

	@Schema(required=true)
	private Integer poNumber;

	public PurchaseOrderLinePK() {
		super();
	}

	public PurchaseOrderLinePK(Integer polNumber, Integer poNumber) {
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

	public void setPoNumber(Integer polNumber) {
		this.poNumber = polNumber;
	}

	@Override
	public String toString() {
		return "PurchaseOrderLinePK [polNumber=" + polNumber + ", poNumber=" + poNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(polNumber, poNumber);
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
		return polNumber == other.polNumber && poNumber == other.poNumber;
	}
}