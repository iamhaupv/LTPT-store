package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dao.StaffDao;
import entity.Staff;

public class Server4 {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1973);
		System.out.println("Server already ....");
		while(true) {
			Socket socket = serverSocket.accept();
			StaffHandle handle = new StaffHandle(socket);
			Thread thread = new Thread(handle);
			thread.start();
		}
	}
}
class StaffHandle implements Runnable{
	private Socket socket;
	private StaffDao staffDao;
	public StaffHandle(Socket socket) {
		staffDao = new StaffDao();
		this.socket = socket;
	}
	@Override
	public void run() {
		try (
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				){
			String id = in.readUTF();
			Staff staff = staffDao.findById(Staff.class, id);
			String rs = "not found";
			if(staff != null) {
				rs = staff.toString();
			}
			out.writeUTF(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
