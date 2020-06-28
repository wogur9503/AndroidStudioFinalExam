package com.cookandroid.p2016316029_final_exam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;

public class TakeAPhoto extends Fragment{
    final static int TAKE_PICTURE = 1;
    final String TAG = getClass().getSimpleName();
    ImageView iv;
    Button btnCamera;
    ViewGroup viewGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.takeaphoto,container,false);
            btnCamera = (Button)viewGroup.findViewById(R.id.button);
            iv = (ImageView)viewGroup.findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.button:
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        return viewGroup;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){
            case TAKE_PICTURE:
                if(resultCode == RESULT_OK && intent.hasExtra("data")){
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    if(bitmap != null){
                        String fileName = String.valueOf(System.currentTimeMillis())+".png";
                        File file = new File(Environment
                                .getExternalStorageDirectory().getAbsolutePath()+"/Pictures/"+fileName);
                        FileOutputStream fos = null;
                        try {
                            file.createNewFile();
                            fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            fos.close();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        iv.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }


}
