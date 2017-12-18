package app.example.videoplayer_quan.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Banner加载图片工具类
 */

public class BannerImagler extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
    }
}
