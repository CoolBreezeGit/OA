package com.coolbreeze.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.springframework.transaction.annotation.Transactional;

@Transactional		//必须要开启事务，否则getCurrentSession不能成功！！子类可以继承
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	protected ProcessEngine processEngine;
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz;

	public BaseDaoImpl() {
		//通过反射得到真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}

	public T getById(Long id) { 
		
		//判断id是否为空，否则如果id为null，会出异常
		if(id == null){
			return null;
		}
		
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		
		//判断ids是否为空，否则如果ids为null或空，会出异常
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	/**
	 * 获取当前session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
