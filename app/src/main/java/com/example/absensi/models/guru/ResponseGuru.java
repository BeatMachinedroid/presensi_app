package com.example.absensi.models.guru;

import java.util.List;

public class ResponseGuru {
	private List<DataGuru> data;
	private String message;
	private boolean status;

	public void setData(List<DataGuru> data){
		this.data = data;
	}

	public List<DataGuru> getData(){
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