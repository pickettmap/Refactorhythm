package com.refactorhythm.dao;

import java.util.List;

/**
 * A set of contractual functionality for the concrete DAOs.
 * @param <T> the object to instantiate the dao.
 */
public interface GenericDao <T> {
	List<T> getList();
	T getById(int id);
	void insert(T t);
	void update(T t);
	void delete(T t);
}
