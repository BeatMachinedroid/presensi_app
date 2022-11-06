package com.example.absensi.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.adapter.AdapterAdminJadwal;
import com.example.absensi.adapter.AdapterAdminSiswa;
import com.example.absensi.adapter.AdapterJadwal;
import com.example.absensi.guru.JadwalActivity;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;
import com.example.absensi.models.siswa.ResponseSiswa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminJadwalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterJadwal;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataJadwal> dataJadwalList = new ArrayList<>();
    ImageView home, btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_jadwal);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminJadwalActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminJadwalActivity.this, CreateJadwalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.rjadwal);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        resultdata();
    }

    private void resultdata() {
        Call<ResponseJadwal> hasil = ApiClient.getUserService().adminjadwal();
        hasil.enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                boolean status = response.body().isStatus();
                String pesan = response.body().getMessage();
                Toast.makeText(AdminJadwalActivity.this, "status :"+status+"| pesan :"+pesan, Toast.LENGTH_SHORT).show();
                dataJadwalList = response.body().getData();
                adapterJadwal = new AdapterAdminJadwal(AdminJadwalActivity.this,dataJadwalList);
                recyclerView.setAdapter(adapterJadwal);
                adapterJadwal.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                Toast.makeText(AdminJadwalActivity.this, "failed"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
    }