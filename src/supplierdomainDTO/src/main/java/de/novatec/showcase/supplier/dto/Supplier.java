package de.novatec.showcase.supplier.dto;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name="Supplier", description="POJO that represents a supplier.")
public class Supplier {

	private Integer id;

	private String contact;

	private String name;

	private String replyUrl;

	private Integer version;

	private String wsUrl;

	private Address address;
	
    public Supplier() {
    }

	public Supplier(String name, String contact, String replyUrl, String wsUrl, Address address) {
		super();
		this.name = name;
		this.contact = contact;
		this.replyUrl = replyUrl;
		this.wsUrl = wsUrl;
		this.address = address;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id)
	{
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
	public String toString() {
		return "Supplier [id=" + id + ", contact=" + contact + ", name=" + name + ", replyUrl=" + replyUrl
				+ ", version=" + version + ", wsUrl=" + wsUrl + ", address=" + address + "]";
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
		return Objects.equals(address, other.address) && Objects.equals(contact, other.contact) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(replyUrl, other.replyUrl)
				&& version == other.version && Objects.equals(wsUrl, other.wsUrl);
	}
}