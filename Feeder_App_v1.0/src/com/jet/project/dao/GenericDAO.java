package com.jet.project.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jet.project.service.EMFService;
import com.jet.project.util.LogUtil;

public enum GenericDAO {
	INSTANCE;
	
	public <T> List<T> getAll(Class<T> clazz) {
		EntityManager em = EMFService.get().createEntityManager();
		LogUtil.log("Getting all rows from " + clazz.getSimpleName() + "...");
		
		Query q = em.createQuery("select m from "+ clazz.getSimpleName() +" m");
		List<T> todos = q.getResultList();
		
		LogUtil.log("Done getting rows from " + clazz.getSimpleName() + "...");
		return todos;
	}

	public <T> void save(T entity) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			//		      Todo todo = new Todo(userId, summary, description, url);
			LogUtil.log("Saving entity of type " + entity.getClass().getSimpleName() + "...");
			
			em.persist(entity);
			em.close();
			
			LogUtil.log("Entity saved.");
		}
	}

	public <T> void remove(Class<T> clazz, long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			LogUtil.log("Removing id no. " + id + " from type " + clazz.getSimpleName() + "...");
			
			T todo = (T) em.find(clazz, id);
			em.remove(todo);
			
			LogUtil.log("Successfully removed id no. " + id);
		} finally {
			em.close();
		}
	}
}