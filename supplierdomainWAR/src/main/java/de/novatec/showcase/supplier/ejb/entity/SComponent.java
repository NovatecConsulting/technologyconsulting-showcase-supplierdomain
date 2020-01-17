package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "S_COMPONENT")
@NamedQueries({ @NamedQuery(name = SComponent.ALL_SCOMPONENT, query = SComponent.ALL_SCOMPONENT_QUERY) })
public class SComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ALL_SCOMPONENT = "ALL_SCOMPONENT";

	public static final String ALL_SCOMPONENT_QUERY = "SELECT sc FROM SComponent sc";

	@Id
	@Column(name = "COMP_ID", nullable = false, length = 20)
	private String id;

	@Column(name = "COMP_COST", precision = 12, scale = 2)
	private BigDecimal currentBestPartCost;

	@Column(name = "COMP_DESC", length = 100)
	private String description;

	@Column(name = "COMP_NAME", length = 10)
	private String partName;

	@Column(name = "COMP_SITE_ID")
	private int siteId;

	@Column(name = "COMP_UNIT", length = 10)
	private String unitOfMeasure;

	@Column(name = "COMP_VERSION")
	private int version;

	@Column(name = "CONTAINER_SIZE")
	private int containerSize;

	@Column(name = "LEAD_TIME")
	private int leadTime;

	@Column(name = "QTY_DEMANDED")
	private int quantityDemanded;

	@Column(name = "QTY_ON_ORDER")
	private int quantityOnOrder;

	public SComponent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCurrentBestPartCostCost() {
		return this.currentBestPartCost;
	}

	public void setCurrentBestPartCostCost(BigDecimal currentBestPartCost) {
		this.currentBestPartCost = currentBestPartCost;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getUnitOfMeasure() {
		return this.unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getContainerSize() {
		return this.containerSize;
	}

	public void setContainerSize(int containerSize) {
		this.containerSize = containerSize;
	}

	public int getLeadTime() {
		return this.leadTime;
	}

	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}

	public int getQuantityDemanded() {
		return this.quantityDemanded;
	}

	public void setQuantityDemanded(int quantityDemanded) {
		this.quantityDemanded = quantityDemanded;
	}

	public int getQuantityOnOrder() {
		return this.quantityOnOrder;
	}

	public void setQuantityOnOrder(int quantityOnOrder) {
		this.quantityOnOrder = quantityOnOrder;
	}

}