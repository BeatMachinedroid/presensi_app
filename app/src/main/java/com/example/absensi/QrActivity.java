package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.absensi.Admin.UpdateSiswaActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.guru.HomeActivity;
import com.example.absensi.models.absensi.DataAbsensi;
import com.example.absensi.models.absensi.DataItem;
import com.example.absensi.models.absensi.ResponseAbsensi;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;
import com.google.zxing.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QrActivity extends AppCompatActivity{

    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private CodeScanner mCodeScanner;
    TextView hasil , nama;
    EditText ekelas, emapel;
    Button btn_absen;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(QrActivity.this, new String[] {Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
        }
        back = findViewById(R.id.backqr);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QrActivity.this, HomeActivity.class));
            }
        });
        nama = findViewById(R.id.nama);
        hasil = findViewById(R.id.hasil);
        ekelas = findViewById(R.id.ekelas);
        emapel = findViewById(R.id.emapel);
        btn_absen = findViewById(R.id.btn_absen);
        codescanner();
    }


    public void codescanner() {
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        hasil.setText(result.getText());
                        Call<ResponseSiswa> siswaCall = ApiClient.getUserService().getnama(result.getText());
                        siswaCall.enqueue(new Callback<ResponseSiswa>() {
                            @Override
                            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                                if (response.isSuccessful()){
                                    List<DataSiswa> dataSiswa = response.body().getData();
                                    for (DataSiswa siswa : dataSiswa){
                                        String nis , name;
                                        int idsiswa = siswa.getId();
                                        nis = "Nis : "+siswa.getNis();
                                        name = "Nama : "+siswa.getNama();
                                        hasil.setText(nis);
                                        nama.setText(name);
                                            btn_absen.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    DataAbsensi absensi = new DataAbsensi();
                                                    absensi.setIdSiswa(idsiswa);
                                                    absensi.setKelas(ekelas.getText().toString());
                                                    absensi.setMapel(emapel.getText().toString());
                                                    absensi.setKeterangan("masuk");
                                                    Call<ResponseAbsensi> absensiCall = ApiClient.getUserService().absen(absensi);
                                                    absensiCall.enqueue(new Callback<ResponseAbsensi>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                                                            if (response.isSuccessful()){
                                                                boolean status = response.body().isStatus();
                                                                Toast.makeText(QrActivity.this, "status : " + status, Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                                                            Toast.makeText(QrActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }


                            @Override
                            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                                Toast.makeText(QrActivity.this,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }



    @Override
    protected void onResume(){
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause(){
        mCodeScanner.releaseResources();
        super.onPause();
    }






}