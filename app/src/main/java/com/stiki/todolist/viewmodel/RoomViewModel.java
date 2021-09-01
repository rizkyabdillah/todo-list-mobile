package com.stiki.todolist.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.stiki.todolist.repository.Repository;
import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.room.entity.User;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private final Repository repository;

    public RoomViewModel(Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public void insertUser(User user) {
        this.repository.insertUser(user);
    }

    public void insertTodo(Todo todo) {
        this.repository.insertTodo(todo);
    }

    public void editTodo(int idTodo, String judul, String tanggal, String agenda) {
        this.repository.editTodo(idTodo, judul, tanggal, agenda);
    }

    public void deleteTodo(int idTodo) {
        this.repository.deleteTodo(idTodo);
    }

    public LiveData<List<User>> getUserLogin(String username, String password) {
        return this.repository.getUserLogin(username, password);
    }

    public LiveData<User> getUser(int idUser) {
        return this.repository.getUser(idUser);
    }

    public LiveData<List<Todo>> getTodo(int idUser) {
        return this.repository.getTodo(idUser);
    }
}
