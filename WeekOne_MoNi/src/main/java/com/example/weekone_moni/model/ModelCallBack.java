package com.example.weekone_moni.model;

import com.example.weekone_moni.bean.DataBean;

/**
 * model层接口，成功和失败的方法
 */
public  interface ModelCallBack {
    public void onSuccess(DataBean bean);
    public void onFailure(Exception e);
}
