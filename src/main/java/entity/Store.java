package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="stores")
public class Store {
	@Id
	@Column(name="store_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="store_name")
	private String name;
	@Embedded
	private Address address;
	@Embedded
	private Contact contact;
	@OneToMany(mappedBy = "store")
	private List<Stock> stocks;
	@OneToMany(mappedBy = "store")
	private List<Staff> staffs;
	public Store() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Store(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + "]";
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Store(String name, Address address, Contact contact) {
		super();
		this.name = name;
		this.address = address;
		this.contact = contact;
	}
}
