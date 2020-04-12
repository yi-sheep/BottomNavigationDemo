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

public class SecondFragment extends Fragment {

    private SecondViewModel mViewModel;
    private ImageView mImageView;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SecondViewModel.class);
        mImageView.setScaleX(mViewModel.scaleFactor);
        mImageView.setScaleY(mViewModel.scaleFactor);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mImageView,"ScaleX",0,0); // 创建一个ScaleX属性动画
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mImageView,"ScaleY",0,0); // 创建一个ScaleY属性动画
        animatorX.setDuration(500);
        animatorY.setDuration(500);
        mImageView.setOnClickListener(v -> {
            if (!animatorX.isRunning()) {
                animatorX.setFloatValues(mImageView.getScaleX() + 0.1f); // 设置X的缩放倍数
                animatorY.setFloatValues(mImageView.getScaleY() + 0.1f); // 设置Y的缩放倍数
                mViewModel.scaleFactor += 0.1;
                animatorX.start(); // 启动
                animatorY.start();
            }
        });
    }

}
