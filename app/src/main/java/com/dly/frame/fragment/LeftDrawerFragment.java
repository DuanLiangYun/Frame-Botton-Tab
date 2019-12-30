package com.dly.frame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dly.frame.R;

/**
 * 框架中的左边侧滑布局Fragment
 */
public class LeftDrawerFragment extends Fragment {

    /**
     * 根布局
     */
    private View rootView;

    /**
     * 单例
     */
    public static LeftDrawerFragment newInstance() {
        LeftDrawerFragment fragment = new LeftDrawerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 绑定根布局
         */
        rootView = inflater.inflate(R.layout.fragment_left_drawer, container, false);
        return rootView;
    }
}
