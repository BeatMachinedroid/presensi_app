package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.Admin.AdminHomeActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.guru.HomeActivity;
import com.example.absensi.models.Login.Login;
import com.example.absensi.models.Login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{


    String nip, email, pass;

    EditText ed_nip;
    EditText ed_email;
    EditText ed_password;
    TextView register, welcome;
    Button btn_login;
    ProgressDialog loding;
    public final static String SHARED_INFO = "log_user";
    public final static String Name = "name";
    public final static String guru = "id_guru";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences(SHARED_INFO, Context.MODE_PRIVATE);
        Loading();

    }

    private void Loading() {
        welcome =
        ed_nip = findViewById(R.id.ed_nip);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_pass) ;
        register = findViewById(R.id.register);
        btn_login =  findViewById(R.id.btn_login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nip = ed_nip.getText().toString();
                email = ed_email.getText().toString();
                pass = ed_password.getText().toString();

                if (email.isEmpty() || pass.isEmpty()){
                    String pesan = "Nip / Email / password required";
                    Toast.makeText(LoginActivity.this,pesan, Toast.LENGTH_LONG).show();

                }else{
//                    loding = ProgressDialog.show(LoginActivity.this,null,"Loading .....",true,true);
                    checkLogin();
                }
            }
        });
    }


    private void checkLogin() {
        Login login = new Login();
        login.setNip(ed_nip.getText().toString());
        login.setEmail(ed_email.getText().toString());
        login.setPassword(ed_password.getText().toString());
        Call<ResponseLogin> loginCall = ApiClient.getUserService().login(login);
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    Login log = response.body().getData();
                    SharedPreferences.Editor spEditor = sp.edit();
                    spEditor.putString(Name, log.getName());
                    spEditor.putString(guru, String.valueOf(log.getId()));
                    spEditor.apply();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
                                if (log.getRole().equals("admin")){
                                    startActivity(new Intent(LoginActivity.this, AdminHomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK ));
                                }else{
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK ));
                                }
//                            }
//                        },300);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}





