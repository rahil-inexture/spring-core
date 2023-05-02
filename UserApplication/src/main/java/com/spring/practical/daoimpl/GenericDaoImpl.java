package com.spring.practical.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.practical.dao.GenericDao;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<E, PK extends Serializable> implements GenericDao<E, PK>{
	protected Class<? extends E> daoType;
     
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class<E>) pt.getActualTypeArguments()[0];
    }
    
    @Autowired
    private SessionFactory sessionFactory;
    
    protected Session getSession() {
        if(sessionFactory == null) {
        	 throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        }
    	return sessionFactory.getCurrentSession();
    }
     
    @Override
    public Long add(E entity) {
        return (Long) getSession().save(entity);
    }
     
    @Override
    public void saveOrUpdate(E entity) {
    	getSession().saveOrUpdate(entity);
    }
     
    @Override
    public void update(E entity) {
    	getSession().saveOrUpdate(entity);
    }
     
    @Override
    public void remove(E entity) {
    	getSession().delete(entity);
    }
     
    @Override
    public E find(PK key) {
        return (E) getSession().get(this.daoType, key);
    }
     
    @Override
    public List<E> getAll() {
    	Criteria criteria = getSession().createCriteria(this.daoType);
    	return (List<E>) criteria.list();
    }
}
