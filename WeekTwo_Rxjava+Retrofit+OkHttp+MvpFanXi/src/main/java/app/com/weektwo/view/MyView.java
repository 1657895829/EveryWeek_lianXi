package app.com.weektwo.view;

import app.com.weektwo.bean.Bean;

/**
 * view层接口类，请求成功与失败的方法
 */
public interface MyView {
    public void onSuccess(Bean bean);
    public void onFailure(Exception e);
}
