package com.example.lianxi_12_27;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxi_12_27.adapter.GridAdapter;
import com.example.lianxi_12_27.adapter.RecycleAdapter;
import com.example.lianxi_12_27.bean.ShopBean;
import com.example.lianxi_12_27.inter.Cantant;
import com.example.lianxi_12_27.presenter.ShopPresenter;
import com.example.lianxi_12_27.view.ShopView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity  implements ShopView {
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.btn_grid)
    Button  btnGrid;
    @BindView(R.id.btn_line)
    Button  btnLine;
    @BindView(R.id.btn_souhuo)
    Button   btnSousuo;
    @BindView(R.id.tv_zonghe)
    TextView  tvZonghe;
    @BindView(R.id.tv_xiaoliang)
    TextView  tvXiaoliang;
    @BindView(R.id.tv_jiage)
    TextView  tvJiage;
    @BindView(R.id.tv_shaixuan)
    TextView  tvShaixuan;
    @BindView(R.id.ed_sou)
    EditText  edSou;


    private List<ShopBean.DataBean> shoplist;
    private ShopPresenter shopPresenter;
    private RecycleAdapter recycleAdapter;
    private GridAdapter gridAdapter;
    private Unbinder bind;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("pid", MODE_PRIVATE);
        bind = ButterKnife.bind(this, this);
        shoplist = new ArrayList<>();
        shopPresenter = new ShopPresenter();
        shopPresenter.attach(this);
        shopPresenter.getShop(Cantant.shoplist);
        recycleAdapter = new RecycleAdapter(this,shoplist);
        rvShop.setAdapter(recycleAdapter);
        rvShop.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        gridAdapter = new GridAdapter(this,shoplist);
        gridAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClick() {
            @Override
            public void onItemClick(String pid) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);


            }
        });
        recycleAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClick() {
            @Override
            public void onItemClick(String pid) {
                sp.edit().putString("pid",pid).commit();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });







    }
    @OnClick({R.id.btn_line,R.id.btn_grid})
    public  void onclickChange(View v){

          switch (v.getId()){
              case R.id.btn_line:

                  rvShop.setAdapter(recycleAdapter);
                  rvShop.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                  btnGrid.setVisibility(View.VISIBLE);
                  break;
              case R.id.btn_grid:

                  rvShop.setAdapter(gridAdapter);
                  rvShop.setLayoutManager(new GridLayoutManager(this,2));
                  btnLine.setVisibility(View.VISIBLE);
                  btnGrid.setVisibility(View.GONE);
                  break;
          }

    }

    @Override
    public void getShop(ShopBean shopBean) {
        List<ShopBean.DataBean> data = shopBean.getData();
        if (data!=null){
            shoplist.clear();
            shoplist.addAll(data);
            recycleAdapter.notifyDataSetChanged();
            gridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        shopPresenter.datach();

    }
}
