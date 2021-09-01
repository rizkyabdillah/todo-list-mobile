package com.stiki.todolist.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.room.entity.User;

import java.util.List;

@Dao
public interface AppDao {

    /* ================ */
    /* USER */
    /* ================ */

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM USER WHERE username = :username AND password = :password")
    LiveData<List<User>> getUserLogin(String username, String password);

    @Query("SELECT * FROM USER WHERE id_user = :idUser")
    LiveData<User> getUser(int idUser);



    /* ================ */
    /* TODO */
    /* ================ */

    @Insert
    void insertTodo(Todo todo);

    @Query("UPDATE TODO SET judul = :judul, tanggal = :tanggal, agenda = :agenda WHERE id_todo = :idTodo")
    void editTodo(int idTodo, String judul, String tanggal, String agenda);

    @Query("DELETE FROM TODO WHERE id_todo = :idTodo")
    void deleteTodo(int idTodo);

    @Query("SELECT * FROM TODO WHERE id_user = :idUser")
    LiveData<List<Todo>> getTodo(int idUser);
}
