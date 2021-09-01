package com.stiki.todolist.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stiki.todolist.R;
import com.stiki.todolist.room.entity.User;
import com.stiki.todolist.utility.SharedPref;
import com.stiki.todolist.viewmodel.RoomViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText textLoginUsername, textLoginPassword;
    private RoomViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textLoginUsername = findViewById(R.id.textLoginUsername);
        textLoginPassword = findViewById(R.id.textLoginPassword);

        viewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        findViewById(R.id.textDaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DaftarActivity.class));
                finish();
            }
        });

        findViewById(R.id.btnMasuk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {
                    viewModel.getUserLogin(
                            textLoginUsername.getText().toString(),
                            textLoginPassword.getText().toString()
                    ).observe(OWNER, new Observer<List<User>>() {
                        @Override
                        public void onChanged(List<User> users) {
                            if(users.size() == 0) {
                                Toast.makeText(v.getContext(), "Username atau password anda salah!", Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPref.addIdUser(v.getContext(), users.get(0).idUser);
                                SharedPref.addStatusLogin(v.getContext(), true);
                                Toast.makeText(v.getContext(), "Berhasil masuk", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(v.getContext(), MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });

    }

    private boolean checkData() {
        int error = 0;

        if(textLoginUsername.getText().toString().isEmpty()) {
            textLoginUsername.setError("Mohon Isi Kolom Username");
            error++;
        }

        if(textLoginPassword.getText().toString().isEmpty()) {
            textLoginPassword.setError("Mohon Isi Kolom password");
            error++;
        }

        return (error == 0);
    }
}