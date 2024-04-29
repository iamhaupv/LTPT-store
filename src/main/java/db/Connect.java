package db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connect {
	private EntityManagerFactory entityManagerFactory;
	private static Connect instance;
	public Connect() {
		entityManagerFactory = Persistence.createEntityManagerFactory("PhamVanHauDemo2");
	}
	public static Connect getInstance() {
		if(instance == null)
			instance = new Connect();
		return instance;
	}
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
