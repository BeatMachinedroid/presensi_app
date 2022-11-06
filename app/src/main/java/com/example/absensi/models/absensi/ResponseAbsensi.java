package com.example.absensi.models.absensi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAbsensi {
	@SerializedName("data")
	private List<DataAbsensi> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataAbsensi> data){
		this.data = data;
	}

	public List<DataAbsensi> getData(){
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