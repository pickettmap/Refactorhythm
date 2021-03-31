package com.refactorhythm.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.refactorhythm.model.User;
import com.refactorhythm.util.SessionUtility;
import org.apache.log4j.Logger;

import com.refactorhythm.model.Reimbursement;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class ReimbursementDao implements GenericDao<Reimbursement> {
	private static final Logger LOGGER = Logger.getLogger(ReimbursementDao.class);
<<<<<<< HEAD
=======

>>>>>>> 3d26138caf8bf7bf8092a1c8a712b98028bc06b6

	@Override
	public List<Reimbursement> getList() {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery query = builder.createQuery(Reimbursement.class);
			query.from(Reimbursement.class);

			return session.createQuery(query).getResultList();
		}
	}

	@Override
	public Reimbursement getById(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
			return (Reimbursement) session.get(Reimbursement.class, id);
		}
	}
	
	@Override
	public List<Reimbursement> getByUserId(int id) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
			Query query = session.createQuery("from reimbursement");
			query.setParameter("author",id);
			return query.getResultList();
		}
	}
	
	public Reimbursement getByUsername(String username) {
		//Empty. Reason - No use.
		return null;
	}

	@Override
	public void insert(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
			Transaction transaction = session.beginTransaction();
			session.persist(r);
			transaction.commit();
		}
	}
	
	public void updateList(int[][] i, int resolver) {
//		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
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
//		} catch (SQLException e) {
//			LOGGER.error("An attempt to accept/deny reimbursements by user ID " + resolver + " from the database failed.");
//			e.printStackTrace();
//		}
	}
	
	@Override
	public void delete(Reimbursement r) {
		try (Session session = SessionUtility.INSTANCE.getSessionFactoryInstance().getCurrentSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(r);
			transaction.commit();
		}
	}
	
}
