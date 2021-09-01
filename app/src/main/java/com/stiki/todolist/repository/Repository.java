package com.stiki.todolist.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.stiki.todolist.room.AppDB;
import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.room.entity.User;

import java.util.List;

public class Repository {

    private final AppDB DB;

    public Repository(Context context) {
        this.DB = AppDB.getInstance(context);
    }

    public void insertUser(User user) {
        AppDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DB.appDao().insertUser(user);
            }
        });
    }

    public void insertTodo(Todo todo) {
        AppDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DB.appDao().insertTodo(todo);
            }
        });
    }

    public void editTodo(int idTodo, String judul, String tanggal, String agenda) {
        AppDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DB.appDao().editTodo(idTodo, judul, tanggal, agenda);
            }
        });
    }

    public void deleteTodo(int idTodo) {
        AppDB.executorService.execute(new Runnable() {
            @Override
            public void run() {
                DB.appDao().deleteTodo(idTodo);
            }
        });
    }

    public LiveData<List<User>> getUserLogin(String username, String password) {
        return this.DB.appDao().getUserLogin(username, password);
    }

    public LiveData<User> getUser(int idUser) {
        return this.DB.appDao().getUser(idUser);
    }

    public LiveData<List<Todo>> getTodo(int idUser) {
        return this.DB.appDao().getTodo(idUser);
    }
}
