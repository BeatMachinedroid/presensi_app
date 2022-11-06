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
import com.example.absensi.adapter.AdapterAdminGuru;
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdminActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterGuruAdmin;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataGuru> dataGuruList = new ArrayList<>();
    ImageView home, add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_admin);

        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListAdminActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        add = findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListAdminActivity.this, CreateGuruActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.rguru);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        resultguru();
    }

    private void resultguru() {
        Call<ResponseGuru> hasil = ApiClient.getUserService().dataGuru();
        hasil.enqueue(new Callback<ResponseGuru>() {
            @Override
            public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                String pesan = response.body().getMessage();
                Toast.makeText(ListAdminActivity.this, "status :"+ response.body().isStatus()+"| pesan :" +pesan, Toast.LENGTH_SHORT).show();
                dataGuruList = response.body().getData();
                adapterGuruAdmin = new AdapterAdminGuru(ListAdminActivity.this, dataGuruList);
                recyclerView.setAdapter(adapterGuruAdmin);
                adapterGuruAdmin.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseGuru> call, Throwable t) {
                Toast.makeText(ListAdminActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}