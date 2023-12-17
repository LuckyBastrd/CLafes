package controller;

import java.util.ArrayList;

import model.Transaction;
import model.TransactionModel;
import model.User;
import model.UserDataSingleton;

public class TransactionController {
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public ArrayList<Transaction> getTrHistoryDataHandling() {
		
		ArrayList<Transaction> TransactionHistoryList = new ArrayList<>();
		
		TransactionModel transactionModel = new TransactionModel();
		
		//Untuk memanggil semua transaction history
		if (currentUser.getUserrole().equals("Admin")) {
			TransactionHistoryList = transactionModel.getAllTrHistoryData();
		}
		
		//Untuk memanggil transaction history customer berdasarkan user id nya
		else if (currentUser.getUserrole().equals("Customer")) {
			TransactionHistoryList = transactionModel.getCustTrHistoryData(currentUser.getUserID());
		}
		
		return TransactionHistoryList;
	}
}
