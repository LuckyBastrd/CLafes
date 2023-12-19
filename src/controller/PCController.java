package controller;

import java.util.ArrayList;

import app.Main;
import model.PC;
import model.PCModel;
import view.PCManagementPage;
import view.PCManagementPage.PCManagementPageVariables;

public class PCController {
	
	PCModel pcModel = new PCModel();
	
	public ArrayList<PC> getAllPCDataHandling() {
		
		PCModel pcModel = new PCModel();
		
		ArrayList<PC> allPCList = pcModel.getAllPCData();

		return allPCList;
	}
	
	public void addPCHandling(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.addPCButton.setOnAction(e -> {
			
			String pcid = pcManagementPageVariables.addPCIDTF.getText();
			String pccondition = "Usable";
			
			if (!pcid.isEmpty()) {
				if (!pcModel.isPCIDExists(pcid)) {
					pcModel.addPC(pcid, pccondition);
					
					Main.setScene(new PCManagementPage().startPCManagementPageVariables());
				}
				
				else {
					pcManagementPageVariables.alert2.showAndWait();
				}
			}
			
			else {
				pcManagementPageVariables.alert1.showAndWait();
			}
		});
	}
	
	public void updatePCHandling(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.updatePCButton.setOnAction(e -> {
			
			String pcid = pcManagementPageVariables.updatePCIDTF.getText();
			String pccondition = pcManagementPageVariables.updatePCConditionTF.getText();
			
			if (pcid.isEmpty()) {
				pcManagementPageVariables.alert1.showAndWait();
				return;
			}
			
			if (!pcModel.isPCIDExists(pcid)) {
				pcManagementPageVariables.alert3.showAndWait();
				return;
			}
			
			if (!pccondition.equals("Usable") && !pccondition.equals("Maintenance") && !pccondition.equals("Broken")) {
				pcManagementPageVariables.alert4.showAndWait();
				return;
			}
			
			pcModel.updatePC(pcid, pccondition);
			
			Main.setScene(new PCManagementPage().startPCManagementPageVariables());
		});
	}
	
	public void deletePCHandling(PCManagementPageVariables pcManagementPageVariables) {
		pcManagementPageVariables.deletePCButton.setOnAction(e -> {
			
			String pcid = pcManagementPageVariables.deletePCIDTF.getText();
			
			if (pcid.isEmpty()) {
				pcManagementPageVariables.alert1.showAndWait();
				return;
			}
			
			if (pcModel.isPCIDBooked(pcid)) {
				pcManagementPageVariables.alert5.showAndWait();
				return;
			}
			
			pcModel.deletePC(pcid);
			
			Main.setScene(new PCManagementPage().startPCManagementPageVariables());
		});
	}
}
