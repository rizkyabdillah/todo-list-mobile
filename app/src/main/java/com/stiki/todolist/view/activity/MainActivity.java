package com.stiki.todolist.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stiki.todolist.R;
import com.stiki.todolist.utility.SharedPref;
import com.stiki.todolist.view.fragment.BerandaFragment;
import com.stiki.todolist.view.fragment.TentangFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment fragmentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        if(SharedPref.isStatusLogin(this)) {
            loadFragment(new BerandaFragment());
            fragmentActive = new BerandaFragment();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new BerandaFragment();
                        break;
                    case R.id.about:
                        fragment = new TentangFragment();
                        break;
                }

                if (!fragment.getClass().getName().equalsIgnoreCase(fragmentActive.getClass().getName())) {
                    return loadFragment(fragment);
                } else {
                    return false;
                }
            }
        });

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentActive = fragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            return true;
        }
        return false;
    }
}