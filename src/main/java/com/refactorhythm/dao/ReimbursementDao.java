package com.refactorhythm.dao;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.refactorhythm.util.SessionUtility;
import org.apache.log4j.Logger;

import com.refactorhythm.model.Reimbursement;
import org.hibernate.Session;
import org.hibernate.Transaction;


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
			return session.createQuery("from Reimbursement", Reimbursement.class)
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

	@Deprecated
	public void updateList(int[][] i, int resolver) {
//		try(Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
//			String aSql = "SELECT acceptarray(?, ?)";
//			String dSql = "SELECT denyarray(?, ?)";
//
//			//Convert both of our int arrays to an Integer object
//			Integer[] a = Arrays.stream(i[0]).boxed().toArray(Integer[]::new);
//			Integer[] d = Arrays.stream(i[1]).boxed().toArray(Integer[]::new);
//
//			//Convert both of our Integer arrays into something useful for SQL.
//			Array aArray = c.createArrayOf("INTEGER", a);
//			Array dArray = c.createArrayOf("INTEGER", d);
//
//			//Perform our SQL calls
//			CallableStatement cs = c.prepareCall(aSql);
//			cs.setArray(1, aArray);
//			cs.setInt(2, resolver);
//			cs.execute();
//			cs.closeOnCompletion();
//
//			cs = c.prepareCall(dSql);
//			cs.setArray(1, dArray);
//			cs.setInt(2, resolver);
//			cs.execute();
//			cs.closeOnCompletion();
//
//			//This section is just for the sake of logging.
//			int totalCount = 0;
//			for(int co = 0; co < a.length; co++) {
//				if (a[co] != -1) {
//					totalCount++;
//				}
//				if (d[co] != -1) {
//					totalCount++;
//				}
//			}
//			LOGGER.debug(totalCount + " reimbursement" + ((totalCount != 1) ? "s" : "") + " modified by user ID " + resolver + ".");
//		}
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
