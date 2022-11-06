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
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateGuruActivity extends AppCompatActivity {
    TextView enip, enama, eno, ejk, eagama, eemail, epass, erole;
    Button btn_update;
    ImageView back, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_guru);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateGuruActivity.this, ListAdminActivity.class));
                finish();
            }
        });
        home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateGuruActivity.this, AdminHomeActivity.class));
                finish();
            }
        });

        enip = findViewById(R.id.nip);
        enama = findViewById(R.id.name);
        eno = findViewById(R.id.nohp);
        ejk = findViewById(R.id.jk);
        eagama = findViewById(R.id.agama);
        eemail = findViewById(R.id.email);
        erole = findViewById(R.id.role);
        epass = findViewById(R.id.pass);

        Intent intent = getIntent();
        String guru = intent.getStringExtra("siswa");
        int id = intent.getIntExtra("id",0);

        Call<ResponseGuru> guruCall = ApiClient.getUserService().getguru(guru);
        guruCall.enqueue(new Callback<ResponseGuru>() {
            @Override
            public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                boolean status = response.body().isStatus();
                String pesan = response.body().getMessage();
                Toast.makeText(UpdateGuruActivity.this, "status : " + status +"| pesan :" +pesan, Toast.LENGTH_SHORT).show();
                List<DataGuru> dataGurus = response.body().getData();
                for (DataGuru guru1 : dataGurus) {
                    String nip, nama, nohp, jk, agama, email, pass, roll;
                    nip = "" + guru1.getNip();
                    nama = "" + guru1.getName();
                    nohp = "" + guru1.getNoHp();
                    jk = "" + guru1.getJk();
                    agama = "" + guru1.getAgama();
                    email = "" + guru1.getEmail();
                    roll = "" + guru1.getRole();

                    enip.setText(nip);
                    enama.setText(nama);
                    eno.setText(nohp);
                    ejk.setText(jk);
                    eagama.setText(agama);
                    eemail.setText(email);
                    erole.setText(roll);
                }
            }

            @Override
            public void onFailure(Call<ResponseGuru> call, Throwable t) {
                Toast.makeText(UpdateGuruActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nip, nama, nohp, jk, agama, email, pass, roll;
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
                    Toast.makeText(UpdateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                }else{
                    DataGuru dataGuru = new DataGuru();
                    dataGuru.setNip(nip);
                    dataGuru.setName(nama);
                    dataGuru.setNoHp(nohp);
                    dataGuru.setJk(jk);
                    dataGuru.setAgama(agama);
                    dataGuru.setEmail(email);
                    dataGuru.setRole(roll);
                    dataGuru.setPassword(pass);

                    Call<ResponseGuru> update = ApiClient.getUserService().upguru(id, dataGuru);
                    update.enqueue(new Callback<ResponseGuru>() {
                        @Override
                        public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                            if (response.isSuccessful()) {
                                String pesan = "Update Gagal";
                                Toast.makeText(UpdateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(UpdateGuruActivity.this, UpdateSiswaActivity.class));
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseGuru> call, Throwable t) {
                            String pesan = "Update Successfull";
                            Toast.makeText(UpdateGuruActivity.this, pesan, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UpdateGuruActivity.this, AdminSiswaActivity.class));

                        }
                    });
                }
            }
        });

    }
}