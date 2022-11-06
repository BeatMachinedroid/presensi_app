package com.example.absensi.Admin;

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
import com.example.absensi.guru.ListGuruActivity;
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGuruActivity extends AppCompatActivity {

    TextView enip, enama, eno, ejk, eagama, eemail, epass, erole;
    String nip, nama, nohp, jk, agama, email, pass, roll;
    Button create;
    ImageView back, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_guru);
        enip = findViewById(R.id.nip);
        enama = findViewById(R.id.name);
        eno = findViewById(R.id.nohp);
        ejk = findViewById(R.id.jk);
        eagama = findViewById(R.id.agama);
        eemail = findViewById(R.id.email);
        erole = findViewById(R.id.role);
        epass = findViewById(R.id.pass);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateGuruActivity.this, ListAdminActivity.class));
                finish();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateGuruActivity.this, AdminHomeActivity.class));
                finish();
            }
        });

        create = findViewById(R.id.btn_add);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nip = enip.getText().toString();
                nama = enama.getText().toString();
                nohp = eno.getText().toString();
                jk = ejk.getText().toString();
                agama = eagama.getText().toString();
                email = eemail.getText().toString();
                roll = erole.getText().toString();
                pass = epass.getText().toString();
                if (nip.isEmpty() || nama.isEmpty() || nohp.isEmpty() || jk.isEmpty()
                        || agama.isEmpty() || email.isEmpty() || pass.isEmpty() || roll.isEmpty()){
                    String pesan = "All inputs required ..... !";
                    Toast.makeText(CreateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                }else {
                    DataGuru dataGuru = new DataGuru();
                    dataGuru.setNip(nip);
                    dataGuru.setName(nama);
                    dataGuru.setNoHp(nohp);
                    dataGuru.setJk(jk);
                    dataGuru.setAgama(agama);
                    dataGuru.setEmail(email);
                    dataGuru.setRole(roll);
                    dataGuru.setPassword(pass);

                    Call<ResponseGuru> tambahguru = ApiClient.getUserService().tambahguru(dataGuru);
                    tambahguru.enqueue(new Callback<ResponseGuru>() {
                        @Override
                        public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                            if (response.isSuccessful()) {
                                String pesan = "successfully...";
                                Toast.makeText(CreateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateGuruActivity.this, ListGuruActivity.class));
                            }else{
                                String pesan = "Gagal, check apakah data sudah benar?!";
                                Toast.makeText(CreateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseGuru> call, Throwable t) {
                            String pesan = "Gagal, check apakah data sudah benar?!";
                            Toast.makeText(CreateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CreateGuruActivity.this, CreateGuruActivity.class));
                        }
                    });
                }
            }
        });

    }
}