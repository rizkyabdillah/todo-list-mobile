package com.stiki.todolist.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.stiki.todolist.room.entity.Todo;
import com.stiki.todolist.room.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Todo.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    private static volatile AppDB INSTANCE;
    private static final String DB_NAME = "DB";
    public static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static AppDB getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (AppDB.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, DB_NAME)
                            .allowMainThreadQueries().enableMultiInstanceInvalidation().build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract AppDao appDao();


}
