package de.novatec.showcase.supplier.ejb.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address 
{
	@Column(name = "SUPP_STREET1")
	private String street1;

	@Column(name = "SUPP_STREET2")
	private String street2;

	@Column(name = "SUPP_CITY")
	private String city;

	@Column(name = "SUPP_STATE")
	private String state;

	@Column(name = "SUPP_COUNTRY")
	private String country;

	@Column(name = "SUPP_ZIP")
	private String zip;

	@Column(name = "SUPP_PHONE")
	private String phone;

	public Address()
	{
		super();
	}

	public Address(String street1, String street2, String city, String state, String country,
			String zip, String phone)
	{
		super();
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
	}

	@Override
	public String toString()
	{
		return "Address [street1=" + street1 + ", street2=" + street2 + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zip=" + zip + ", phone=" + phone
				+ "]";
	}

	public String getStreet1()
	{
		return street1;
	}

	public void setStreet1(String street1)
	{
		this.street1 = street1;
	}

	public String getStreet2()
	{
		return street2;
	}

	public void setStreet2(String street2)
	{
		this.street2 = street2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}
