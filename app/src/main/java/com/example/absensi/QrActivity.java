package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QrActivity extends AppCompatActivity{

    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private CodeScanner mCodeScanner;
    TextView hasil , nama, kelas, jam;
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
        kelas = findViewById(R.id.kelas);
        jam = findViewById(R.id.jam);
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
                                if (response.isSuccessful()) {
                                    List<DataSiswa> dataSiswa = response.body().getData();
                                    SharedPreferences sp = getSharedPreferences(LoginActivity.SHARED_INFO, Context.MODE_PRIVATE);
                                    String getguru = sp.getString(LoginActivity.guru, null);
                                    for (DataSiswa siswa : dataSiswa) {
                                        String nis, name, kelass ,mapell;
                                        int idsiswa = siswa.getId();
                                        nis = "Nis : " + siswa.getNis();
                                        name = "Nama : " + siswa.getNama();
                                        kelass = "Kelas : VIIA";
                                        mapell =  "Bahasa indonesia";
                                        hasil.setText(nis);
                                        nama.setText(name);
                                        kelas.setText(kelass);
                                        jam.setText(gettime());

                                        Call<ResponseAbsensi> absensiCall = ApiClient.getUserService().absensii();
                                        absensiCall.enqueue(new Callback<ResponseAbsensi>() {
                                            @Override
                                            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                                                List<DataAbsensi> dataAbsensis = response.body().getData();
                                                for (DataAbsensi absensi : dataAbsensis){
                                                    int idsiswa1 = absensi.getIdSiswa();
                                                    if (idsiswa == idsiswa1){
                                                        int id = absensi.getId();
                                                        DataAbsensi absensis = new DataAbsensi();
                                                        absensis.setIdSiswa(idsiswa);
                                                        absensis.setKelas(kelass);
                                                        absensis.setMapel(mapell);
                                                        absensis.setKeterangan("masuk");
                                                        Call<ResponseAbsensi> responseAbsensiCall = ApiClient.getUserService().qrabsen(id,absensis);
                                                        responseAbsensiCall.enqueue(new Callback<ResponseAbsensi>() {
                                                            @Override
                                                            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                                                                if (response.isSuccessful()){
                                                                    Toast.makeText(QrActivity.this,"success", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(QrActivity.this,"gagal, check lagi", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                                                                Toast.makeText(QrActivity.this,"success", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {

                                            }
                                        });
//

                                    }
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                                Toast.makeText(QrActivity.this,"Nama tidak sesuai", Toast.LENGTH_SHORT).show();
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

    private String gettime(){
        return new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
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