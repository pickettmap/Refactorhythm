package com.refactorhythm.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import com.refactorhythm.dao.UserDao;
import com.refactorhythm.model.User;

public class UserService {
	private UserDao ud;
	private Gson gson = new Gson();
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	
	public UserService() {
		ud = new UserDao();
	}

	public void createUser(String json) {
		try {
			User u = gson.fromJson(json, User.class);
			LOGGER.debug("JSON from the client was successfully parsed.");

			if(u != null) {
				String user = u.getUsername();
				String pass = u.getPassword();
				String full = user + pass + "salt";
				try {
					//Let MessageDigest know that we want to hash using MD5
					MessageDigest m = MessageDigest.getInstance("md5");
					//Convert our full string to a byte array.
					byte[] messageDigest = m.digest(full.getBytes());
					//Convert our byte array into a signum representation of its former self.
					BigInteger n = new BigInteger(1, messageDigest);

					//Convert the whole array into a hexadecimal string.
					String hash = n.toString(16);
					while(hash.length() < 32) {
						hash = "0" + hash;
					}

					u.setPassword(hash);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}

			ud.insert(u);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}

	public void updateUser(String json){
		try {
			User u = gson.fromJson(json, User.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			ud.update(u);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for update reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}

	public List<User> fetchAllUsers() {
		return ud.getList();
	}
	
	public User getUserById(int id) {
		return ud.getById(id);
	}
	
	public User getUserByUsername(String username) {
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(""); //Remove the hashed password for security reasons.
			LOGGER.trace("Password info removed from username " + username + ".");
			return u;
		}
		return null;
	}

	public void deleteUser(int id) {
		User u = ud.getById(id);
		ud.delete(u);
	}

	public User getUserByLogin(String user, String pass) {
		User u = ud.getByUsername(user);
		
		if(u != null) {
		String full = user + pass + "salt";
			try {
				//Let MessageDigest know that we want to hash using MD5
				MessageDigest m = MessageDigest.getInstance("md5");
				//Convert our full string to a byte array.
				byte[] messageDigest = m.digest(full.getBytes());
				//Convert our byte array into a signum representation of its former self.
				BigInteger n = new BigInteger(1, messageDigest);
				
				//Convert the whole array into a hexadecimal string.
				String hash = n.toString(16);
				while(hash.length() < 32) {
					hash = "0" + hash;
				}
				
				if(u.getPassword().equals(hash)) {
					//System.out.println("Hash matched!");
					return u;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
