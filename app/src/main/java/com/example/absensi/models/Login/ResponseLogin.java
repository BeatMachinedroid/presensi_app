package com.example.absensi.models.Login;

public class ResponseLogin {
	private Login data;
	private String message;
	private boolean status;

	public void setData(Login data){
		this.data = data;
	}

	public Login getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}
