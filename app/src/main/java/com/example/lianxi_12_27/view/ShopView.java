package com.example.lianxi_12_27.view;

import com.example.lianxi_12_27.bean.ShopBean;

public interface ShopView {
    void  getShop(ShopBean shopBean);
    void  failed(Exception e);
}
