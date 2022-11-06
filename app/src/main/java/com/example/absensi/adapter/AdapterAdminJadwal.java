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

import com.example.absensi.Admin.AdminJadwalActivity;
import com.example.absensi.Admin.AdminSiswaActivity;
import com.example.absensi.Admin.UpdateJadwalActivity;
import com.example.absensi.Admin.UpdateSiswaActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.models.jadwal.DataJadwal;
import com.example.absensi.models.jadwal.ResponseJadwal;
import com.example.absensi.models.siswa.ResponseSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAdminJadwal extends RecyclerView.Adapter<AdapterAdminJadwal.holderJadwal>{
    private Context context;
    private List<DataJadwal> dataJadwalList;

    public AdapterAdminJadwal(Context context, List<DataJadwal> dataJadwalList){
        this.context = context;
        this.dataJadwalList = dataJadwalList;
    }

    @NonNull
    @Override
    public AdapterAdminJadwal.holderJadwal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_admin2,parent,false);
        holderJadwal holder = new holderJadwal(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdminJadwal.holderJadwal holder, int position) {
        DataJadwal dataJadwal = dataJadwalList.get(position);
//        holder.tid.setText(String.valueOf(dataJadwal.getId()));
        holder.tguru.setText("Nama : "+dataJadwal.getUser().getName());
        holder.tmapel.setText("Mapel : "+dataJadwal.getMapel());
        holder.tkelas.setText("Kelas : "+dataJadwal.getKelas());
        holder.thari.setText("Hari : "+dataJadwal.getHari());
        holder.tjam.setText("Jam : "+dataJadwal.getJam());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseJadwal> jadwalCall = ApiClient.getUserService().deljadwal(dataJadwal.getId());
                jadwalCall.enqueue(new Callback<ResponseJadwal>() {
                    @Override
                    public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                        if (response.isSuccessful()){
                            String pesan = response.body().getMessage();
                            Toast.makeText(context, "pesan :" +pesan, Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, AdminJadwalActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                        Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseJadwal> siswaCall = ApiClient.getUserService().upjadwals(dataJadwal.getIdGuru());
                siswaCall.enqueue(new Callback<ResponseJadwal>() {
                    @Override
                    public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                        boolean status = response.body().isStatus();
                        String pesan = response.body().getMessage();
                        Toast.makeText(context, "status : " + status +"| pesan :" +pesan, Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, UpdateJadwalActivity.class).putExtra("siswa",dataJadwal.getIdGuru()).putExtra("id",dataJadwal.getId()));
                    }

                    @Override
                    public void onFailure(Call<ResponseJadwal> call, Throwable t) {
                        Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataJadwalList.size();
    }

    public class holderJadwal extends RecyclerView.ViewHolder {
        TextView tid,  tmapel, tkelas, tguru, thari, tjam;
        ImageView edit, delete;
        public holderJadwal(@NonNull View itemView) {
            super(itemView);
            tid = itemView.findViewById(R.id.cdid);
            tguru = itemView.findViewById(R.id.cdnama);
            tmapel = itemView.findViewById(R.id.cdnis);
            tkelas = itemView.findViewById(R.id.cdjk);
            thari = itemView.findViewById(R.id.cddd);
            tjam = itemView.findViewById(R.id.cdket);
            edit = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}