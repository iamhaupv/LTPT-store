package rmi.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.service.Ical;

public class CalImpl extends UnicastRemoteObject implements Ical{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalImpl() throws RemoteException{
		
	}

	@Override
	public int sum(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a + b;
	}

	@Override
	public int sub(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a - b;
	}
}
