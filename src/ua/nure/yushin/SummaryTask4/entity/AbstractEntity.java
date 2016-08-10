package ua.nure.yushin.SummaryTask4.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4245474056875314110L;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

}
