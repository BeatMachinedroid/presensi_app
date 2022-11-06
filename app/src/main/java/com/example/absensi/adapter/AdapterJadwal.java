package com.example.absensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.models.jadwal.DataJadwal;

import java.util.List;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.holderJadwal>{
    private Context context;
    private List<DataJadwal> dataJadwalList;

    public AdapterJadwal(Context context, List<DataJadwal> dataJadwalList){
        this.context = context;
        this.dataJadwalList = dataJadwalList;
    }

    @NonNull
    @Override
    public AdapterJadwal.holderJadwal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item3,parent,false);
        holderJadwal holder = new holderJadwal(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJadwal.holderJadwal holder, int position) {
        DataJadwal dataJadwal = dataJadwalList.get(position);
        holder.tid.setText(String.valueOf(dataJadwal.getId()));
        holder.tguru.setText("Nama : "+dataJadwal.getUser().getName());
        holder.tmapel.setText("Mapel : "+dataJadwal.getMapel());
        holder.tkelas.setText("Kelas : "+dataJadwal.getKelas());
        holder.thari.setText("Hari : "+dataJadwal.getHari());
        holder.tjam.setText("Jam : "+dataJadwal.getJam());
    }

    @Override
    public int getItemCount() {
        return dataJadwalList.size();
    }

    public class holderJadwal extends RecyclerView.ViewHolder {
        TextView tid,  tmapel, tkelas, tguru, thari, tjam;
        public holderJadwal(@NonNull View itemView) {
            super(itemView);
            tid = itemView.findViewById(R.id.cdid);
            tguru = itemView.findViewById(R.id.cdnama);
            tmapel = itemView.findViewById(R.id.cdnis);
            tkelas = itemView.findViewById(R.id.cdjk);
            thari = itemView.findViewById(R.id.cddd);
            tjam = itemView.findViewById(R.id.cdket);
        }
    }
}