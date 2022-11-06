package com.example.absensi.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateJadwalActivity extends AppCompatActivity {
    String enip, enama, enohp, ejk, eagama, eemail, epass, eroll;
    TextView nama, mapel, kelas, hari, jam, user;
    Button btn_update;
    ImageView back, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_jadwal);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateJadwalActivity.this, AdminJadwalActivity.class));
                finish();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateJadwalActivity.this, AdminHomeActivity.class));
                finish();
            }
        });

        nama = findViewById(R.id.nama);
        mapel = findViewById(R.id.mapel);
        kelas = findViewById(R.id.kelas);
        hari = findViewById(R.id.hari);
        jam = findViewById(R.id.jam);

        Intent intent = getIntent();
        String jhari = intent.getStringExtra("siswa");
        int id = intent.getIntExtra("id",0);

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emapel, ekelas, ehari, ejam, enama;
                enama = nama.getText().toString();
                emapel = mapel.getText().toString();
                ekelas = kelas.getText().toString();
                ehari = hari.getText().toString();
                ejam = jam.getText().toString();

                if ( ehari.isEmpty() || ekelas.isEmpty() || emapel.isEmpty() || ejam.isEmpty()){
                    String pesan = "All inputs required ..... !";
                    Toast.makeText(CreateJadwalActivity.this, pesan, Toast.LENGTH_LONG).show();
                }else{
                    Call<ResponseGuru> guruCall1 = ApiClient.getUserService().getguru(enama);
                    guruCall1.enqueue(new Callback<ResponseGuru>() {
                        @Override
                        public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                            List<DataGuru> dataGurus = response.body().getData();
                            for (DataGuru guru1 : dataGurus) {
                                int id_guru = guru1.getId();
                                DataJadwal dataJadwal = new DataJadwal();
                                dataJadwal.setMapel(emapel);
                                dataJadwal.setKelas(ekelas);
                                dataJadwal.setIdGuru(id_guru);
                                dataJadwal.setHari(ehari);
                                dataJadwal.setJam(ejam);
                                Call<ResponseJadwal> update = ApiClient.getUserService().upjadwal(id, dataJadwal);
                                update.enqueue(new Callback<ResponseJadwal>() {
                                    @Override
                                    public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                                        if (response.isSuccessful()) {
                                            String pesan = "Update Gagal";
                                            Toast.makeText(CreateJadwalActivity.this, pesan, Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(CreateJadwalActivity.this, CreateJadwalActivity.class));
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                                        String pesan = "Update Successfull";
                                        Toast.makeText(CreateJadwalActivity.this, pesan, Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(CreateJadwalActivity.this, AdminJadwalActivity.class));

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseGuru> call, Throwable t) {

                        }
                    });
                            }
                        }


                    });


    }

}