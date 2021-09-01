package com.stiki.todolist.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.todolist.R;
import com.stiki.todolist.room.entity.User;
import com.stiki.todolist.utility.Random;
import com.stiki.todolist.utility.SharedPref;
import com.stiki.todolist.viewmodel.RoomViewModel;

public class DaftarActivity extends AppCompatActivity {

    private EditText textDaftarNamaLengkap, textDaftarUsername, textDaftarPassword;
    private RoomViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        textDaftarNamaLengkap = findViewById(R.id.textDaftarNamaLengkap);
        textDaftarUsername = findViewById(R.id.textDaftarUsername);
        textDaftarPassword = findViewById(R.id.textDaftarPassword);

        viewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        findViewById(R.id.textMasuk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LoginActivity.class));
                finish();
            }
        });

        findViewById(R.id.btnDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {
                    final int idUser = Random.getRandom(3);
                    viewModel.insertUser(new User(
                            idUser,
                            textDaftarNamaLengkap.getText().toString(),
                            textDaftarUsername.getText().toString(),
                            textDaftarPassword.getText().toString()
                    ));
                    SharedPref.addIdUser(v.getContext(), idUser);
                    SharedPref.addStatusLogin(v.getContext(), true);
                    Toast.makeText(v.getContext(), "Berhasil mendaftar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(v.getContext(), MainActivity.class));
                    finish();
                }
            }
        });
    }


    private boolean checkData() {
        int error = 0;
        if(textDaftarNamaLengkap.getText().toString().isEmpty()) {
            textDaftarNamaLengkap.setError("Mohon Isi Kolom Nama Lengkap");
            error++;
        }

        if(textDaftarUsername.getText().toString().isEmpty()) {
            textDaftarUsername.setError("Mohon Isi Kolom Username");
            error++;
        }

        if(textDaftarPassword.getText().toString().isEmpty()) {
            textDaftarPassword.setError("Mohon Isi Kolom password");
            error++;
        }

        return (error == 0);
    }
}