package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {
	@Column(columnDefinition = "nvarchar(25)")
	private String phone;
	private String email;
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Contact [phone=" + phone + ", email=" + email + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
	}
	
}
