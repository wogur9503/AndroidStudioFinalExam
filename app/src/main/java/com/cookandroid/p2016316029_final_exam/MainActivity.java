package com.cookandroid.p2016316029_final_exam;

import android.Manifest;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    ImageView iv;
    GridView gv;
    Button btnCamera;
    File[] imageFiles;
    String imageFname;
    final static int TAKE_PICTURE = 1;
    BottomNavigationView bottomNavigationView;
    Fragment takeaphoto, gallery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MODE_PRIVATE);
        takeaphoto = new TakeAPhoto();
        gallery = new Gallery();

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottomNavigationView);


        //초기화면 뷰를 설정해줌
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout,takeaphoto).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){

                        case R.id.tab1:{
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.main_layout, takeaphoto)
                                    .commitAllowingStateLoss();

                            return true;
                        }

                        case R.id.tab2:{
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.main_layout, gallery)
                                    .commitAllowingStateLoss();

                            return true;
                        }
                        default:return false;
                    }
                }

        });

    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_layout, fragment).commit();
    }
}