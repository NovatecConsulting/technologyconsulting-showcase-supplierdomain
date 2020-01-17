package de.novatec.showcase.supplier.ejb.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity
@Table(name = "S_SUPPLIER")
@NamedQueries(value = { @NamedQuery(name = Supplier.ALL_SUPPLIERS, query = Supplier.ALL_SUPPLIERS_QUERY),
		@NamedQuery(name = Supplier.SUPPLIER_BY_SUPPLIERCOMPONENT_ID, query = Supplier.SUPPLIER_BY_SUPPLIERCOMPONENT_ID_QUERY),
		@NamedQuery(name = Supplier.LAST_PURCHASEORDER_NUMBER, query = Supplier.LAST_PURCHASEORDER_NUMBER_QUERY) })
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ALL_SUPPLIERS = "ALL_SUPPLIERS";
	public static final String SUPPLIER_BY_SUPPLIERCOMPONENT_ID = "SUPPLIER_BY_SUPPLIERCOMPONENT_ID";
	public static final String LAST_PURCHASEORDER_NUMBER = "LAST_PURCHASEORDER_NUMBER";

	// @formatter:off
	public static final String ALL_SUPPLIERS_QUERY = "SELECT s FROM Supplier s";

	public static final String SUPPLIER_BY_SUPPLIERCOMPONENT_ID_QUERY = "SELECT s FROM Supplier s, SupplierComponent sc WHERE s.id = sc.supplierId AND sc.componentId = :componentId";

	public static final String LAST_PURCHASEORDER_NUMBER_QUERY = "SELECT MAX(po.poNumber) FROM Supplier s, PurchaseOrder po WHERE po.supplierId = s.id AND s.id = :id";
	// @formatter:on

	@Id
	@Column(name = "SUPP_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "S_ID_GEN")
	@TableGenerator(name = "S_ID_GEN", table = "U_SEQUENCES", pkColumnName = "S_ID", valueColumnName = "S_NEXTNUM", pkColumnValue = "S_SEQ", allocationSize = 1)
	private Integer id;

	@Column(name = "SUPP_CONTACT", length = 25)
	private String contact;

	@Column(name = "SUPP_NAME", length = 16)
	private String name;

	@Column(name = "SUPP_REPLY_URL",length = 128)
	private String replyUrl;

	@Column(name = "SUPP_WS_URL", length = 128)
	private String wsUrl;

	@Version
	@Column(name = "SUPP_VERSION")
	private Integer version;
	
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street1", column = @Column(name = "SUPP_STREET1", length = 20)),
			@AttributeOverride(name = "street2", column = @Column(name = "SUPP_STREET2", length = 20)),
			@AttributeOverride(name = "city", column = @Column(name = "SUPP_CITY", length = 20)),
			@AttributeOverride(name = "state", column = @Column(name = "SUPP_STATE", length = 2)),
			@AttributeOverride(name = "country", column = @Column(name = "SUPP_COUNTRY", length = 10)),
			@AttributeOverride(name = "zip", column = @Column(name = "SUPP_ZIP", length = 9)),
			@AttributeOverride(name = "phone", column = @Column(name = "SUPP_PHONE", length = 16)) })
	private Address address;

	public Supplier() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReplyUrl() {
		return this.replyUrl;
	}

	public void setReplyUrl(String replyUrl) {
		this.replyUrl = replyUrl;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getWsUrl() {
		return this.wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, contact, id, name, replyUrl, version, wsUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Supplier)) {
			return false;
		}
		Supplier other = (Supplier) obj;
		return Objects.equals(address, other.address) && Objects.equals(contact, other.contact)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(replyUrl, other.replyUrl) && Objects.equals(version, other.version)
				&& Objects.equals(wsUrl, other.wsUrl);
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", contact=" + contact + ", name=" + name + ", replyUrl=" + replyUrl
				+ ", version=" + version + ", wsUrl=" + wsUrl + ", address=" + address + "]";
	}
}