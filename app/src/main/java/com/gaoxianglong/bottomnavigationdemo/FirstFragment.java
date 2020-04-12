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

public class FirstFragment extends Fragment {

    private FirstViewModel mViewModel;
    private ImageView mImageView;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        mImageView = view.findViewById(R.id.imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(FirstViewModel.class); // 这里传入的this表示ViewModel保存的数据只在当前碎片中生效，要是从其他碎片中切换回到这个碎片，数据将会丢失，可以使用requireActivity()扩大范围
        mImageView.setRotation(mViewModel.rotationPosition); // 因为使用了ViewModel保存了旋转的度数，所以每次创建活动时就获取出上一次保存是度数
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotation", 0, 0);// 设置属性动画，旋转
        animator.setDuration(500); // 设置持续时间
        mImageView.setOnClickListener(v -> {
            // 判断动画是否是在执行中，如果不是就执行动画
            if (!animator.isRunning()) {
                animator.setFloatValues(mImageView.getRotation(), mImageView.getRotation() + 90); // 通过mImageView.getRotation()获取到图片当前的旋转度数是多少
                mViewModel.rotationPosition += 90;
                animator.start(); // 启动动画
            }
        });
    }

}
