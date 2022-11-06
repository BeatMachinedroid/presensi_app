package com.example.absensi.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateSiswaActivity extends AppCompatActivity {
    TextView enis, enisn, enama, ejk, eagama, ealamat, etgl,etempat, enohp, enamaortu;
    Button btn_update;
    ImageView back, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_siswa);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSiswaActivity.this, AdminSiswaActivity.class));
                finish();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSiswaActivity.this, AdminHomeActivity.class));
                finish();
            }
        });

        enis = findViewById(R.id.nis);
        enisn = findViewById(R.id.nisn);
        enama = findViewById(R.id.nama);
        ejk = findViewById(R.id.jk);
        eagama = findViewById(R.id.agama);
        ealamat = findViewById(R.id.alamat);
        etempat = findViewById(R.id.tempat);
        etgl = findViewById(R.id.tgl);
        enohp = findViewById(R.id.nohp);
        enamaortu = findViewById(R.id.namaortu);


        //menerima id
        Intent intent = getIntent();
        String idSiswa = intent.getStringExtra("siswa");
        int id = intent.getIntExtra("id",0);

            //get data form id

            Call<ResponseSiswa> siswaCall = ApiClient.getUserService().getnama(idSiswa);
            siswaCall.enqueue(new Callback<ResponseSiswa>() {
                @Override
                public void onResponse(@NonNull Call<ResponseSiswa> call, @NonNull Response<ResponseSiswa> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        boolean status = response.body().isStatus();
                        String pesan = response.body().getMessage();
                        Toast.makeText(UpdateSiswaActivity.this, "status : " + status + "| pesan :" + pesan, Toast.LENGTH_SHORT).show();
                        List<DataSiswa> dataSiswa = response.body().getData();
                        for (DataSiswa siswa : dataSiswa){
                            String nis, nisn, nama, jk, agama, alamat, tgl,tempat, nohp, namaortu;
                            nis = "" + siswa.getNis();
                            nisn = "" + siswa.getNisn();
                            nama = "" + siswa.getNama();
                            jk = "" + siswa.getJk();
                            agama = "" + siswa.getAgama();
                            alamat = "" + siswa.getAlamat();
                            tgl = "" + siswa.getTglLahir();
                            tempat = ""+siswa.getTempatLahir();
                            nohp = "" + siswa.getNoHpOrtu();
                            namaortu = "" + siswa.getNamaOrtu();

                            enis.setText(nis);
                            enisn.setText(nisn);
                            enama.setText(nama);
                            ejk.setText(jk);
                            eagama.setText(agama);
                            ealamat.setText(alamat);
                            etempat.setText(tempat);
                            etgl.setText(tgl);
                            enohp.setText(nohp);
                            enamaortu.setText(namaortu);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseSiswa> call, @NonNull Throwable t) {
                    Toast.makeText(UpdateSiswaActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            btn_update = findViewById(R.id.btn_update);
            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //update
                    String nis, nisn, nama, jk, alamat, tgl,tempat, nohp, namaortu ,agama;
                    nis = enis.getText().toString();
                    nisn = enisn.getText().toString();
                    nama = enama.getText().toString();
                    jk = ejk.getText().toString();
                    alamat = ealamat.getText().toString();
                    agama = eagama.getText().toString();
                    tempat = etempat.getText().toString();
                    tgl = etgl.getText().toString();
                    nohp = enohp.getText().toString();
                    namaortu = enamaortu.getText().toString();
                    if (nis.isEmpty() || nisn.isEmpty() || nama.isEmpty() || jk.isEmpty() ||
                            alamat.isEmpty() || agama.isEmpty() || tempat.isEmpty()||tgl.isEmpty()
                            || nohp.isEmpty() || namaortu.isEmpty()) {
                        String pesan = "All inputs required ..... !";
                        Toast.makeText(UpdateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                    } else {
                        DataSiswa dataSiswa = new DataSiswa();
                        dataSiswa.setNis(nis);
                        dataSiswa.setNisn(nisn);
                        dataSiswa.setNama(nama);
                        dataSiswa.setJk(jk);
                        dataSiswa.setAgama(agama);
                        dataSiswa.setAlamat(alamat);
                        dataSiswa.setTempatLahir(tempat);
                        dataSiswa.setTglLahir(tgl);
                        dataSiswa.setNoHpOrtu(nohp);
                        dataSiswa.setNamaOrtu(namaortu);

                            Call<ResponseSiswa> update = ApiClient.getUserService().updatesis(id, dataSiswa);
                            update.enqueue(new Callback<ResponseSiswa>() {
                                @Override
                                public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                                    if (response.isSuccessful()) {
                                        String pesan = "Update Gagal";
                                        Toast.makeText(UpdateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(UpdateSiswaActivity.this, UpdateSiswaActivity.class));
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                                    String pesan = "Update Successfull";
                                    Toast.makeText(UpdateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(UpdateSiswaActivity.this, AdminSiswaActivity.class));
                                }
                            });


                    }

                }
            });
        }

    }