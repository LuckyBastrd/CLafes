package controller;

import java.util.ArrayList;

import model.Transaction;
import model.TransactionModel;
import model.User;

public class TransactionController {
	
	public ArrayList<Transaction> getTrHistoryDataHandling(User user) {
		
		ArrayList<Transaction> TransactionHistoryList = new ArrayList<>();
		
		String userrole = user.getUserrole();
		Integer userid = user.getUserID();
		
		TransactionModel transactionModel = new TransactionModel();
		
		//Untuk memanggil semua transaction history
		if (userrole.equals("Admin")) {
			TransactionHistoryList = transactionModel.getAllTrHistoryData();
		}
		
		//Untuk memanggil transaction history customer berdasarkan user id nya
		else if (userrole.equals("Customer")) {
			TransactionHistoryList = transactionModel.getCustTrHistoryData(userid);
		}
		
		return TransactionHistoryList;
	}
}
