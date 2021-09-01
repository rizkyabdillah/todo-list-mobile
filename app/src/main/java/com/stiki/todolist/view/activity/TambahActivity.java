package com.stiki.todolist.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.stiki.todolist.R;
import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.utility.Random;
import com.stiki.todolist.utility.SharedPref;
import com.stiki.todolist.viewmodel.RoomViewModel;

public class TambahActivity extends AppCompatActivity {

    private EditText textTambahJudul, textTambahTanggal, textTambahJam;
    private TextInputEditText textTambahAgenda;
    private TextView textJudulUtama;

    private RoomViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        textTambahJudul = findViewById(R.id.textTambahJudul);
        textTambahTanggal = findViewById(R.id.textTambahTanggal);
        textTambahJam = findViewById(R.id.textTambahJam);
        textTambahAgenda = findViewById(R.id.textTambahAgenda);
        textJudulUtama = findViewById(R.id.textJudulUtama);

        viewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        final boolean isEdit = this.getIntent().getBooleanExtra("IS_EDIT", false);
        final Todo TODO = (Todo) this.getIntent().getSerializableExtra("TODO");

        if(isEdit) {
            textJudulUtama.setText("UBAH DATA");
            final String[] DT = TODO.getTanggal().split("\\|");
            textTambahJudul.setText(TODO.getJudul());
            textTambahTanggal.setText(DT[0]);
            textTambahJam.setText(DT[1]);
            textTambahAgenda.setText(TODO.getAgenda());
        }

        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnSimpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {
                    if(isEdit) {
                        viewModel.editTodo(
                                TODO.idTodo,
                                textTambahJudul.getText().toString(),
                                textTambahTanggal.getText().toString().concat("|").concat(textTambahJam.getText().toString()),
                                textTambahAgenda.getText().toString()
                        );
                        Toast.makeText(v.getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        viewModel.insertTodo( new Todo(
                                Random.getRandom(4),
                                SharedPref.getIdUser(v.getContext()),
                                textTambahJudul.getText().toString(),
                                textTambahTanggal.getText().toString().concat("|").concat(textTambahJam.getText().toString()),
                                textTambahAgenda.getText().toString()
                        ));
                    }
                    Toast.makeText(v.getContext(), "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private boolean checkData() {
        int count = 0;

        if(textTambahJudul.getText().toString().isEmpty()) {
            textTambahJudul.setError("Mohon isi judul");
            count++;
        }

        if(textTambahTanggal.getText().toString().isEmpty()) {
            textTambahTanggal.setError("Mohon isi tanggal");
            count++;
        }

        if(textTambahJam.getText().toString().isEmpty()) {
            textTambahJam.setError("Mohon isi jam");
            count++;
        }

        if(textTambahAgenda.getText().toString().isEmpty()) {
            textTambahAgenda.setError("Mohon isi agenda");
            count++;
        }

        return (count == 0);
    }
}