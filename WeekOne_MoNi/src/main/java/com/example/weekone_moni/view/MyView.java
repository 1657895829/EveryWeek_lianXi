package com.example.weekone_moni.view;

import com.example.weekone_moni.bean.DataBean;
/**
 * view层接口类，请求成功与失败的方法
 */public interface MyView {
     public void onSuccess(DataBean bean);
     public void onFailure(Exception e);
}
