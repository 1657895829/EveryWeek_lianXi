package app.example.videoplayer_quan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.hjm.bottomtabbar.BottomTabBar;
import app.example.videoplayer_quan.fragment.Find03_Fragment;
import app.example.videoplayer_quan.fragment.JingXuan01_Fragment;
import app.example.videoplayer_quan.fragment.Mine04_Fragment;
import app.example.videoplayer_quan.fragment.ZhuanTi02_Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)      //图片大小
                .setFontSize(12)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.RED, Color.BLACK)     //选项卡的选择颜色
                .addTabItem("精选", R.drawable.found, JingXuan01_Fragment.class)
                .addTabItem("专题", R.drawable.special, ZhuanTi02_Fragment.class)
                .addTabItem("发现", R.drawable.fancy, Find03_Fragment.class)
                .addTabItem("我的", R.drawable.my, Mine04_Fragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });
    }

}
