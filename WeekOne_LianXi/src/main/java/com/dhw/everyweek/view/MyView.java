package com.dhw.everyweek.view;

import com.dhw.everyweek.bean.NewsBean;

/**
 * view层接口类，请求成功与失败的方法
 */
public interface MyView {
    public void onSuccess(NewsBean bean);
    public void onFailure(Exception e);
}
