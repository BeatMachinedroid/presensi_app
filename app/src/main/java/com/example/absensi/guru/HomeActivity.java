package com.example.absensi.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.Admin.UpdateSiswaActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.LoginActivity;
import com.example.absensi.QrActivity;
import com.example.absensi.R;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity{
//    object
    RelativeLayout buttonscan, absensi, guru, siswa;
    TextView viewjadwal,tgljadwal,harijadwal,datejam;
    TextView klsmapel, klsjadwal, namauser, jamm;
    String Harii;
    ImageView logout ;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tgljadwal = findViewById(R.id.tgljadwal);
        harijadwal = findViewById(R.id.harijadwal);
        datejam = findViewById(R.id.jam);
//        calender
        Date calender = Calendar.getInstance().getTime();
        String formatedate = DateFormat.getDateInstance(DateFormat.FULL).format(calender);
        String [] splitdate = formatedate.split(",");
        tgljadwal.setText(splitdate[1]);
        harijadwal.setText(splitdate[0]);
        String Hari = harijadwal.getText().toString();
//         time
        datejam.setText(getTime());





        //init obj
        namauser = findViewById(R.id.welcome);
        SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_INFO,Context.MODE_PRIVATE);
        namauser.setText("Wellcome |"+sp.getString(LoginActivity.Name, null));


        Call<ResponseJadwal> jadwalCall = ApiClient.getUserService().getjadwal(Hari);
        jadwalCall.enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                    List<DataJadwal> jadwals = response.body().getData();
                    for (DataJadwal dataJadwal : jadwals) {
                        String mapel, kelas, jam, hari;
                        mapel = "" + dataJadwal.getMapel();
                        kelas = "" + dataJadwal.getKelas();
                        jam = "" + dataJadwal.getJam();
                        hari = "" + dataJadwal.getHari();
                        Harii = harijadwal.getText().toString();

                            klsjadwal.setText("Kelas : " + kelas);
                            klsmapel.setText(mapel);
                            jamm.setText(jam);
                            if (response.isSuccessful()) {
                                if (hari.equals(Hari)) {
                                    Toast.makeText(HomeActivity.this, "success", Toast.LENGTH_SHORT).show();
                                }
                            }
                    }


            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                Toast.makeText(HomeActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//      Siswa
        siswa = findViewById(R.id.siswa);
        siswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SiswaActivity.class);
                startActivity(intent);
                finish();
            }
        });

//      jadwal
        viewjadwal = findViewById(R.id.viewjadwal);
        viewjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, JadwalActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        jadwal mengajar
        klsjadwal = findViewById(R.id.klsjadwal);
        klsmapel = findViewById(R.id.klsmapel);
        jamm = findViewById(R.id.jamm);
//


//      btn logout
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });

        //scan qr
        buttonscan = findViewById(R.id.buttonscan);
        buttonscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QrActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        view absensi
        absensi = findViewById(R.id.absensi);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AbsenSiswaActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        list view guru
        guru = findViewById(R.id.user);
        guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        }




//jadwalmengajarcall



//    jam
    private String getTime(){
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
    }
}
