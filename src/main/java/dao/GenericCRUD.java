package dao;

import java.util.ArrayList;
import java.util.List;

import db.Connect;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public abstract class GenericCRUD<T> {
	protected EntityManagerFactory entityManagerFactory;
	public GenericCRUD() {
		entityManagerFactory = Connect.getInstance().getEntityManagerFactory();
	}
	// add 
	public boolean add(T t) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.persist(t);
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return false;
	}
	// update
	public boolean update(T t) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.merge(t);
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return false;
	}
	// delete
	public boolean delete(Class<T> cl, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.remove(entityManager.find(cl, id));
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return false;
	}
	// findById
	public T findById(Class<T> cl, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		T t = null;
		try {
			t = entityManager.find(cl, id);
			entityTransaction.commit();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return null;
	}
	// getAll
	public List<T> getAll(Class<T> cl){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<T> list = new ArrayList<>();
		try {
			list = entityManager.createQuery("from " + cl.getName(), cl).getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		return list;
	}
}
