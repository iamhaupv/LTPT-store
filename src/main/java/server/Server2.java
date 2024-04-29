package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dao.CategoryDao;
import dao.CustomerDao;
import dao.ProductDao;
import entity.Category;
import entity.Customer;
import entity.Product;

public class Server2 {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(1973);
		System.out.println("server already ... ");
		while(true) {
			Socket socket = serverSocket.accept();
			ProductHandle customerHandle = new ProductHandle(socket);
			Thread thread = new Thread(customerHandle);
			thread.start();
		}
	}
}

class ProductHandle implements Runnable{
	private ProductDao customerDao;
	private Socket socket;
	public ProductHandle(Socket socket) {
		customerDao = new ProductDao();
		this.socket = socket;
	}
	@Override
	public void run() {
		try(
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				) {
			while(true) {
				String id = in.readUTF();
				Product customer = customerDao.findById(Product.class, id);
				String rs = "not found!";
				if(customer != null) {
					rs = customer.toString();
				}
				out.writeUTF(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}