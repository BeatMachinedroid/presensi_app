package com.example.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.Admin.ListAdminActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.Admin.UpdateGuruActivity;
import com.example.absensi.models.guru.DataGuru;
import com.example.absensi.models.guru.ResponseGuru;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAdminGuru extends RecyclerView.Adapter<AdapterAdminGuru.holderGuru> {
    private Context context;
    private List<DataGuru> dataGuruList;

    public AdapterAdminGuru(Context context, List<DataGuru> dataGuruList){
        this.context = context;
        this.dataGuruList = dataGuruList;
    }

    @NonNull
    @Override
    public AdapterAdminGuru.holderGuru onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_admin1,parent,false);
        holderGuru holder = new holderGuru(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdminGuru.holderGuru holder, int position) {
        DataGuru dataGuru = dataGuruList.get(position);
        int idguru = dataGuru.getId();
//        holder.tid.setText(String.valueOf(dataGuru.getId()));
        holder.tnama.setText("Nama : "+dataGuru.getName());
        holder.nip.setText("Nip : "+dataGuru.getNip());
        holder.tjk.setText("Kelamin : "+dataGuru.getJk());
        holder.talm.setText("Email : "+dataGuru.getEmail());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseGuru> guru = ApiClient.getUserService().getguru(String.valueOf(idguru));
                guru.enqueue(new Callback<ResponseGuru>() {
                    @Override
                    public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                        boolean status = response.body().isStatus();
                        String pesan = response.body().getMessage();
                        Toast.makeText(context, "status : " + status +"| pesan :" +pesan, Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, UpdateGuruActivity.class).putExtra("siswa",dataGuru.getName()).putExtra("id",dataGuru.getId()));
                    }

                    @Override
                    public void onFailure(Call<ResponseGuru> call, Throwable t) {

                    }
                });

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseGuru> guruCall = ApiClient.getUserService().delguru(dataGuru.getId());
                guruCall.enqueue(new Callback<ResponseGuru>() {
                    @Override
                    public void onResponse(Call<ResponseGuru> call, Response<ResponseGuru> response) {
                        if (response.isSuccessful()){
                            String pesan = response.body().getMessage();
                            Toast.makeText(context, "pesan :" +pesan, Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, ListAdminActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGuru> call, Throwable t) {
                        Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataGuruList.size();
    }


    public static class holderGuru extends RecyclerView.ViewHolder{

        TextView tid, tnama, nip, tjk, talm;
        ImageView delete, update;
        public holderGuru(@NonNull View itemView) {
            super(itemView);

            tid = itemView.findViewById(R.id.cdid);
            tnama = itemView.findViewById(R.id.cdnama);
            nip = itemView.findViewById(R.id.cdnis);
            tjk = itemView.findViewById(R.id.cdjk);
            talm = itemView.findViewById(R.id.cdalm);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);
        }
    }
}

