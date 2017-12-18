package app.example.videoplayer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.example.videoplayer.R;
import app.example.videoplayer.SuperVideoDetailsActivity;
import app.example.videoplayer.SuperVideoRecycleViewActivity;

/**
    精选页面
 */
public class JingXuan01_Fragment extends Fragment  implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jingxuan_fragment, container, false);
        view.findViewById(R.id.tv_demand_play).setOnClickListener(this); //点播
        view.findViewById(R.id.tv_live_play).setOnClickListener(this);   //直播
        view.findViewById(R.id.tv_recycleview_player).setOnClickListener(this);//recycleView

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_demand_play) {
            Intent demandIntent = new Intent(getActivity(),SuperVideoDetailsActivity.class);
            demandIntent.putExtra("isLive",false);
            demandIntent.putExtra("url","http://movie.vods0.cnlive.com/3/vod/2017/0607/3_da131623be6e40378e3da8c949dd3874/ff8080815bf6b453015c82db4adc192a_400.m3u8");
            startActivity(demandIntent);

        } else if (v.getId() == R.id.tv_live_play) {
            Intent liveIntent = new Intent(getActivity(),SuperVideoDetailsActivity.class);
            liveIntent.putExtra("isLive",true);
            liveIntent.putExtra("url","http://172.17.29.27/oppo.mp4");
            startActivity(liveIntent);
        } else if (v.getId() == R.id.tv_recycleview_player) {
            Intent listViewIntent = new Intent(getActivity(),SuperVideoRecycleViewActivity.class);
            startActivity(listViewIntent);
        }
    }
}
