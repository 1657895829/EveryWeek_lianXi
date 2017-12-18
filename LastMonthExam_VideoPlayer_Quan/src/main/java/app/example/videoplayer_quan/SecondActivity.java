package app.example.videoplayer_quan;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.example.videoplayer_quan.bean.Bean;
import app.example.videoplayer_quan.presenter.BeanPresenter;
import app.example.videoplayer_quan.utils.Eventbusmessage;
import app.example.videoplayer_quan.view.BeanView;

public class SecondActivity extends AppCompatActivity implements BeanView {
    private BeanPresenter presenter;
    private String str="";
    PlayerView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EventBus.getDefault().register(this);
        presenter=new BeanPresenter((BeanView) this);
        presenter.success(str);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Subscribe(sticky = true)
    public void onmessage(Eventbusmessage eventbusmessage){
        str=eventbusmessage.getPosition();
        Toast.makeText(SecondActivity.this,eventbusmessage.getText()+eventbusmessage.getPosition(),Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(Bean bean) {
        String url = bean.getRet().getSmoothURL();
        play = new PlayerView(this)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        play.startPlay();
    }
    @Override
    protected void onStop() {
        super.onStop();
        play.stopPlay();
    }
}
