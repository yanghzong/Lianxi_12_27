package com.example.lianxi_12_27.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lianxi_12_27.R;
import com.example.lianxi_12_27.bean.DataShopBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TextFragment extends Fragment {
    @BindView(R.id.tv_title)
    TextView  tvTitle;

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.textfragment_item,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bind = ButterKnife.bind(this, getActivity());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
