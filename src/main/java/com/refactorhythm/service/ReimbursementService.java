package com.refactorhythm.service;

import java.util.List;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorhythm.dao.ReimbursementDao;
import com.refactorhythm.model.Reimbursement;

public class ReimbursementService {
	private ReimbursementDao rd;
	private Gson gson = new Gson();
	private static final Logger LOGGER = Logger.getLogger(ReimbursementService.class);
	
	public ReimbursementService() {
		rd = new ReimbursementDao();
	}
	
	public void createReimbursement(String json) {
		try {
			Reimbursement r = gson.fromJson(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.insert(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for a new reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}
	
	public List<Reimbursement> fetchAllReimbursements() {
		return rd.getList();
	}
	
	public List<Reimbursement> getReimbursementsByUserID(int id) {
		return rd.getByUserId(id);
	}

	public void updateReimbursement(String json){
		try {
			Reimbursement r = gson.fromJson(json, Reimbursement.class);
			LOGGER.debug("JSON from the client was successfully parsed.");
			rd.update(r);
		} catch (Exception e) {
			LOGGER.error("Something occurred during JSON parsing for update reimbursement. Is the JSON malformed?");
			e.printStackTrace();
		}
	}

	public void deleteReimbursementById(int id){
		rd.delete(rd.getById(id));
	}

}
