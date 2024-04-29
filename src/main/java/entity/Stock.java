package entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="stocks")
public class Stock {
	@Column(nullable = false)
	private int quantity;
	@Id
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@Id
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	@Override
	public int hashCode() {
		return Objects.hash(product, store);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(product, other.product) && Objects.equals(store, other.store);
	}
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Stock(int quantity) {
		super();
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Stock [quantity=" + quantity + "]";
	}
}
