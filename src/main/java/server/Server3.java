package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dao.CategoryDao;
import dao.CustomerDao;
import entity.Category;
import entity.Customer;

public class Server3 {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(1973);
		System.out.println("server already ... ");
		while(true) {
			Socket socket = serverSocket.accept();
			CategoryHandle customerHandle = new CategoryHandle(socket);
			Thread thread = new Thread(customerHandle);
			thread.start();
		}
	}
}

class CategoryHandle implements Runnable{
	private CategoryDao customerDao;
	private Socket socket;
	public CategoryHandle(Socket socket) {
		customerDao = new CategoryDao();
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
				Category customer = customerDao.findById(Category.class, id);
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