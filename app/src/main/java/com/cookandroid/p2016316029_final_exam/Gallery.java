package com.cookandroid.p2016316029_final_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Gallery extends Fragment {
    ViewGroup viewGroup2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup2 = (ViewGroup) inflater.inflate(R.layout.gallery,container,false);

        return viewGroup2;
    }


}
