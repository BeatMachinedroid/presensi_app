package com.example.absensi.guru;

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
import com.example.absensi.adapter.AdapterJadwal;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterJadwal;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataJadwal> dataJadwalList = new ArrayList<>();
    ImageView home, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JadwalActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JadwalActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });



        recyclerView = findViewById(R.id.rjadwal);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        resultjadwal();
    }

    private void resultjadwal() {
        Call<ResponseJadwal> hasil = ApiClient.getUserService().dataJadwal();
        hasil.enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                boolean status = response.body().isStatus();
                String pesan = response.body().getMessage();
                Toast.makeText(JadwalActivity.this, "status :"+status+"| pesan :"+pesan, Toast.LENGTH_SHORT).show();
                dataJadwalList = response.body().getData();
                adapterJadwal = new AdapterJadwal(JadwalActivity.this,dataJadwalList);
                recyclerView.setAdapter(adapterJadwal);
                adapterJadwal.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                Toast.makeText(JadwalActivity.this, "failed"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}