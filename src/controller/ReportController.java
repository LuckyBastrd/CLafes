package controller;

import java.util.ArrayList;

import app.Main;
import model.Report;
import model.ReportModel;
import model.User;
import model.UserDataSingleton;
import view.MakeReportPage;
import view.MakeReportPage.MakeReportPageVariables;

public class ReportController {
	
	ReportModel reportModel = new ReportModel();
	
	User currentUser = UserDataSingleton.getInstance().getCurrentUser();
	
	public ArrayList<Report> getAllReportDataHandling() {
		ArrayList<Report> allReportList = reportModel.getAllReportData();
		
		return allReportList;
	}

	public void makeReportHandling(MakeReportPageVariables makeReportPageVariables) {
		makeReportPageVariables.reportButton.setOnAction(e -> {
			
			String pcID = makeReportPageVariables.pcIDTF.getText();
			String reportNote = makeReportPageVariables.reportNoteTF.getText();

			if (reportModel.isPcExists(pcID)) {

				if (!reportNote.isEmpty()) {
					reportModel.makeReport(currentUser.getUserID(), pcID, reportNote);
					
					Main.setScene(new MakeReportPage().startMakeReportPage());
				}
				
				else {
					makeReportPageVariables.alert2.showAndWait();
				}
			}

			else {
				makeReportPageVariables.alert1.showAndWait();
			}
			
		});
	}
}
