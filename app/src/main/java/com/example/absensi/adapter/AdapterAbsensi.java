package com.example.absensi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.absensi.R;
import com.example.absensi.models.absensi.DataAbsensi;
import com.example.absensi.models.absensi.Siswa;

import java.util.List;

public class AdapterAbsensi extends RecyclerView.Adapter<AdapterAbsensi.holderAbsensi>{
    private Context context;
    private List<DataAbsensi> dataAbsensiList;
    private List<Siswa> siswaList;

    public AdapterAbsensi(Context context, List<DataAbsensi> dataAbsensiList){
        this.context = context;
        this.dataAbsensiList = dataAbsensiList;
    }


    @NonNull
    @Override
    public AdapterAbsensi.holderAbsensi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item3,parent,false);
        holderAbsensi holder = new holderAbsensi(layout);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterAbsensi.holderAbsensi holder, int position) {
        DataAbsensi dataAbsensi = dataAbsensiList.get(position);
        holder.tid.setText(String.valueOf(dataAbsensi.getId()));
        holder.tname.setText("Nama : "+dataAbsensi.getSiswa().getNama());
        holder.tkelas.setText("Kelas : "+dataAbsensi.getKelas());
        holder.tmapel.setText("Mapel : "+dataAbsensi.getMapel());
        holder.tjk.setText("Alamat : "+dataAbsensi.getSiswa().getAlamat());
        holder.tketr.setText("keterangan : "+dataAbsensi.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return dataAbsensiList.size();
    }

    public class holderAbsensi extends RecyclerView.ViewHolder {
        TextView tid, tname, tkelas, tmapel, tjk, tketr;

        public holderAbsensi(@NonNull View itemView) {
            super(itemView);
            tid = itemView.findViewById(R.id.cdid);
            tname = itemView.findViewById(R.id.cdnama);
            tkelas = itemView.findViewById(R.id.cdnis);
            tmapel = itemView.findViewById(R.id.cdjk);
            tjk = itemView.findViewById(R.id.cddd);
            tketr = itemView.findViewById(R.id.cdket);
        }
    }
}
