package de.novatec.showcase.supplier.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


public class SupplierComponent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SupplierComponentPK pk;

	private int deliveryInDays;

	private BigDecimal discount;

	private BigDecimal price;

	private int quantityForDiscount;

	private int version;

    public SupplierComponent() {
    }

	public SupplierComponent(SupplierComponentPK pk, int deliveryInDays, BigDecimal discount, BigDecimal price,
			int quantityForDiscount, int version) {
		super();
		this.pk = pk;
		this.deliveryInDays = deliveryInDays;
		this.discount = discount;
		this.price = price;
		this.quantityForDiscount = quantityForDiscount;
		this.version = version;
	}

	public SupplierComponentPK getPk() {
		return this.pk;
	}

	public void setPk(SupplierComponentPK pk) {
		this.pk = pk;
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

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "SupplierComponent [pk=" + pk + ", deliveryInDays=" + deliveryInDays + ", discount=" + discount
				+ ", price=" + price + ", quantityForDiscount=" + quantityForDiscount + ", version=" + version + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(pk, deliveryInDays, discount, price, quantityForDiscount, version);
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
		return Objects.equals(pk, other.pk) && deliveryInDays == other.deliveryInDays
				&& Objects.equals(discount, other.discount) && Objects.equals(price, other.price)
				&& quantityForDiscount == other.quantityForDiscount && version == other.version;
	}

}