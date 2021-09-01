package com.stiki.todolist.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stiki.todolist.R;
import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.view.activity.TambahActivity;

import java.io.Serializable;
import java.util.List;

public class TodoAdapter  extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    private final List<Todo> list;
    private final TodoAdapter.onHapusData onHapusData;

    public interface onHapusData{
        void onHapusData(int id);
    }

    public TodoAdapter(List<Todo> list, TodoAdapter.onHapusData onHapusData) {
        this.list = list;
        this.onHapusData = onHapusData;
    }

    @NonNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position) {
        holder.textJudul.setText("Judul : " + list.get(position).getJudul());
        holder.textTanggal.setText("Tanggal : " + list.get(position).getTanggal());
        holder.textAgenda.setText("Agenda : " + list.get(position).getAgenda());

        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TambahActivity.class);
                i.putExtra("TODO", (Serializable) list.get(position));
                i.putExtra("IS_EDIT", true);
                v.getContext().startActivity(i);
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHapusData.onHapusData(list.get(position).idTodo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textJudul, textTanggal, textAgenda;
        private Button btnUbah, btnHapus;

        public ViewHolder(View v) {
            super(v);
            textJudul = v.findViewById(R.id.textJudul);
            textTanggal = v.findViewById(R.id.textTanggal);
            textAgenda = v.findViewById(R.id.textAgenda);
            btnUbah = v.findViewById(R.id.btnUbah);
            btnHapus = v.findViewById(R.id.btnHapus);
        }
    }
}
