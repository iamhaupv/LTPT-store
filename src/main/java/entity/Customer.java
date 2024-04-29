package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
@AttributeOverrides({
	@AttributeOverride(name="id", column = @Column(name="customer_id"))
})
public class Customer extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Embedded
	private Address address;
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customer [address=" + address + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contact=" + contact + "]";
	}
	public Customer(String firstName, String lastName, Contact contact, Address address) {
		super(firstName, lastName, contact);
		this.address = address;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
