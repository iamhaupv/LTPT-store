package rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ical extends Remote{
	public int sum(int a, int b) throws RemoteException;
	public int sub(int a, int b) throws RemoteException;
}
