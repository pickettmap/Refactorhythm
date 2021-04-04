package com.refactorhythm.dao;

import java.util.List;

public interface GenericDao <T> {
	List<T> getList();
	T getById(int id);
	void insert(T t);
	void update(T t);
	void delete(T t);
}
