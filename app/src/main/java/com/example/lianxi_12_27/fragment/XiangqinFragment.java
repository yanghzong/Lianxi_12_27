package com.example.lianxi_12_27.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lianxi_12_27.R;
import com.example.lianxi_12_27.bean.DataShopBean;
import com.example.lianxi_12_27.inter.Cantant;
import com.example.lianxi_12_27.presenter.DataPresenter;
import com.example.lianxi_12_27.utils.StringUtils;
import com.example.lianxi_12_27.view.DataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class XiangqinFragment extends Fragment implements DataView {
    @BindView(R.id.img_xiangqing)
    ImageView  imgXinagqing;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView  tvPrice;
    @BindView(R.id.add_shop)
    Button btnAdd;
    private Unbinder bind;
    private DataPresenter presenter;
    private String pid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.xiangqinfragment_item,container,false);

       SharedPreferences sp = getActivity().getSharedPreferences("pid", Context.MODE_PRIVATE);
        String pid = sp.getString("pid", "");
        presenter = new DataPresenter();
        presenter.attach(this);
        presenter.DataShop(Cantant.xiangqing+pid);
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
    @Override
    public void getData(DataShopBean dataShopBean) {
        DataShopBean.DataBean data = dataShopBean.getData();
        if (data !=null){
            tvTitle.setText(data.getTitle());
            tvPrice.setText(""+ data.getPrice());
            String[] split = data.getImages().split("\\|");
            Glide.with(getActivity()).load(StringUtils.Http2http(split[0])).into(imgXinagqing);


        }
    }
}
