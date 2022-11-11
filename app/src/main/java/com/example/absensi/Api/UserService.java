package com.example.absensi.Api;

import com.example.absensi.models.Login.Login;
import com.example.absensi.models.Login.ResponseLogin;
import com.example.absensi.models.Register.RegisterReq;
import com.example.absensi.models.Register.RegisterRes;
import com.example.absensi.models.absensi.DataAbsensi;
import com.example.absensi.models.absensi.ResponseAbsensi;
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;
import com.example.absensi.models.siswa.DataItem;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    //login register user
    @POST("login")
    Call<ResponseLogin> login(
            @Body Login uslog
    );

    @POST("register")
    Call<RegisterRes> userRegister(
            @Body RegisterReq registerReq
    );

    //guru
    @POST("guru/siswa")
    Call<ResponseSiswa> datasiswa();

    @POST("guru/user")
    Call<ResponseGuru> dataGuru();

    @POST("guru/absensi")
    Call<ResponseAbsensi> dataAbsinsi();

    @POST("guru/jadwal")
    Call<ResponseJadwal> dataJadwal();

    //admin siswa
    @GET("admin/siswa")
    Call<ResponseSiswa> datasiswaadmin();

    @POST("admin/siswa/delete/{id}")
    Call<ResponseSiswa> deletesiswa(
            @Path("id") int idsiswa
    );

    @GET("admin/siswa/{nama}")
    Call<ResponseSiswa> getnama(
            @Path("nama") String nama
    );

    @POST("admin/siswa/update/{id}")
    Call<ResponseSiswa> updatesis(
            @Path("id") int idsiswa,
            @Body DataSiswa dataSiswa
            );

    @POST("admin/siswa")
    Call<ResponseSiswa> tambah(
            @Body DataItem siswa
            );
//    end siswa

    //admin guru
    @GET("admin/user")
    Call<ResponseGuru> dataguruadmin();

    @POST("admin/user/delete/{id}")
    Call<ResponseGuru> delguru(
            @Path("id") int idguru
    );

    @POST("admin/user/update/{id}")
    Call<ResponseGuru> upguru(
            @Path("id") int id,
            @Body DataGuru dataGuru
            );

    @GET("admin/user/{nama}")
    Call<ResponseGuru> getguru(
            @Path("nama") String nama
    );

    @POST("admin/user")
    Call<ResponseGuru> tambahguru(
            @Body DataGuru dataGuru
    );
    //end guru

    //admin jadwal
    @GET("admin/jadwal")
    Call<ResponseJadwal> adminjadwal();

    @POST("admin/jadwal")
    Call<ResponseJadwal> tambahjadwal(
            @Body DataJadwal dataJadwal
    );

    @GET("admin/jadwal/{id_guru}")
    Call<ResponseJadwal> upjadwals(
            @Path("id_guru") int id_guru
    );

    @POST("admin/jadwal/delete/{id}")
    Call<ResponseJadwal> deljadwal(
            @Path("id") int id
    );

    @POST("admin/jadwal/update/{id}")
    Call<ResponseJadwal> upjadwal(
            @Path("id") int id,
            @Body DataJadwal dataJadwal
    );



//    end adjadwal
    @GET("admin/absensi")
    Call<ResponseAbsensi> absensii();

    //admin absensi

    @GET("admin/absensi/{keterangan}")
    Call<ResponseAbsensi> getabsen(
            @Path("keterangan") String keterangan
    );

    @POST("admin/absensi/delete/{id}")
    Call<ResponseAbsensi> delabsen(
            @Path("id") int id
    );

    //admin qr code
    @PUT("admin/absensi/{id}")
    Call<ResponseAbsensi> qrabsen(
            @Path("id") int id,
            @Body DataAbsensi absensi
            );

//    get jadwal
    @GET("admin/jadwal/{hari}")
    Call<ResponseJadwal> getjadwal(
            @Path("hari") String hari
    );



}
