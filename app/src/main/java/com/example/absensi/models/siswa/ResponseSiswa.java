package com.example.absensi.models.siswa;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSiswa {
	private List<DataSiswa> data;
	private String message;
	private boolean status;

	@SerializedName("data")
	public void setData(List<DataSiswa> data){
		this.data = data;
	}

	public List<DataSiswa> getData(){
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