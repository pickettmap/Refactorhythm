package com.refactorhythm.service;

import java.util.List;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorhythm.dao.ReimbursementDao;
import com.refactorhythm.model.Reimbursement;

/**
 * This handles all Business Logic for Reimbursements.
 * @author Brandon Pinkerton
 * @version 1.0
 */
public class ReimbursementService {
	private ReimbursementDao rd;
	private Gson gson = new Gson();
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);

	/**
	 * The constructor.
	 */
	public ReimbursementService() {
		rd = new ReimbursementDao();
	}

	/**
	 * This creates a new Reimbursement object from a JSON String.
	 * @param json the JSON String to be parsed by gson.
	 * @throws Exception throws an exception if there is an issue with parsing the JSON.
	 */
	public void createReimbursement(String json) throws Exception{
		try {
			Reimbursement r = gson.fromJson(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This calls the DAO and returns a list of all Reimbursements.
	 * @return Returns a list of Reimbursement objects.
	 */
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}

	/**
	 * This calls the DAO and returns a list of all Reimbursements for a given user.
	 * @param id the author.
	 * @return Returns a list of Reimbursement objects.
	 */
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}

	/**
	 * This creates a Reimbursement object from a JSON String then calls the DAO to update the object if it exists.
	 * @param json the JSON String to be parsed.
	 * @throws Exception throws an exception if there is an issue with parsing the JSON.
	 */
	public void updateReimbursement(String json) throws Exception{
		try {
			Reimbursement r = gson.fromJson(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.update(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for update reimbursement. Is the JSON malformed?");
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * This retrieves an object from the DAO by id and if exists, removes the Reimbursement from the database.
	 * @param id the reimbursementId to be removed.
	 * @throws Exception throws an exception to the Controller if the id is not found.
	 */
	public void deleteReimbursementById(int id) throws Exception {
		Reimbursement r = rd.getById(id);
		if(r != null)
			rd.delete(r);
		else throw new Exception("id not found");
	}

}
