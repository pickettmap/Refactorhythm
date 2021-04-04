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


/**
 * This handles all Database Access for Reimbursements.
 * @author Brandon Pinkerton
 * @version 1.0
 */
public class ReimbursementDao implements GenericDao<Reimbursement> {
	private static final Logger LOGGER = Logger.getLogger(ReimbursementDao.class);

	/**
	 * This gets a list of all reimbursements in the Reimbursements table.
	 * @return Returns a List of Reimbursement objects.
	 */
	@Override
	public List<Reimbursement> getList() {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from Reimbursement", Reimbursement.class).list();
		}
	}

	/**
	 * This gets a single reimbursement by primary key, reimbursementId.
	 * @param id the reimbursementId.
	 * @return Returns a Reimbursement object.
	 */
	@Override
	public Reimbursement getById(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.get(Reimbursement.class, id);
		}
	}

	/**
	 * This gets a list of reimbursements for a specific user.
	 * @param id the author.
	 * @return Returns a List of Reimbursement objects.
	 */
	public List<Reimbursement> getByUserId(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			return session.createQuery("from Reimbursement where author= :author", Reimbursement.class)
			.setParameter("author", id).getResultList();
		}
	}

	/**
	 * This inserts a reimbursement in the Reimbursements table.
	 * @param r the Reimbursement object to be inserted.
	 */
	@Override
	public void insert(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(r);
			transaction.commit();
		}
	}

	/**
	 * This updates a reimbursement in the Reimbursements table.
	 * @param r the Reimbursement object to be updated.
	 */
	@Override
	public void update(Reimbursement r){
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.merge(r);
			transaction.commit();
		}
	}

	/**
	 * This deletes a reimbursement in Reimbursements table.
	 * @param r the Reimbursement object to be deleted.
	 */
	@Override
	public void delete(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(r);
			transaction.commit();
		}
	}
	
}
