package controller;

import java.util.ArrayList;

import model.PC;
import model.PCModel;

public class PCController {
	
	// INI BUAT AMBIL DATA UNTUK NAMPILIN VIEW ALL PC
	public ArrayList<PC> getAllPCDataHandling() {
		
		PCModel pcModel = new PCModel();
		
		ArrayList<PC> allPCList = pcModel.getAllPCData();

		return allPCList;
	}
}
