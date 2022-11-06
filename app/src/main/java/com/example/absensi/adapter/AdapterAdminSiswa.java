package com.example.absensi.adapter;

import android.annotation.SuppressLint;
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

import com.example.absensi.Admin.AdminSiswaActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.Admin.UpdateSiswaActivity;
import com.example.absensi.models.siswa.DataSiswa;
import com.example.absensi.models.siswa.ResponseSiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAdminSiswa extends RecyclerView.Adapter<AdapterAdminSiswa.holderData>{
    private Context context;
    private List<DataSiswa> dataSiswaList;
    ImageView delete, create;


    public AdapterAdminSiswa(Context context, List<DataSiswa> dataSiswaList) {
        this.context = context;
        this.dataSiswaList = dataSiswaList;
    }

    @NonNull
    @Override
    public holderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_admin, parent, false);
        holderData holder = new holderData(layout);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull holderData holder, int position) {
        DataSiswa dataSiswa = dataSiswaList.get(position);
        holder.tid.setText(String.valueOf(dataSiswa.getId()));
        holder.tnama.setText("Nama : "+dataSiswa.getNama());
        holder.tnis.setText("Nis : "+dataSiswa.getNis());
        holder.tnisn.setText("Nisn : "+dataSiswa.getNisn());
        holder.tjk.setText("jenis : "+dataSiswa.getJk());
        holder.talm.setText("Alamat : "+dataSiswa.getAlamat());
        holder.no.setText("No Ortu : "+dataSiswa.getNoHpOrtu());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Call<ResponseSiswa> siswaCall = ApiClient.getUserService().deletesiswa(dataSiswa.getId());
                        siswaCall.enqueue(new Callback<ResponseSiswa>() {
                            @Override
                            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                                if (response.isSuccessful()){
                                    String pesan = response.body().getMessage();
                                    Toast.makeText(context, "pesan :" +pesan, Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context, AdminSiswaActivity.class));
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                                Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseSiswa> siswaCall = ApiClient.getUserService().getnama(dataSiswa.getNama());
                siswaCall.enqueue(new Callback<ResponseSiswa>() {
                    @Override
                    public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                        boolean status = response.body().isStatus();
                        String pesan = response.body().getMessage();
                        Toast.makeText(context, "status : " + status +"| pesan :" +pesan, Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, UpdateSiswaActivity.class).putExtra("siswa",dataSiswa.getNama()).putExtra("id",dataSiswa.getId()));
                    }

                    @Override
                    public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                        Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSiswaList.size();
    }

    public class holderData extends RecyclerView.ViewHolder{
        TextView tid, tnama, tnis, tjk, talm, tnisn, no;
        ImageView update, delete;

        public holderData(@NonNull View itemView) {
            super(itemView);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
            tid = itemView.findViewById(R.id.cdid);
            tnama = itemView.findViewById(R.id.cdnama);
            tnis = itemView.findViewById(R.id.cdnis);
            tnisn = itemView.findViewById(R.id.cdnisn);
            tjk = itemView.findViewById(R.id.cdjk);
            talm = itemView.findViewById(R.id.cdalmt);
            no = itemView.findViewById(R.id.cdno);

        }
    }
}
