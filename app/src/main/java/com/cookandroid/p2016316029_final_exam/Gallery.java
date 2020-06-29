package com.cookandroid.p2016316029_final_exam;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;

public class Gallery extends Fragment {
    ViewGroup viewGroup2;
    GridView gv;
    PhotoAdapter adapter;
    int curNum = 0;
    File[] imageFiles;
    String imageFname;
    InfoPhoto infoPhoto = new InfoPhoto();
    MyDBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup2 = (ViewGroup) inflater.inflate(R.layout.gallery, container, false);
        gv = (GridView)viewGroup2.findViewById(R.id.gv);
        dbHelper = new MyDBHelper(getActivity());
        sqLiteDatabase = dbHelper.getReadableDatabase();
        dbHelper.onUpgrade(sqLiteDatabase,0,1);
        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        curNum = 0;
        adapter = new PhotoAdapter(imageFiles, getActivity());
        //Bitmap bm = BitmapFactory.decodeFile(imageFiles[curNum].toString());
        gv.setAdapter(adapter);
        return viewGroup2;
    }

    private class PhotoAdapter extends BaseAdapter {
        File[] photoFiles;
        Context context = null;

        public PhotoAdapter(File[] photoFiles, Context context) {
            this.photoFiles = photoFiles;
            this.context = context;
        }

        @Override
        public int getCount() {
            return photoFiles.length;
        }

        public Object getItem(int i) {
            return photoFiles[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ImageView iv;
            if (null != view)
                iv = (ImageView)view;
            else {
                Bitmap bm = BitmapFactory.decodeFile(imageFiles[curNum].toString());
                curNum++;
                iv = new ImageView(context);
                iv.setAdjustViewBounds(true);
                iv.setImageBitmap(bm);
            }
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sqLiteDatabase = dbHelper.getReadableDatabase();
                    Cursor cursor;
                    cursor = sqLiteDatabase.rawQuery("SELECT * FROM imagelist WHERE imageid="+ (i+1) +";", null);
                    while (cursor.moveToNext()){
                        String picture = cursor.getString(1);
                        String detail = cursor.getString(2);
                        Bundle bundle = new Bundle();
                        bundle.putString("picture", picture);
                        bundle.putString("detail", detail);
                        infoPhoto.setArguments(bundle);
                    }
                    ((MainActivity)getActivity()).replaceFragment(infoPhoto);
                }
            });
            return iv;
        }
    }
}
