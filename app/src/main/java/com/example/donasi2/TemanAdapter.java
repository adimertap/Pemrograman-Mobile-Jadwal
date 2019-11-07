package com.example.donasi2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.circularreveal.CircularRevealFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.Viewholder> {
    private List<Teman> daftarTeman =new ArrayList<>();

    public TemanAdapter(List<Teman> daftarTeman) {
        this.daftarTeman = daftarTeman;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_item_daftar_teman, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        viewholder.bind(daftarTeman.get(i));
        // ngambil di urutan di masukan ke tampilan dari viewholder dapat melakukan onbind
    }

    @Override
    public int getItemCount() {
        return daftarTeman.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        private TextView tvNama, tvTelp;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvTelp = itemView.findViewById(R.id.tv_telp);
        }

        public void bind(final Teman teman){
            tvNama.setText(teman.getName());
            tvTelp.setText(teman.getTelp());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(itemView.getContext(), DetailTemanActivity.class);
                    intent.putExtra("teman", teman);
                    itemView.getContext().startActivity(intent);
                    Toast.makeText(itemView.getContext(), "Ashiapppp Selamat Datang", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
