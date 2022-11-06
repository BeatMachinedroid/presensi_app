package com.example.absensi.models.absensi;

import com.google.gson.annotations.SerializedName;

public class Siswa{

	@SerializedName("jk")
	private String jk;

	@SerializedName("nisn")
	private String nisn;

	@SerializedName("agama")
	private String agama;

	@SerializedName("nama_ortu")
	private String namaOrtu;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("tempat_lahir")
	private String tempatLahir;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("nis")
	private String nis;

	@SerializedName("id")
	private int id;

	@SerializedName("no_hp_ortu")
	private String noHpOrtu;

	public void setJk(String jk){
		this.jk = jk;
	}

	public String getJk(){
		return jk;
	}

	public void setNisn(String nisn){
		this.nisn = nisn;
	}

	public String getNisn(){
		return nisn;
	}

	public void setAgama(String agama){
		this.agama = agama;
	}

	public String getAgama(){
		return agama;
	}

	public void setNamaOrtu(String namaOrtu){
		this.namaOrtu = namaOrtu;
	}

	public String getNamaOrtu(){
		return namaOrtu;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setTempatLahir(String tempatLahir){
		this.tempatLahir = tempatLahir;
	}

	public String getTempatLahir(){
		return tempatLahir;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setNis(String nis){
		this.nis = nis;
	}

	public String getNis(){
		return nis;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNoHpOrtu(String noHpOrtu){
		this.noHpOrtu = noHpOrtu;
	}

	public String getNoHpOrtu(){
		return noHpOrtu;
	}
}