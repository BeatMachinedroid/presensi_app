package com.example.absensi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.Admin.AdminAbsenActivity;
import com.example.absensi.Api.ApiClient;
import com.example.absensi.R;
import com.example.absensi.models.absensi.DataAbsensi;
import com.example.absensi.models.absensi.ResponseAbsensi;
import com.example.absensi.models.absensi.Siswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAdminlistAbsensi extends RecyclerView.Adapter<AdapterAdminlistAbsensi.holderAbsensi>{
    private Context context;
    private List<DataAbsensi> dataAbsensiList;
    private List<Siswa> siswaList;

    public AdapterAdminlistAbsensi(Context context, List<DataAbsensi> dataAbsensiList){
        this.context = context;
        this.dataAbsensiList = dataAbsensiList;
    }


    @NonNull
    @Override
    public AdapterAdminlistAbsensi.holderAbsensi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_admin3,parent,false);
        holderAbsensi holder = new holderAbsensi(layout);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterAdminlistAbsensi.holderAbsensi holder, int position) {
        DataAbsensi dataAbsensi = dataAbsensiList.get(position);
//        holder.tid.setText(String.valueOf(dataAbsensi.getId()));
        holder.tname.setText("Nama : "+dataAbsensi.getSiswa().getNama());
        holder.tkelas.setText("Kelas : "+dataAbsensi.getKelas());
        holder.tmapel.setText("Mapel : "+dataAbsensi.getMapel());
        holder.tjk.setText("Alamat : "+dataAbsensi.getSiswa().getAlamat());
        holder.tketr.setText("keterangan : "+dataAbsensi.getKeterangan());
        holder.wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesan = "assalamualaikum Bapak/Ibu/wali dari siswa yang bernama "+
                        dataAbsensi.getSiswa().getNama()+
                        ", kami menginformasikan bahwasanya anak dari bapak/ibu/wali murid tidak hadir pada hari ini. Terimakasih";
                String url = "https://api.whatsapp.com/send?phone=+62"+dataAbsensi.getSiswa().getNoHpOrtu()+"&text="+pesan;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseAbsensi> siswaCall = ApiClient.getUserService().delabsen(dataAbsensi.getId());
                siswaCall.enqueue(new Callback<ResponseAbsensi>() {
                    @Override
                    public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                        if (response.isSuccessful()){
                            String pesan = response.body().getMessage();
                            Toast.makeText(context, "pesan :" +pesan, Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, AdminAbsenActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                        Toast.makeText(context,"failed"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataAbsensiList.size();
    }

    public class holderAbsensi extends RecyclerView.ViewHolder {
        TextView tid, tname, tkelas, tmapel, tjk, tketr, tjam;
        ImageView update,delete, wa;

        public holderAbsensi(@NonNull View itemView) {
            super(itemView);
            tid = (TextView) itemView.findViewById(R.id.cdid);
            tname = (TextView) itemView.findViewById(R.id.cdnama);
            tkelas = (TextView)itemView.findViewById(R.id.cdnis);
            tmapel = (TextView)itemView.findViewById(R.id.cdjk);
            tjk = (TextView)itemView.findViewById(R.id.cddd);
            tketr = (TextView)itemView.findViewById(R.id.cdket);
            wa = (ImageView) itemView.findViewById(R.id.wa);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }
    }
}
