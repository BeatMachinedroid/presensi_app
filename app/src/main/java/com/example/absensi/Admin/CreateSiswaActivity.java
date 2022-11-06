package com.example.absensi.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.models.siswa.DataItem;
import com.example.absensi.models.siswa.ResponseSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSiswaActivity extends AppCompatActivity {

    TextView enis, enisn, enama, ejk, eagama, ealamat, etgl,etempat, enohp, enamaortu;
    String nis, nisn, nama, jk, alamat, tgl,tempat, nohp, namaortu ,agama;
    Button create;
    ImageView back, home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_siswa);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateSiswaActivity.this, AdminSiswaActivity.class));
                finish();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateSiswaActivity.this, AdminHomeActivity.class));
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
        create = findViewById(R.id.tambah);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    String pesan = "Data tidak boleh kosong ..... !";
                    Toast.makeText(CreateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                } else {
                    DataItem Siswa = new DataItem();
                    Siswa.setNis(nis);
                    Siswa.setNisn(nisn);
                    Siswa.setNama(nama);
                    Siswa.setJk(jk);
                    Siswa.setAgama(agama);
                    Siswa.setAlamat(alamat);
                    Siswa.setTempatLahir(tempat);
                    Siswa.setTglLahir(tgl);
                    Siswa.setNoHpOrtu(nohp);
                    Siswa.setNamaOrtu(namaortu);
//            masukan request
                    Call<ResponseSiswa> siswaCall = ApiClient.getUserService().tambah(Siswa);
                    siswaCall.enqueue(new Callback<ResponseSiswa>() {
                        @Override
                        public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                            if (response.isSuccessful()){
                                String pesan = "berhasil menambah data";
                                Toast.makeText(CreateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateSiswaActivity.this, AdminSiswaActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                            String pesan = "Gagal menambah data check ulang data";
                            Toast.makeText(CreateSiswaActivity.this, pesan, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CreateSiswaActivity.this, CreateSiswaActivity.class));
                        }
                    });

                }
            }
        });
//

    }
}