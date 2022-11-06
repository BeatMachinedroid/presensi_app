package com.example.absensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.absensi.R;
import com.example.absensi.models.guru.DataGuru;

import java.util.List;

public class AdapterGuru extends RecyclerView.Adapter<AdapterGuru.holderGuru> {
    private Context context;
    private List<DataGuru> dataGuruList;

    public AdapterGuru(Context context,List<DataGuru> dataGuruList){
        this.context = context;
        this.dataGuruList = dataGuruList;
    }

    @NonNull
    @Override
    public AdapterGuru.holderGuru onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        holderGuru holder = new holderGuru(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGuru.holderGuru holder, int position) {
        DataGuru dataGuru = dataGuruList.get(position);
        holder.tid.setText(String.valueOf(dataGuru.getId()));
        holder.tnama.setText("Nama : "+dataGuru.getName());
        holder.nip.setText("Nip : "+dataGuru.getNip());
        holder.tjk.setText("Kelamin : "+dataGuru.getJk());
        holder.talm.setText("Email : "+dataGuru.getEmail());
    }

    @Override
    public int getItemCount() {
        return dataGuruList.size();
    }


    public static class holderGuru extends RecyclerView.ViewHolder{

        TextView tid, tnama, nip, tjk, talm;
        public holderGuru(@NonNull View itemView) {
            super(itemView);

            tid = itemView.findViewById(R.id.cdid);
            tnama = itemView.findViewById(R.id.cdnama);
            nip = itemView.findViewById(R.id.cdnis);
            tjk = itemView.findViewById(R.id.cdjk);
            talm = itemView.findViewById(R.id.cdalm);
        }
    }
}

