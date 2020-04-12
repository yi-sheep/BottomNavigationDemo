package com.gaoxianglong.bottomnavigationdemo;

import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Random;

public class ThirdFragment extends Fragment {

    private ThirdViewModel mViewModel;
    private ImageView mImageView;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(ThirdViewModel.class);

        mImageView.setX(mImageView.getX() + mViewModel.dx);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView,"X",0,0);
        animator.setDuration(500);
        mImageView.setOnClickListener(v -> {
            if (!animator.isRunning()) {
                float dx = new Random().nextBoolean() ? 100:-100;
                animator.setFloatValues(mImageView.getX(),mImageView.getX() + dx);
                mViewModel.dx += dx;
                animator.start(); // 启动
            }
        });
    }

}
