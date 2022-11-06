package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.Api.ApiClient;
import com.example.absensi.models.Register.RegisterReq;
import com.example.absensi.models.Register.RegisterRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    TextView login;
    Button register;
    EditText ednip,edemail,ednohp,edjk,edagama,ednama,edpassword;
    String nip, email, no_hp, jk , agama, nama, password;
    ProgressDialog loding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ednip = findViewById(R.id.ed_nip);
        edemail = findViewById(R.id.ed_email);
        ednohp = findViewById(R.id.ed_nohp);
        edjk = findViewById(R.id.ed_jk);
        edagama = findViewById(R.id.ed_agama);
        ednama = findViewById(R.id.ed_nama);
        edpassword = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nip = ednip.getText().toString();
                email = edemail.getText().toString();
                no_hp = ednohp.getText().toString();
                jk = edjk.getText().toString();
                agama = edagama.getText().toString();
                nama = ednama.getText().toString();
                password = edpassword.getText().toString();
                if (nip.isEmpty() || email.isEmpty() || no_hp.isEmpty() || jk.isEmpty() || agama.isEmpty() || nama.isEmpty() || nama.isEmpty() || password.isEmpty()){
                    String pesan = "All inputs required ..... !";
                    Toast.makeText(RegisterActivity.this,pesan,Toast.LENGTH_LONG).show();
                }else if (email.isEmpty() || no_hp.isEmpty() || jk.isEmpty() || agama.isEmpty() || nama.isEmpty() || nama.isEmpty() || password.isEmpty()){
                    RegisterReq registerReq = new RegisterReq();
                    registerReq.setNip(ednip.getText().toString());
                    registerReq.setName(ednama.getText().toString());
                    registerReq.setNo_hp(ednohp.getText().toString());
                    registerReq.setJk(edjk.getText().toString());
                    registerReq.setAgama(edagama.getText().toString());
                    registerReq.setEmail(edemail.getText().toString());
                    registerReq.setPassword(edpassword.getText().toString());
                    registerUser(registerReq);
                }else {
                    RegisterReq registerReq = new RegisterReq();
                    registerReq.setNip(ednip.getText().toString());
                    registerReq.setName(ednama.getText().toString());
                    registerReq.setNo_hp(ednohp.getText().toString());
                    registerReq.setJk(edjk.getText().toString());
                    registerReq.setAgama(edagama.getText().toString());
                    registerReq.setEmail(edemail.getText().toString());
                    registerReq.setPassword(edpassword.getText().toString());
                    registerUser(registerReq);
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void registerUser(RegisterReq registerReq){

        Call<RegisterRes> registerResCall = ApiClient.getUserService().userRegister(registerReq);
        registerResCall.enqueue(new Callback<RegisterRes>() {
            @Override
            public void onResponse(Call<RegisterRes> call, Response<RegisterRes> response) {
                if (response.isSuccessful()){
                    String pesan = "Register Successfull";
                    Toast.makeText(RegisterActivity.this,pesan,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }else{
                    String pesan = "An error accurred please try again";
                    Toast.makeText(RegisterActivity.this,pesan,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterRes> call, Throwable t) {
                String pesan = "Register Gagal";
                Toast.makeText(RegisterActivity.this,pesan+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }





}