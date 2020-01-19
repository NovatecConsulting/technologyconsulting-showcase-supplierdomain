package de.novatec.showcase.supplier.dto;

import java.math.BigDecimal;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="SupplierComponent", description="POJO that represents a supplier component.")
public class SupplierComponent {

	private String componentId;
	
	private Integer supplierId;

	private int deliveryInDays;

	private BigDecimal discount;

	private BigDecimal price;

	private int quantityForDiscount;

	private Integer version;

    public SupplierComponent() {
    }

	public SupplierComponent(Integer supplierId, String componentId, int deliveryInDays, BigDecimal discount, BigDecimal price,
			int quantityForDiscount) {
		super();
		this.supplierId = supplierId;
		this.componentId = componentId;
		this.deliveryInDays = deliveryInDays;
		this.discount = discount;
		this.price = price;
		this.quantityForDiscount = quantityForDiscount;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public int getDeliveryInDays() {
		return this.deliveryInDays;
	}

	public void setDeliveryInDays(int deliveryInDays) {
		this.deliveryInDays = deliveryInDays;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantityForDiscount() {
		return this.quantityForDiscount;
	}

	public void setQuantityForDiscount(int quantityForDiscount) {
		this.quantityForDiscount = quantityForDiscount;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "SupplierComponent [componentId=" + componentId + ", supplierId=" + supplierId + ", deliveryInDays="
				+ deliveryInDays + ", discount=" + discount + ", price=" + price + ", quantityForDiscount="
				+ quantityForDiscount + ", version=" + version + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(componentId, deliveryInDays, discount, price, quantityForDiscount, supplierId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupplierComponent)) {
			return false;
		}
		SupplierComponent other = (SupplierComponent) obj;
		return Objects.equals(componentId, other.componentId) && deliveryInDays == other.deliveryInDays
				&& Objects.equals(discount, other.discount) && Objects.equals(price, other.price)
				&& quantityForDiscount == other.quantityForDiscount && Objects.equals(supplierId, other.supplierId)
				&& version == other.version;
	}

}