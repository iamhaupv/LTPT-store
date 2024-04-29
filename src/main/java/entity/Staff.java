package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="staffs")
@AttributeOverrides({
	@AttributeOverride(name="id", column = @Column(name="staff_id"))
})
public class Staff extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte active;
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Staff manager;
	@OneToMany(mappedBy = "staff")
	private List<Order> orders;
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	public Staff() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Staff [active=" + active + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contact=" + contact + "]";
	}
	public Staff(String firstName, String lastName, Contact contact, byte active) {
		super(firstName, lastName, contact);
		this.active = active;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	
}
