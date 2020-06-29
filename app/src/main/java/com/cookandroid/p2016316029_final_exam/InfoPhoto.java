package com.cookandroid.p2016316029_final_exam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoPhoto extends Fragment {
    static InfoPhoto newInstance(){
        return new InfoPhoto();
    }
    ViewGroup viewGroup;
    ImageView ivInfo;
    TextView tvInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.infophoto, container, false);
        ivInfo = (ImageView)viewGroup.findViewById(R.id.imageView3);
        tvInfo = (TextView)viewGroup.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        String fileName = bundle.getString("fileName");
        String detail = bundle.getString("detail");//bundle.getString("detail");
        Bitmap bmp = BitmapFactory.decodeFile(fileName);
        tvInfo.setText(detail);
        ivInfo.setImageBitmap(bmp);


        return viewGroup;
    }
}
