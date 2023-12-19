package controller;

import java.util.ArrayList;

import app.Main;
import database.UserDataSingleton;
import model.PCBook;
import model.PCBookModel;
import model.User;
import view.BookPCPage;
import view.BookPCPage.BookPCPageVariables;

public class PCBookController {
	
	PCBookModel pcBookModel = new PCBookModel();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public ArrayList<PCBook> getBookedPCDataHandling() {
		return pcBookModel.getAllPCBookData();
	}
	
	public void bookPCHandling(BookPCPageVariables bookPCPageVariables) {
		bookPCPageVariables.bookPCButton.setOnAction(e -> {
			String pcID = bookPCPageVariables.pcIDTF.getText();
			java.sql.Date bookDate = java.sql.Date.valueOf(bookPCPageVariables.bookDateDP.getValue());
			
			if (pcBookModel.isPcExists(pcID)) {
				
				if (!pcBookModel.isBooked(pcID, bookDate)) {
					pcBookModel.bookPC(pcID, currentUser.getUserID(), bookDate);
					
					Main.setScene(new BookPCPage().startBookPCPage());
				}
				
				else {
					bookPCPageVariables.alert2.showAndWait();
				}
				
			}
			
			else {
				bookPCPageVariables.alert1.showAndWait();
			}
			
		});
	}
			
}
