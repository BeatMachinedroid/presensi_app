package com.example.absensi.models.absensi;

import com.google.gson.annotations.SerializedName;

public class DataAbsensi {
	@SerializedName("siswa")
	private Siswa siswa;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("mapel")
	private String mapel;

	@SerializedName("id_siswa")
	private int idSiswa;

	public void setSiswa(Siswa siswa){
		this.siswa = siswa;
	}

	public Siswa getSiswa(){ return siswa; }

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){ return keterangan; }

	public void setUpdatedAt(String updatedAt){ this.updatedAt = updatedAt; }

	public String getUpdatedAt(){ return updatedAt; }

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
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

	public int getId(){ return id; }

	public void setMapel(String mapel){
		this.mapel = mapel;
	}

	public String getMapel(){
		return mapel;
	}

	public void setIdSiswa(int idSiswa){
		this.idSiswa = idSiswa;
	}

	public int getIdSiswa(){
		return idSiswa;
	}

}
