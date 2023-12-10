package controller;

import java.util.ArrayList;

import model.PC;
import model.Report;
import model.ReportModel;
import model.User;
import view.MakeReportPage.MakeReportPageVariables;

public class ReportController {

	public ArrayList<Report> getAllReportDataHandling() {
		
		ReportModel reportModel = new ReportModel();

		ArrayList<Report> allReportList = reportModel.getAllReportData();
		
		return allReportList;
	}

	public void makeReportHandling(MakeReportPageVariables makeReportPageVariables, User user) {
		String pcID = makeReportPageVariables.pcIDTF.getText();
		String reportNote = makeReportPageVariables.reportNoteTF.getText();
		
		ReportModel reportModel = new ReportModel();
		
		PCController pcController = new PCController();

		ArrayList<PC> pcList = pcController.getAllPCDataHandling();

		boolean pcExists = false;
		for (PC pc : pcList) {
			if (pc.getPcID().toString().equals(pcID)) {
				pcExists = true;
				break;
			}
		}

		if (pcExists) {

			if (!reportNote.isEmpty()) {
				reportModel.makeReport(user, pcID, reportNote);
			}
			
			else {
				makeReportPageVariables.alert2.showAndWait();
			}
		}

		else {
			makeReportPageVariables.alert1.showAndWait();
		}
	}
}
