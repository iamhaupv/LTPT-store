package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Customer;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CustomerDao extends GenericCRUD<Customer>{
//	4) Thống kê số khách hàng theo từng bang.
//	+ getNumberCustomerByState() : Map<String, Integer>
	public Map<String, Integer> getNumberCustomerByState(){
		EntityManager maEntityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = maEntityManager.getTransaction();
		entityTransaction.begin();
		Map<String, Integer> map = new HashMap<>();
		try {
			String sql = "select state, count(customer_id) \r\n"
					+ "from customers\r\n"
					+ "group by state";
			List<Object[]> list = maEntityManager.createNativeQuery(sql, Object[].class).getResultList();
			System.out.println(list);
			for(Object[] o : list) {
				String rs = (String) o[0];
				map.put(rs, (int)o[1]);
			}
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return map;
	}
//	--6) Đếm số đơn hàng của từng khách hàng.
//	--+ getOrdersByCustomers() : Map<Customer, Integer>
	public Map<Customer, Integer> getOrdersByCustomers(){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Map<Customer, Integer> map = new HashMap<>();
		try {
			String sql = "select c.customer_id, count(order_id)\r\n"
					+ "from customers c \r\n"
					+ "join orders o \r\n"
					+ "on c.customer_id = o.customer_id\r\n"
					+ "group by c.customer_id";
			List<Object[]> list = entityManager.createNativeQuery(sql, Object[].class).getResultList();
			for(Object[] o : list) {
				map.put(findById(Customer.class, o[0]), (int)o[1]);
			}
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return map;
	}
}
