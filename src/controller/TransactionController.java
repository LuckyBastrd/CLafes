package controller;

import java.util.ArrayList;

import database.UserDataSingleton;
import model.PCBook;
import model.Transaction;
import model.TransactionModel;
import model.User;

public class TransactionController {
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	TransactionModel transactionModel = new TransactionModel();
	
	public ArrayList<Transaction> getTrHistoryDataHandling() {
		
		ArrayList<Transaction> TransactionHistoryList = new ArrayList<>();
		
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
	
	public void addTransactionHandling(ArrayList<PCBook> pcBooks) {
		transactionModel.addTransaction(pcBooks, currentUser.getUserID());
	}
}
