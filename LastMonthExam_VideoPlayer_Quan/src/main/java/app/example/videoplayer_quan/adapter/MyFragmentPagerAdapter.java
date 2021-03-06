package app.example.videoplayer_quan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import app.example.videoplayer_quan.tab.Frag_tab1;
import app.example.videoplayer_quan.tab.Frag_tab2;

/**
 * Tab 选项卡 的 Fragment 适配器
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"简历","评论"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==1){
            return new Frag_tab2();
        }
        return new Frag_tab1();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
