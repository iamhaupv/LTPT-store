package demo;

import dao.CustomerDao;
import dao.ProductDao;

public class DemoSup {
	public static void main(String[] args) {
//		CustomerDao customerDao = new CustomerDao();
//		customerDao.getNumberCustomerByState().entrySet().forEach(e->{
//			System.out.println(e.getKey());
//			System.out.println(e.getValue());
//		});
//		
//		customerDao.getOrdersByCustomers().entrySet().forEach(e->{
//			System.out.println(e.getKey());
//			System.out.println(e.getValue());
//		});
		ProductDao productDao = new ProductDao();
//		productDao.getTotalProduct().entrySet().forEach(e->{
//			System.out.println(e.getKey());
//			System.out.println(e.getValue());
//		});
		productDao.getProductOrderIsNull().forEach(t -> System.out.println(t));
	}
}
