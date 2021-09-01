package com.stiki.todolist.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TODO")
public class Todo implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id_todo")
    public int idTodo;

    @ColumnInfo(name = "id_user")
    public int idUser;

    @ColumnInfo(name = "judul")
    public String judul;

    @ColumnInfo(name = "agenda")
    public String agenda;

    @ColumnInfo(name = "tanggal")
    public String tanggal;

    public Todo(int idTodo, int idUser, String judul, String tanggal, String agenda) {
        this.idTodo = idTodo;
        this.idUser = idUser;
        this.judul = judul;
        this.agenda = agenda;
        this.tanggal = tanggal;
    }

    public int getIdTodo() {
        return idTodo;
    }

    public void setIdTodo(int idTodo) {
        this.idTodo = idTodo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
