package com.example.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.models.siswa.DataSiswa;

import java.util.List;

public class Adapterdata extends RecyclerView.Adapter<Adapterdata.holderData>{
    private Context context;
    private List<DataSiswa> dataSiswaList;



    public Adapterdata(Context context, List<DataSiswa> dataSiswaList) {
        this.context = context;
        this.dataSiswaList = dataSiswaList;
    }

    @NonNull
    @Override
    public holderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item4, parent, false);
        holderData holder = new holderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holderData holder, int position) {
        DataSiswa dataSiswa = dataSiswaList.get(position);
        holder.tid.setText(String.valueOf(dataSiswa.getId()));
        holder.tnama.setText("Nama : "+dataSiswa.getNama());
        holder.tnis.setText("Nis    : "+dataSiswa.getNis());
        holder.tnisn.setText("Nisn   : "+dataSiswa.getNisn());
        holder.tjk.setText("jenis  : "+dataSiswa.getJk());
        holder.lahir.setText("TTL    : "+dataSiswa.getTempatLahir()+" , "+dataSiswa.getTglLahir());
        holder.talm.setText("Alamat : "+dataSiswa.getAlamat());
        holder.no.setText("No Ortu: "+dataSiswa.getNoHpOrtu());
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = "https://api.whatsapp.com/send?phone=+62"+dataSiswa.getNoHpOrtu();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataSiswaList.size();
    }

    public class holderData extends RecyclerView.ViewHolder{
        TextView tid, tnama, tnis, tjk, talm, tnisn, no, lahir;


        public holderData(@NonNull View itemView) {
            super(itemView);

            tid = itemView.findViewById(R.id.cdid);
            tnama = itemView.findViewById(R.id.cdnama);
            tnis = itemView.findViewById(R.id.cdnis);
            tnisn = itemView.findViewById(R.id.cdnisn);
            tjk = itemView.findViewById(R.id.cdjk);
            lahir = itemView.findViewById(R.id.cdtgl);
            talm = itemView.findViewById(R.id.cdalmt);
            no = itemView.findViewById(R.id.cdno);
        }
    }
}
