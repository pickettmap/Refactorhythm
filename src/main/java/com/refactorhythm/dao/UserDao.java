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

/**
 * This handles all Database Access for Reimbursements.
 * @author Mikayla Pickett
 * @version 1.0
 *
 */
public class UserDao implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	/**
	 * This returns a list of all the users in the users table
	 * @return Returns a list of user objects
	 */
	@Override
	public List<User> getList() {

		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery query = builder.createQuery(User.class);
			query.from(User.class);

			return session.createQuery(query).getResultList();
		}
	}

	/**
	 * This returns a single user by primary key user_id
	 * @param id userId of the user.
	 * @return Returns user object
	 */
	@Override
	public User getById(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.get(User.class, id);
		}
	}

	/**
	 * This returns a single user with matching username
	 * @param username username of the user.
	 * @return Returns user object
	 */
	public User getByUsername(String username) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from User where username= :username", User.class)
					.setParameter("username", username).uniqueResult();
		}
	}

	/**
	 * Inserts a user into the database
	 * @param u user object to be saved
	 */
	@Override
	public void insert(User u) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(u);
			transaction.commit();
		}
	}

	/**
	 * Updates corresponding user in the database with new user information
	 * @param u user object to be updated
	 */
	@Override
	public void update(User u) {
		try(Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(u);
			transaction.commit();
		}
	}

	/**
	 * Deletes corresponding user from the database
	 * @param u user object to be deleted from the database
	 */
	@Override
	public void delete(User u) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(u);
			transaction.commit();
		}
		
	}
}
