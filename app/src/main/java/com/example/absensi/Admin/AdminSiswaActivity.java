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
import com.example.absensi.adapter.AdapterAdminSiswa;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adaptersiswa;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataSiswa> dataSiswaList = new ArrayList<>();
    ImageView home, delete, create, btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_siswa);

        //home
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSiswaActivity.this, AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //create data
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSiswaActivity.this, CreateSiswaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.rdata);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        resultdata();
    }

    public void resultdata(){
        Call<ResponseSiswa> hasil = ApiClient.getUserService().datasiswaadmin();
        hasil.enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                boolean status = response.body().isStatus();
                String pesan = response.body().getMessage();
                Toast.makeText(AdminSiswaActivity.this, "success", Toast.LENGTH_SHORT).show();
                dataSiswaList = response.body().getData();
                adaptersiswa =  new AdapterAdminSiswa(AdminSiswaActivity.this,dataSiswaList);
                recyclerView.setAdapter(adaptersiswa);
                adaptersiswa.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(AdminSiswaActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}