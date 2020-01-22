package de.novatec.showcase.supplier.ejb.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "S_SUPP_COMPONENT")
@NamedQueries(value = {
		@NamedQuery(name = SupplierComponent.FIND_ALL_SUPPCOMPONENT, query = SupplierComponent.FIND_ALL_SUPPCOMPONENT_QUERY),
		@NamedQuery(name = SupplierComponent.FIND_SUPPCOMPONENT_BY_COMPONENT_ID, query = SupplierComponent.FIND_SUPPCOMPONENT_BY_COMPONENT_ID_QUERY),
		@NamedQuery(name = SupplierComponent.FIND_SUPPCOMPONENT_BY_SC_SUPP_ID, query = SupplierComponent.FIND_SUPPCOMPONENT_BY_SC_SUPP_ID_QUERY) })
@IdClass(SupplierComponentPK.class)
public class SupplierComponent {

	public static final String FIND_SUPPCOMPONENT_BY_COMPONENT_ID = "FIND_SUPPCOMPONENT_BY_COMPONENT_ID";

	public static final String FIND_ALL_SUPPCOMPONENT = "FIND_ALL_SUPPCOMPONENT";

	public static final String FIND_SUPPCOMPONENT_BY_SC_SUPP_ID = "FIND_SUPPCOMPONENT_BY_SC_SUPP_ID";

	public static final String FIND_ALL_SUPPCOMPONENT_QUERY = "SELECT s FROM SupplierComponent s";

	public static final String FIND_SUPPCOMPONENT_BY_COMPONENT_ID_QUERY = "SELECT sc FROM SupplierComponent sc WHERE sc.componentId = :id";

	public static final String FIND_SUPPCOMPONENT_BY_SC_SUPP_ID_QUERY = "SELECT sc FROM SupplierComponent sc WHERE sc.supplierId = :id";

	@Id
	@Column(name = "SC_P_ID", nullable = false, length = 20)
	private String componentId;
	
	@Id
	@Column(name = "SC_SUPP_ID", nullable = false)
	private Integer supplierId;

	@Column(name = "SC_DEL_DATE")
	private int deliveryInDays;

	@Column(name = "SC_DISCOUNT")
	private BigDecimal discount;

	@Column(name = "SC_PRICE", precision = 12, scale = 2)
	private BigDecimal price;

	@Column(name = "SC_QTY", precision = 6, scale = 4)
	private int quantityForDiscount;

	@Version
	@Column(name = "SC_VERSION")
	private Integer version;

	public SupplierComponent() {
	}

	public int getDeliveryInDays() {
		return this.deliveryInDays;
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

	@Override
	public String toString() {
		return "SupplierComponent [componentId=" + componentId + ", supplierId=" + supplierId + ", deliveryInDays="
				+ deliveryInDays + ", discount=" + discount + ", price=" + price + ", quantityForDiscount="
				+ quantityForDiscount + ", version=" + version + "]";
	}

}