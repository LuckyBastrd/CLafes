package controller;

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
import view.ViewPCBookedPage;
import view.ViewPCBookedPage.ViewPCBookedPageVariables;

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
	
	public void cancelBookHandling(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		viewPCBookedPageVariables.cancelButton.setOnAction(e -> {
			String bookid = viewPCBookedPageVariables.cancelBookIDTF.getText();
			LocalDate todayDate = LocalDate.now();
			
			if (!pcBookModel.isBookedExists(bookid)) {
				viewPCBookedPageVariables.alert1.showAndWait();
				return;
			}
			
			if (pcBookModel.isBookOverYet(bookid, todayDate)) {
				viewPCBookedPageVariables.alert2.showAndWait();
				return;
			}
			
			pcBookModel.cancelBook(bookid);
			
			Main.setScene(new ViewPCBookedPage().startViewPCBookedPage());
		});
	}
	
	public void finishBookHandling(ViewPCBookedPageVariables viewPCBookedPageVariables) {
		viewPCBookedPageVariables.finishButton.setOnAction(e -> {
			java.sql.Date bookDate = java.sql.Date.valueOf(viewPCBookedPageVariables.bookDateDP.getValue());
			LocalDate todayDate = LocalDate.now();
			
			if (!pcBookModel.checkBookDate(bookDate, todayDate)) {
				viewPCBookedPageVariables.alert4.showAndWait();
				return;
			}
			
			pcBookModel.finishBook(currentUser.getUserID(), bookDate);

			Main.setScene(new ViewPCBookedPage().startViewPCBookedPage());
		});
	}

}
