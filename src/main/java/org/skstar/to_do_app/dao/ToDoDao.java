package org.skstar.to_do_app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.skstar.to_do_app.dto.Task;
import org.skstar.to_do_app.dto.User;

public class ToDoDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sakline");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void insert(User user) {
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
	}
	
	public List<User> findByMob(long umob) {
	
	return 	entityManager.createQuery("select y from User y where umob=?1").setParameter(1, umob).getResultList();
		
	}
	
	public List<User> findByEmail(String umail) {
	
		return entityManager.createQuery("select y from User y where umail=?1").setParameter(1, umail).getResultList();
		
	}
	
	
	
	public void addTask(Task task) {
		entityTransaction.begin();
		entityManager.persist(task);
		entityTransaction.commit();
	}
	
	
	public List<Task> fetachAllTaskByUserId(int user_id) {
		
		List<Task> task = 	entityManager.createQuery("select x from Task x where user_id=?1").setParameter(1, user_id).getResultList();
		
		return task;
	}
	
	
	public Task fetachTaskById(int id) {
		
		return entityManager.find(Task.class, id);
		
	}
	
	public void updateTask(Task task) {
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
		
	}
	
	public void deleteTask(Task task) {
		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
		
	}
	
	
	
	
	
	
	
	
	
	
}
