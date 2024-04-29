package entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="order_items")
public class OrderItem {
	@Column(nullable =  false)
	private int quantity;
	@Column(name="list_price", columnDefinition = "decimal(10, 2)")
	private double listPrice;
	@Column(columnDefinition = "decimal(4, 2)")
	private double discount;
	@Id
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	@Id
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public OrderItem(int quantity, double listPrice, double discount) {
		super();
		this.quantity = quantity;
		this.listPrice = listPrice;
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "OrderItem [quantity=" + quantity + ", listPrice=" + listPrice + ", discount=" + discount + "]";
	}
}
