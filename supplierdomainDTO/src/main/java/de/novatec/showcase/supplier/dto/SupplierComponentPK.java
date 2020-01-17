package de.novatec.showcase.supplier.dto;

import java.io.Serializable;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="SupplierComponentPK", description="POJO that represents a supplier component primary key.")
public class SupplierComponentPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(required=true)
	private String componentId;

	@Schema(required=true)
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
	public String toString() {
		return "SupplierComponentPK [componentId=" + componentId + ", supplierId=" + supplierId + "]";
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
		return Objects.equals(componentId, other.componentId) && supplierId == other.supplierId;
	}
}