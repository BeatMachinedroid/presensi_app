package com.example.absensi.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.absensi.guru.AbsenSiswaActivity;
import com.example.absensi.guru.JadwalActivity;
import com.example.absensi.LoginActivity;
import com.example.absensi.QrActivity;
import com.example.absensi.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AdminHomeActivity extends AppCompatActivity {

    TextView tgljadwal,harijadwal,datejam, namauser, viewjadwal;
    ImageView logout , home;
    RelativeLayout buttonscan, absensi, guru, siswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        tgljadwal = findViewById(R.id.tgljadwal);
        harijadwal = findViewById(R.id.harijadwal);
        datejam = findViewById(R.id.jam);
        //calender
        Date calender = Calendar.getInstance().getTime();
        String formatedate = DateFormat.getDateInstance(DateFormat.FULL).format(calender);
        String [] splitdate = formatedate.split(",");
        tgljadwal.setText(splitdate[1]);
        harijadwal.setText(splitdate[0]);
//         time
        datejam.setText(getTime());

        //init obj
        namauser = findViewById(R.id.welcome);
        SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_INFO, Context.MODE_PRIVATE);
        namauser.setText("Admin | " +sp.getString(LoginActivity.Name, null));
        int id_guru = Integer.parseInt(sp.getString(LoginActivity.guru, null));

        //      Siswa
        siswa = findViewById(R.id.siswa);
        siswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminSiswaActivity.class);
                startActivity(intent);
                finish();
            }
        });



//      jadwal
        viewjadwal = findViewById(R.id.viewjadwal);
        viewjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminJadwalActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        home
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        btn logout
        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sp.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(AdminHomeActivity.this, LoginActivity.class));
            }
        });


        //scan qr
        buttonscan = findViewById(R.id.buttonscan);
        buttonscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, QrActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        view absensi
        absensi = findViewById(R.id.absensi);
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminAbsenActivity.class);
                startActivity(intent);
                finish();
            }
        });

//      list view guru
        guru = findViewById(R.id.user);
        guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, ListAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //    jam
    private String getTime(){
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
    }


}