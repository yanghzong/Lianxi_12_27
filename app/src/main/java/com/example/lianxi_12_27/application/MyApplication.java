package com.example.lianxi_12_27.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.lianxi_12_27.greendao.DaoMaster;
import com.example.lianxi_12_27.greendao.DaoSession;
import com.example.lianxi_12_27.utils.NetWorkUtils;

public class MyApplication  extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
        initNetWork();
    }

    private void initNetWork() {
        boolean b = NetWorkUtils.isNetWorkAvailable(getApplicationContext());
        if(b){//网络正常
            Toast.makeText(getApplicationContext(),"网络正常",Toast.LENGTH_LONG).show();
        }else{
            //网络异常
            Toast.makeText(getApplicationContext(),"网络异常",Toast.LENGTH_LONG).show();
        }
    }
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "customer.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster dm = new DaoMaster(database);
        daoSession = dm.newSession();
    }
}
