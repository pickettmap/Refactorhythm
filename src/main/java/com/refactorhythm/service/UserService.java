package com.refactorhythm.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import com.refactorhythm.dao.UserDao;
import com.refactorhythm.model.User;

/**
 * This handles all Business Logic for User.
 * @author Mikayla Pickett
 * @version 1.0
 */
public class UserService {
	private UserDao ud;
	private Gson gson = new Gson();
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	
	public UserService() {
		ud = new UserDao();
	}

	/**
	 * Calls the DAO to create a user object based on json input.
	 * Converts json input to User object and hashes the new user's password before saving it to the database.
	 * @param json the JSON string to be converted by gson to a user object
	 */
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

	/**
	 * Calls the DAO to update existing user object based on json input.
	 * Converts json input to user object and updates corresponding user with any new data.
	 * @param json the JSON string to be converted by gson to a user object
	 */
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

	/**
	 * Calls the DAO and returns a list of all users
	 * @return Returns a list of user objects
	 */
	public List<User> fetchAllUsers() {
		List <User> noPasswordUsers = new ArrayList();

		for (User u : ud.getList()) {
			u.setPassword("");
			noPasswordUsers.add(u);
		}

		return noPasswordUsers;
	}

	/**
	 * Calls the DAO and returns the corresponding user from the database based on user_id
	 * @param id the user_id of the user to be retrieved
	 * @return Returns the corresponding user from the database with matching user_id
	 */
	public User getUserById(int id) {
		User u = ud.getById(id);

		if(u != null) {
			u.setPassword("");
			return u;
		}

		return null;
	}

	/**
	 * Calls the DAO and returns the corresponding user from the database based on username
	 * @param username the username of the user to be retrieved
	 * @return Returns the corresponding user from the database with matching username
	 */
	public User getUserByUsername(String username) {
		User u = ud.getByUsername(username);
		if (u != null) {
			u.setPassword(""); //Remove the hashed password for security reasons.
			LOGGER.trace("Password info removed from username " + username + ".");
			return u;
		}
		return null;
	}

	/**
	 * Calls the DAO and deletes the corresponding user from the database based on user_id
	 * @param id the user_id of the user to be deleted
	 */
	public void deleteUser(int id) {
		User u = ud.getById(id);
		ud.delete(u);
	}

	/**
	 * Calls the DAO and returns the corresponding user from the database based on username and password
	 * This method salts and hashes the password and compares it to the hashed password
	 * stored in the database
	 * @param user the username of the user to be retrieved
	 * @param pass the password of the user to be retrieved
	 * @return Returns the corresponding user from the database with correct username and password
	 */
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
