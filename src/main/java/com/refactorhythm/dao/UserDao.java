package com.refactorhythm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.refactorhythm.model.Reimbursement;
import com.refactorhythm.util.SessionUtility;
import org.apache.log4j.Logger;

import com.refactorhythm.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	
	@Override
	public List<User> getList() {

		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery query = builder.createQuery(User.class);
			query.from(User.class);

			return session.createQuery(query).getResultList();
		}
	}

	@Override
	public User getById(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.get(User.class, id);
		}
	}

	@Deprecated
	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from User where username= :username", User.class)
					.setParameter("username", username).uniqueResult();
		}
	}

	@Override
	public void insert(User t) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(t);
			transaction.commit();
		}
	}

	@Override
	public void update(User t) {

	}

	@Override
	public void delete(User t) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(t);
			transaction.commit();
		}
		
	}
}
