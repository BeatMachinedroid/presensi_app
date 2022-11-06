package com.example.absensi.models.jadwal;

import com.google.gson.annotations.SerializedName;

public class DataJadwal {

	@SerializedName("hari")
	private String hari;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("jam")
	private String jam;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("id_guru")
	private int idGuru;

	@SerializedName("user")
	private User user;

	@SerializedName("mapel")
	private String mapel;

	public void setHari(String hari){
		this.hari = hari;
	}

	public String getHari(){
		return hari;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setJam(String jam){
		this.jam = jam;
	}

	public String getJam(){
		return jam;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIdGuru(int idGuru){
		this.idGuru = idGuru;
	}

	public int getIdGuru(){
		return idGuru;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setMapel(String mapel){
		this.mapel = mapel;
	}

	public String getMapel(){
		return mapel;
	}
}