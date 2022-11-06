package com.example.absensi.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        Gson gson = new GsonBuilder().serializeNulls().create();
            retrofit = new Retrofit.Builder()
//                   ubah ip sesuai dengan laptop
                    .baseUrl("https://yohanesseptian.000webhostapp.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        return retrofit;
    }

    public  static UserService getUserService(){
        UserService userService = getClient().create(UserService.class);
        return userService;
    }


}
