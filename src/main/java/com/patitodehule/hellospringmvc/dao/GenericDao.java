package com.patitodehule.hellospringmvc.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dsi
 * @param <T>
 */
public abstract class GenericDao<T> {
    
    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
    	this.entityClass = entityClass; 
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Transactional
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }
    
	@Transactional(readOnly = true)
    public T find(int id) {
        return ((T) sessionFactory.getCurrentSession().get(entityClass, id));
    }

    @Transactional
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public abstract boolean logicDelete(T entity);
    
    public abstract List<T> findAllActivos();

    @Transactional(readOnly = true)
    public List<T> findAll() {
        Criteria cri = sessionFactory.getCurrentSession().createCriteria(entityClass);
        return (List<T>) cri.list();
    }
}