package de.novatec.showcase.supplier.ejb.entity;

import java.util.Objects;

public class SupplierComponentPK {

	private String componentId;

	private Integer supplierId;

    public SupplierComponentPK() {
    }
    
	public SupplierComponentPK(String componentId, Integer supplierId) {
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
	public Integer getSupplierId() {
		return this.supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(componentId, supplierId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupplierComponentPK)) {
			return false;
		}
		SupplierComponentPK other = (SupplierComponentPK) obj;
		return Objects.equals(componentId, other.componentId) && Objects.equals(supplierId, other.supplierId);
	}

	@Override
	public String toString() {
		return "SupplierComponentPK [componentId=" + componentId + ", supplierId=" + supplierId + "]";
	}

}