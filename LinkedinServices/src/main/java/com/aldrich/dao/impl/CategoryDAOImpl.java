package com.aldrich.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.aldrich.dao.CategoryDAO;
import com.aldrich.entity.Category;

@SuppressWarnings({ "nls", "unchecked" })
@Repository
public class CategoryDAOImpl implements CategoryDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> checkForExistance(String categoryName) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from Category model where model.code = :categoryName");
		query.setParameter("categoryName", categoryName);
		return query.list();
	}

	@Override
	public Object save(Category category) {
		return this.sessionFactory.openSession().save(category);
	}

	@Override
	public List<Category> getCategoryByParentId(long parentId) {
		Query query = this.sessionFactory.openSession()
				.createQuery("select model from Category model where model.parentId = :parentId");
		query.setParameter("parentId", parentId);
		return query.list();
	}

}
