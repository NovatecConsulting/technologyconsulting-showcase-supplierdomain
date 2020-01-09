package de.novatec.showcase.supplier.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.novatec.showcase.supplier.GlobalConstants;

@Schema(name="PurchaseOrder", description="POJO that represents a purchase order.")
public class PurchaseOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int poNumber;

	@JsonFormat(pattern = GlobalConstants.DATE_FORMAT, locale = "de_DE")
	private Date sentDate;

	private int siteId;

	@JsonFormat(pattern = GlobalConstants.DATE_FORMAT, locale = "de_DE")
	private Timestamp startDate;

	private Integer supplierId;

	private int version;

	private Collection<PurchaseOrderLine> purchaseOrderlines;

	public PurchaseOrder() {
	}

	public PurchaseOrder(int siteId, Integer supplierId, Timestamp startDate, int version) {
		super();
		this.siteId = siteId;
		this.startDate = startDate;
		this.supplierId = supplierId;
		this.version = version;
		this.purchaseOrderlines = new ArrayList<PurchaseOrderLine>();
	}

	public int getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(int poNumber) {
		this.poNumber = poNumber;
	}

	public Date getSentDate() {
		return this.sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Collection<PurchaseOrderLine> getPurchaseOrderlines() {
		return this.purchaseOrderlines;
	}

	public void setPurchaseOrderlines(Collection<PurchaseOrderLine> purchaseOrderlines) {
		this.purchaseOrderlines = purchaseOrderlines;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [poNumber=" + poNumber + ", sentDate=" + sentDate + ", siteId=" + siteId + ", startDate="
				+ startDate + ", supplierId=" + supplierId + ", version=" + version + ", purchaseOrderlines="
				+ purchaseOrderlines + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchaseOrderlines, poNumber, sentDate, siteId, startDate, supplierId, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PurchaseOrder)) {
			return false;
		}
		PurchaseOrder other = (PurchaseOrder) obj;
		return Objects.equals(purchaseOrderlines, other.purchaseOrderlines) && poNumber == other.poNumber
				&& Objects.equals(sentDate, other.sentDate) && siteId == other.siteId
				&& Objects.equals(startDate, other.startDate) && supplierId == other.supplierId
				&& version == other.version;
	}

}