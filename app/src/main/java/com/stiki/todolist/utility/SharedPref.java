package com.stiki.todolist.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private static final String KEY_ID_USER = "ID_USER";
    private static final String KEY_STATUS_LOGIN = "STATUS_LOGIN";

    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

    public static void addIdUser(Context context, int idUser) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putInt(KEY_ID_USER, idUser);
        editor.apply();
    }

    public static void addStatusLogin(Context context, boolean statusLogin) {
        SharedPreferences.Editor editor = getPreferenceEditor(context);
        editor.putBoolean(KEY_STATUS_LOGIN, statusLogin);
        editor.apply();
    }

    public static int getIdUser(Context context) {
        return getSharedPreferences(context).getInt(KEY_ID_USER, 0);
    }

    public static boolean isStatusLogin(Context context) {
        return getSharedPreferences(context).getBoolean(KEY_STATUS_LOGIN, false);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(KEY_ID_USER);
        editor.remove(KEY_STATUS_LOGIN);
        editor.apply();
    }

}
