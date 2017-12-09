package app.com.weektwo.model;

import app.com.weektwo.bean.Bean;

/**
 * model层接口，成功和失败的方法
 */
public interface ModelCallBack {
    public void onSuccess(Bean bean);
    public void onFailure(Exception e);
}
