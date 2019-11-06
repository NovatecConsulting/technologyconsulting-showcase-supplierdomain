package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "S_SUPP_COMPONENT")
@NamedQueries(value = {
		@NamedQuery(name = SupplierComponent.FIND_ALL_SUPPCOMPONENT, query = SupplierComponent.FIND_ALL_SUPPCOMPONENT_QUERY),
		@NamedQuery(name = SupplierComponent.FIND_SUPPCOMPONENT_BY_COMPONENT_ID, query = SupplierComponent.FIND_SUPPCOMPONENT_BY_COMPONENT_ID_QUERY),
		@NamedQuery(name = SupplierComponent.FIND_SUPPCOMPONENT_BY_SC_SUPP_ID, query = SupplierComponent.FIND_SUPPCOMPONENT_BY_SC_SUPP_ID_QUERY) })
public class SupplierComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_SUPPCOMPONENT_BY_COMPONENT_ID = "FIND_SUPPCOMPONENT_BY_COMPONENT_ID";

	public static final String FIND_ALL_SUPPCOMPONENT = "FIND_ALL_SUPPCOMPONENT";

	public static final String FIND_SUPPCOMPONENT_BY_SC_SUPP_ID = "FIND_SUPPCOMPONENT_BY_SC_SUPP_ID";

	public static final String FIND_ALL_SUPPCOMPONENT_QUERY = "SELECT s FROM SupplierComponent s";

	public static final String FIND_SUPPCOMPONENT_BY_COMPONENT_ID_QUERY = "SELECT sc FROM SupplierComponent sc WHERE sc.pk.componentId = :id";

	public static final String FIND_SUPPCOMPONENT_BY_SC_SUPP_ID_QUERY = "SELECT sc FROM SupplierComponent sc WHERE sc.pk.supplierId = :id";

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "componentId", column = @Column(name = "SC_P_ID")),
			@AttributeOverride(name = "supplierId", column = @Column(name = "SC_SUPP_ID")) })
	private SupplierComponentPK pk;

	@Column(name = "SC_DEL_DATE")
	private int deliveryInDays;

	@Column(name = "SC_DISCOUNT")
	private BigDecimal discount;

	@Column(name = "SC_PRICE")
	private BigDecimal price;

	@Column(name = "SC_QTY")
	private int quantityForDiscount;

	@Column(name = "SC_VERSION")
	private int version;

	public SupplierComponent() {
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

}