package de.novatec.showcase.supplier.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class SComponent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private BigDecimal currentBestPartCost;

	private String description;

	private String partName;

	private int siteId;

	private String unitOfMeasure;

	private Integer version;

	private int containerSize;

	private int leadTime;

	private int quantityDemanded;

	private int quantityOnOrder;

	public SComponent() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCurrentBestPartCost() {
		return this.currentBestPartCost;
	}

	public void setCurrentBestPartCost(BigDecimal currentBestPartCost) {
		this.currentBestPartCost = currentBestPartCost;
	}

	public String getDescription() {
		return this.description;
	}

	public void getDescription(String description) {
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
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

	@Override
	public String toString() {
		return "SComponent [id=" + id + ", currentBestPartCost=" + currentBestPartCost + ", description=" + description
				+ ", partName=" + partName + ", siteId=" + siteId + ", unitOfMeasure=" + unitOfMeasure + ", version="
				+ version + ", containerSize=" + containerSize + ", leadTime=" + leadTime + ", quantityDemanded="
				+ quantityDemanded + ", quantityOnOrder=" + quantityOnOrder + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(currentBestPartCost, description, id, partName, siteId, unitOfMeasure, version, containerSize,
				leadTime, quantityDemanded, quantityOnOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SComponent)) {
			return false;
		}
		SComponent other = (SComponent) obj;
		return Objects.equals(currentBestPartCost, other.currentBestPartCost) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(partName, other.partName)
				&& siteId == other.siteId && Objects.equals(unitOfMeasure, other.unitOfMeasure)
				&& version == other.version && containerSize == other.containerSize
				&& leadTime == other.leadTime && quantityDemanded == other.quantityDemanded && quantityOnOrder == other.quantityOnOrder;
	}

}