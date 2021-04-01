package com.refactorhythm.dao;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.refactorhythm.util.SessionUtility;
import org.apache.log4j.Logger;

import com.refactorhythm.model.Reimbursement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.postgresql.util.PSQLException;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class ReimbursementDao implements GenericDao<Reimbursement> {
	private static final Logger LOGGER = Logger.getLogger(ReimbursementDao.class);

	@Override
	public List<Reimbursement> getList() {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from Reimbursement", Reimbursement.class).list();
		}
	}

	@Override
	public Reimbursement getById(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.get(Reimbursement.class, id);
		}
	}
	
	@Override
	public List<Reimbursement> getByUserId(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from Reimbursement where author= :author", Reimbursement.class)
			.setParameter("author", id).getResultList();
		}
	}

	@Deprecated
	public Reimbursement getByUsername(String username) {
		//Empty. Reason - No use.
		return null;
	}

	@Override
	public void insert(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(r);
			transaction.commit();
		}
	}

	@Override
	public void update(Reimbursement r){
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(r);
			transaction.commit();
		}
	}

	@Override
	public void delete(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(r);
			transaction.commit();
		}
	}
	
}
