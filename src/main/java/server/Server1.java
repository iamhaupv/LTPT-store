package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dao.CustomerDao;
import entity.Customer;

public class Server1 {
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket = new ServerSocket(1973);
		System.out.println("server already ... ");
		while(true) {
			Socket socket = serverSocket.accept();
			CustomerHandle customerHandle = new CustomerHandle(socket);
			Thread thread = new Thread(customerHandle);
			thread.start();
		}
	}
}

class CustomerHandle implements Runnable{
	private CustomerDao customerDao;
	private Socket socket;
	public CustomerHandle(Socket socket) {
		customerDao = new CustomerDao();
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
				Customer customer = customerDao.findById(Customer.class, id);
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