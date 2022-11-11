package com.example.absensi.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.adapter.AdapterAdminAbsensi;
import com.example.absensi.adapter.AdapterAdminlistAbsensi;
import com.example.absensi.models.absensi.DataAbsensi;
import com.example.absensi.models.absensi.ResponseAbsensi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListpresensiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterAbsensi;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataAbsensi> dataAbsensiList = new ArrayList<>();
    ImageView home, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpresensi);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListpresensiActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListpresensiActivity.this, AdminHomeActivity.class));
                finish();
            }
        });


        recyclerView = findViewById(R.id.rdata11);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        resultabsen();
    }

    private void resultabsen() {
        Call<ResponseAbsensi> hasil = ApiClient.getUserService().getabsen("tidak masuk");
        hasil.enqueue(new Callback<ResponseAbsensi>() {
            @Override
            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                boolean status = response.body().isStatus();
                String pesan = response.body().getMessage();
                Toast.makeText(ListpresensiActivity.this, "success", Toast.LENGTH_SHORT).show();
                dataAbsensiList = response.body().getData();
                adapterAbsensi = new AdapterAdminlistAbsensi(ListpresensiActivity.this,dataAbsensiList);
                recyclerView.setAdapter(adapterAbsensi);
                adapterAbsensi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                Toast.makeText(ListpresensiActivity.this, "failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}