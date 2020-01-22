package de.novatec.showcase.supplier.ejb.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "S_PURCHASE_ORDER")
@NamedQueries(value = {
		@NamedQuery(name = PurchaseOrder.FIND_ALL_PURCHASEORDER, query = PurchaseOrder.FIND_ALL_PURCHASEORDER_QUERY), })
public class PurchaseOrder {

	public static final String FIND_ALL_PURCHASEORDER = "FIND_ALL_PURCHASEORDER";

	public static final String FIND_ALL_PURCHASEORDER_QUERY = "Select p from PurchaseOrder as p";

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "PO_NUMBER", nullable = false)
	private Integer poNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "PO_SENT_DATE")
	private Date sentDate;

	@Column(name = "PO_SITE_ID", nullable = false)
	private int siteId;

	@Column(name = "PO_START_DATE")
	private Timestamp startDate;

	@Column(name = "PO_SUPP_ID")
	private Integer supplierId;

	@Version
	@Column(name = "PO_VERSION")
	private Integer version;

	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
	private Collection<PurchaseOrderLine> purchaseOrderlines;

	public PurchaseOrder() {
		super();
	}

	public PurchaseOrder(int siteId, Integer supplierId, Timestamp startDate) {
		super();
		this.siteId = siteId;
		this.startDate = startDate;
		this.supplierId = supplierId;
		this.purchaseOrderlines = new ArrayList<PurchaseOrderLine>();
	}

	public Integer getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(Integer poNumber) {
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

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Collection<PurchaseOrderLine> getPurchaseOrderlines() {
		return this.purchaseOrderlines;
	}

	public void setPurchaseOrderlines(Collection<PurchaseOrderLine> purchaseOrderlines) {
		this.purchaseOrderlines = purchaseOrderlines;
	}

	public void addPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
		this.purchaseOrderlines.add(purchaseOrderLine);
	}

	@Override
	public int hashCode() {
		return Objects.hash(poNumber, purchaseOrderlines, sentDate, siteId, startDate, supplierId, version);
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
		return Objects.equals(poNumber, other.poNumber) && Objects.equals(purchaseOrderlines, other.purchaseOrderlines)
				&& Objects.equals(sentDate, other.sentDate) && siteId == other.siteId
				&& Objects.equals(startDate, other.startDate) && Objects.equals(supplierId, other.supplierId)
				&& Objects.equals(version, other.version);
	}

	@Override
	public String toString() {
		return "PurchaseOrder [poNumber=" + poNumber + ", sentDate=" + sentDate + ", siteId=" + siteId + ", startDate="
				+ startDate + ", supplierId=" + supplierId + ", version=" + version + ", purchaseOrderlines="
				+ purchaseOrderlines + "]";
	}
	
}