package com.dhw.everyweek;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.dhw.everyweek.bean.EventBusBean;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;

//EventBus的传值通信接收页
public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.draweeView)
    SimpleDraweeView draweeView;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //初始化EventBus,注册EventBus，订阅黏性事件
        EventBus.getDefault().register(this);
    }

    //主线程UI，注册粘性订阅事件，黏性事件处理函数
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)//这种写法达到粘性的目的
    public void receiveSticky(EventBusBean busBean){
        draweeView.setImageURI(busBean.getUrl());
        title.setText(busBean.getTitle());
    }

    //在onDestory()方法中取消订阅：防止内存溢出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}
