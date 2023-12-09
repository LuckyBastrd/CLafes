package model;

public class PC {
	private Integer pcID;
	private String pcCondition;
	
	public PC(Integer pcID, String pcCondition) {
		super();
		this.pcID = pcID;
		this.pcCondition = pcCondition;
	}

	public Integer getPcID() {
		return pcID;
	}

	public void setPcID(Integer pcID) {
		this.pcID = pcID;
	}

	public String getPcCondition() {
		return pcCondition;
	}

	public void setPcCondition(String pcCondition) {
		this.pcCondition = pcCondition;
	}
}
