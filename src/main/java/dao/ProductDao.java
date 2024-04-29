package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductDao extends GenericCRUD<Product>{
//--7) Tính tổng số lượng của từng sản phẩm đã bán ra.
//--+ getTotalProduct(): Map<Product, Integer>
	public Map<Product, Integer> getTotalProduct(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Map<Product, Integer> map = new HashMap<>();
		try {
			String sql = "select product_id, sum(quantity)\r\n"
				+ "from order_items\r\n"
				+ "group by product_id";
			List<Object[]> list = entityManager.createNativeQuery(sql, Object[].class).getResultList();
			for(Object[] o : list) {
				map.put(findById(Product.class, o[0]), (int)o[1]);
			}
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return map;
	}

//--2) Tìm danh sách sản phẩm có giá cao nhất.
	public List<Product> getProductListPriceMax() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Product> list = new ArrayList<>();
		entityTransaction.begin();
		try {
			String sql = "select *\r\n"
					+ "from products\r\n"
					+ "where list_price >= all(\r\n"
					+ "	select max(list_price)\r\n"
					+ "	from products\r\n"
					+ ")";
			list = entityManager.createNativeQuery(sql, Product.class).getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return list;
	}
	// san pham chua ban dc lan nao
	public List<Product> getProductOrderIsNull() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Product> list = new ArrayList<>();
		entityTransaction.begin();
		try {
			String sql = "select p.product_id, p.product_name\r\n"
					+ "from products p\r\n"
					+ "join order_items o\r\n"
					+ "on p.product_id = o.product_id\r\n"
					+ "where p.product_id not in (select distinct o.product_id from order_items)";
			list = entityManager.createNativeQuery(sql, Product.class).getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return list;
	}
}
