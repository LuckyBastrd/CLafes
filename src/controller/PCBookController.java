package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import app.Main;
import database.UserDataSingleton;
import model.PCBook;
import model.PCBookModel;
import model.PCModel;
import model.User;
import view.BookPCPage;
import view.AssignUserPage;
import view.AssignUserPage.AssignUserPageVariables;
import view.BookPCPage.BookPCPageVariables;

public class PCBookController {

	PCBookModel pcBookModel = new PCBookModel();
	PCModel pcModel = new PCModel();

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

	public void assignUserHandling(AssignUserPageVariables assignUserPageVariables) {
		assignUserPageVariables.updateButton.setOnAction(e -> {
			String bookid = assignUserPageVariables.bookIDTF.getText();
			String pcid = assignUserPageVariables.pcIDTF.getText();
			LocalDate todaydate = LocalDate.now();
			
			if (!pcBookModel.isBookedExists(bookid)) {
				assignUserPageVariables.alert1.showAndWait();
				return;
			}

			if (pcBookModel.isPcBookToday(bookid, todaydate)) {
				assignUserPageVariables.alert2.showAndWait();
				return;
			}

			if (!pcModel.isPCIDExists(pcid)) {
				assignUserPageVariables.alert3.showAndWait();
				return;
			}
			
			pcBookModel.assignUser(pcid, bookid);
			
			Main.setScene(new AssignUserPage().startAssignUserPage());
		});
	}

}
