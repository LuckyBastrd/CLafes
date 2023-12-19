package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.PCController;
import controller.TransactionController;
import database.Connect;

public class PCBookModel {

	Connect con = Connect.getInstance();

	TransactionController transactionController = new TransactionController();

	public ArrayList<PCBook> getAllPCBookData() {
		String query = "SELECT * FROM pcbook";

		ArrayList<PCBook> allAllPCBookList = new ArrayList<>();

		ResultSet rs = con.selectData(query);

		try {
			while (rs.next()) {
				Integer bid = rs.getInt("BookID");
				Integer pid = rs.getInt("PC_ID");
				Integer uid = rs.getInt("UserID");
				Date bdate = rs.getDate("BookedDate");

				allAllPCBookList.add(new PCBook(bid, pid, uid, bdate));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allAllPCBookList;
	}

	public void bookPC(String pcID, Integer userID, Date bookDate) {
		String query = "INSERT INTO `pcbook`(`BookID`, `PC_ID`, `UserID`, `BookedDate`) VALUES (?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(query);

		try {

			ps.setString(1, null);
			ps.setString(2, pcID);
			ps.setInt(3, userID);
			ps.setDate(4, bookDate);

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isPcExists(String pcid) {

		PCController pcController = new PCController();

		ArrayList<PC> pcList = pcController.getAllPCDataHandling();

		boolean isPCExists = false;

		for (PC pc : pcList) {
			if (pc.getPcID().toString().equals(pcid)) {
				isPCExists = true;
				break;
			}
		}

		return isPCExists;
	}

	public boolean isBooked(String pcID, java.sql.Date bookDate) {
		String checkBookedDate = "SELECT * FROM PCBook WHERE PC_ID = ? AND BookedDate = ?";

		PreparedStatement psBookedDate = con.prepareStatement(checkBookedDate);

		try {
			psBookedDate.setString(1, pcID);
			psBookedDate.setDate(2, bookDate);

			ResultSet rsBooked = psBookedDate.executeQuery();

			boolean isBooked = false;
			while (rsBooked.next()) {
				String pcid = rsBooked.getString("PC_ID");
				java.sql.Date bookeddate = rsBooked.getDate("BookedDate");

				if (pcid.equals(pcID) && bookeddate.toLocalDate().equals(bookDate.toLocalDate())) {
					isBooked = true;
					break;
				}
			}

			return isBooked;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public void assignUser(String pcid, String bookid) {
		String query = "UPDATE `pcbook` SET `PC_ID` = ? WHERE BookID = ?";

		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setString(1, pcid);
			ps.setString(2, bookid);

			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public boolean isBookedExists(String bookid) {

		ArrayList<PCBook> bookedPCList = getAllPCBookData();

		boolean isBookedExists = false;

		for (PCBook pcbook : bookedPCList) {
			if (pcbook.getBookID().toString().equals(bookid)) {
				isBookedExists = true;
				break;
			}
		}

		return isBookedExists;
	}

	public boolean isPcBookToday(String bookid, LocalDate todaydate) {
		String checkPCBooked = "SELECT * FROM PCBook WHERE BookID = ?";

		PreparedStatement psPCBooked = con.prepareStatement(checkPCBooked);

		try {
			psPCBooked.setString(1, bookid);

			ResultSet rsBooked = psPCBooked.executeQuery();

			boolean isBooked = false;
			while (rsBooked.next()) {
				Date bdate = rsBooked.getDate("BookedDate");

				if (bdate.toLocalDate().equals(todaydate)) {
					isBooked = true;
					break;
				}
			}

			return isBooked;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public void cancelBook(String bookid) {
		String query = "DELETE FROM `pcbook` WHERE BookID = ?";

		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setString(1, bookid);

			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public boolean isBookOverYet(String bookid, LocalDate todayDate) {
		String checkBookDate = "SELECT * FROM PCBook WHERE BookID = ?";

		PreparedStatement psBookDate = con.prepareStatement(checkBookDate);

		try {
			psBookDate.setString(1, bookid);

			ResultSet rsDate = psBookDate.executeQuery();

			boolean isDateOver = false;
			while (rsDate.next()) {
				Date bdate = rsDate.getDate("BookedDate");

				if (bdate.toLocalDate().isBefore(todayDate)) {
					isDateOver = true;
					break;
				}
			}

			return isDateOver;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public void finishBook(Integer staffid, java.sql.Date bookDate) {
		ArrayList<PCBook> pcBookList = getBookedPCByDate(bookDate);
		
		deletePCBookList(bookDate);

		transactionController.addTransactionHandling(pcBookList);
	}

	public boolean checkBookDate(java.sql.Date bookDate, LocalDate todayDate) {
		String checkBookDate = "SELECT * FROM PCBook WHERE BookedDate = ?";

		PreparedStatement ps = con.prepareStatement(checkBookDate);

		try {
			ps.setDate(1, bookDate);

			ResultSet rs = ps.executeQuery();

			boolean isDateExists = false;
			while (rs.next()) {
				Date bdate = rs.getDate("BookedDate");

				if (bdate.toLocalDate().isBefore(todayDate)) {
					isDateExists = true;
					break;
				}
			}

			return isDateExists;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public ArrayList<PCBook> getBookedPCByDate(java.sql.Date bookDate) {
		String query = "SELECT * FROM PCBook WHERE BookedDate = ?";

		PreparedStatement ps = con.prepareStatement(query);

		ArrayList<PCBook> pcBookList = new ArrayList<>();

		try {
			ps.setDate(1, bookDate);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Integer bid = rs.getInt("BookID");
				Integer pid = rs.getInt("PC_ID");
				Integer uid = rs.getInt("UserID");
				Date bdate = rs.getDate("BookedDate");

				pcBookList.add(new PCBook(bid, pid, uid, bdate));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return pcBookList;
	}

	public void deletePCBookList(java.sql.Date bookDate) {
		String deleteQuery = "DELETE FROM PCBook WHERE BookedDate = ?";

		PreparedStatement ps = con.prepareStatement(deleteQuery);

		try {
			ps.setDate(1, bookDate);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
