package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rmi.service.Ical;
import rmi.service.impl.CalImpl;


public class Server5 {
	public static void main(String[] args) throws RemoteException, NamingException {
		LocateRegistry.createRegistry(1973);
		Context context = new InitialContext();
		Ical ical = new CalImpl(); 
		context.bind("rmi://HP:1973/ical", ical);
		System.out.println("server is ready ...");
	}
}
