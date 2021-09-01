package com.stiki.todolist.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stiki.todolist.R;
import com.stiki.todolist.adapter.TodoAdapter;
import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.room.entity.User;
import com.stiki.todolist.utility.SharedPref;
import com.stiki.todolist.view.activity.LoginActivity;
import com.stiki.todolist.view.activity.TambahActivity;
import com.stiki.todolist.viewmodel.RoomViewModel;

import java.util.List;

public class BerandaFragment extends Fragment implements TodoAdapter.onHapusData{

    private RoomViewModel viewModel;
    private final LifecycleOwner OWNER = this;
    private TextView textNama;
    private TodoAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        textNama = view.findViewById(R.id.textNama);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        view.findViewById(R.id.textLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.clear(v.getContext());
                getActivity().finish();
                v.getContext().startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });

        view.findViewById(R.id.fabTambah).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), TambahActivity.class));
            }
        });

        viewModel.getUser(SharedPref.getIdUser(getContext())).observe(OWNER, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                final String[] nama = user.getName().split(" ");
                textNama.setText("Halo, " + nama[0]);
            }
        });

        viewModel.getTodo(SharedPref.getIdUser(getContext())).observe(OWNER, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                adapter = new TodoAdapter(todos, BerandaFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onHapusData(int id) {
        new AlertDialog.Builder(getContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah anda yakin ingin menghapus?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteTodo(id);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
}