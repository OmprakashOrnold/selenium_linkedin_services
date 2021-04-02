package com.aldrich.dao;

import java.util.List;

import com.aldrich.entity.Category;

public interface CategoryDAO {
	public List<Category> checkForExistance(String categoryName);

	public Object save(Category category);

	public List<Category> getCategoryByParentId(long parentId);

}
