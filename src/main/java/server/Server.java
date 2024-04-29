package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import dao.BrandDao;
import entity.Brand;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1973);
		System.out.println("server already ...");
		
		while(true) {
			Socket socket = serverSocket.accept();
			BranHandle branHandle = new BranHandle(socket);
			Thread thread = new Thread(branHandle);
			thread.start();
		}
	}
}
class BranHandle implements Runnable{
	private Socket socket;
	private BrandDao brandDao;
	public BranHandle(Socket socket) {
		this.socket = socket;
		brandDao = new BrandDao();
	}
	@Override
	public void run() {
		try(
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				) {
			while(true) {
				int id = in.readInt();
				Brand brand = brandDao.findById(Brand.class, id);
				String rs = "not found!";
				if(brand != null) 
					rs = brand.toString();
				out.writeUTF(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
