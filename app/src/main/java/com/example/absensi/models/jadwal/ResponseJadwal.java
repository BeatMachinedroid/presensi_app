package com.example.absensi.models.jadwal;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseJadwal {

	@SerializedName("data")
	private List<DataJadwal> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataJadwal> data){
		this.data = data;
	}

	public List<DataJadwal> getData(){
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