package com.dhw.everyweek.model;

import com.dhw.everyweek.bean.NewsBean;

/**
 * model类接口，成功和失败的方法
 */
public interface ModelCallBack {
    public void onSuccess(NewsBean bean);
    public void onFailure(Exception e);
}
