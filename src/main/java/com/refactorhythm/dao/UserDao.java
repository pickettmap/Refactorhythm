package com.refactorhythm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.refactorhythm.util.SessionUtility;
import org.apache.log4j.Logger;

import com.refactorhythm.model.User;
import org.hibernate.Session;

/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao <User> {
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);
	
	@Override
	public List<User> getList() {
		return null;
	}

	@Override
	public User getById(int id) {
		User u = null;
		
		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
			String qSql = "SELECT * FROM ers_users WHERE ers_users_id = ?";
			PreparedStatement ps = c.prepareStatement(qSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				u = objectConstructor(rs);
			
			LOGGER.debug("Information about user ID " + id + " was retrieved from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about user ID " + id + " from the database failed.");
		}
		return u;
	}
	
	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {
		User u = null;
		
		try(Connection c = ConnectionUtil.getInstance().getConnection()) {
			String qSql = "SELECT * FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = c.prepareStatement(qSql);
			ps.setString(1, username.toLowerCase());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				//System.out.println("User object was created!");
				u = objectConstructor(rs);
			}
			
			LOGGER.debug("Information about username " + username + " was retrieved from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("An attempt to get info about username " + username + " from the database failed.");
		}
		return u;
	}

	@Override
	public void insert(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
}
