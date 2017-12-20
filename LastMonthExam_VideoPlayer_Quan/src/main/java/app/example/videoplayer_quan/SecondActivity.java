package app.example.videoplayer_quan;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import app.example.videoplayer_quan.adapter.MyFragmentPagerAdapter;
import app.example.videoplayer_quan.bean.VideoBean;
import app.example.videoplayer_quan.presenter.BeanPresenter;
import app.example.videoplayer_quan.utils.Eventbusmessage;
import app.example.videoplayer_quan.view.BeanView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//视频详情页
public class SecondActivity extends AppCompatActivity implements BeanView {
    @BindView(R.id.title_top)
    TextView titleTop;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private BeanPresenter presenter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private String str = "";
    PlayerView play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);

        //实例化p层，获取数据
        presenter = new BeanPresenter((BeanView) this);
        presenter.success(str);

        //tab与viewpager与fragment
        initviews();
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
    public void onmessage(Eventbusmessage eventbusmessage) {
        str = eventbusmessage.getPosition();

        // 设置传递的影片名称
        titleTop.setText(eventbusmessage.getText());
        Toast.makeText(SecondActivity.this, eventbusmessage.getText() + eventbusmessage.getPosition(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success(VideoBean videoBean) {
        //获取 视频url 进行播放
        String url = videoBean.getRet().getSmoothURL();
        play = new PlayerView(this)
                .setTitle("标题")
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

    // 点击返回按钮，返回至上一页
    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    //设置viewpager的选项卡
    private void initviews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
    }
}
