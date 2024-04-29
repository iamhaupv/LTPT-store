package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="product_name")
	private String name;
	@Column(name="model_year")
	private int modelYear;
	@Column(name="list_price", columnDefinition = "decimal(10, 2)")
	private double listPrice;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	@OneToMany(mappedBy = "product")
	private List<Stock> stocks;
	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String name, int modelYear, double listPrice) {
		super();
		this.name = name;
		this.modelYear = modelYear;
		this.listPrice = listPrice;
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
	public int getModelYear() {
		return modelYear;
	}
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}
	public double getListPrice() {
		return listPrice;
	}
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", modelYear=" + modelYear + ", listPrice=" + listPrice + "]";
	}
}
