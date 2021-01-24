package com.ufpr.es.divresidapi.dto;

public class NewPassUptReqDTO {
	
	private Long userId;
	private String currentPass;
	private String newPass;
	
	public NewPassUptReqDTO(Long userId, String currentPass, String newPass) {
		super();
		this.userId = userId;
		this.currentPass = currentPass;
		this.newPass = newPass;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCurrentPass() {
		return currentPass;
	}
	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
	
	
}
