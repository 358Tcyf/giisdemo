package simple.project.giisdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.helper.custom.ZoomImageView;
import simple.project.giisdemo.helper.utils.MessageEvent;

import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;
import static simple.project.giisdemo.helper.utils.SBUtil.stringToBitmap;

/**
 * @author Created by ys
 * @date at 2019/1/14 13:34
 * @describe
 */
public class ShowImageActivity extends QMUIFragmentActivity {
    @BindView(R.id.show_image)
    ZoomImageView showImage;


    @Override
    protected int getContextViewId() {
        return R.id.image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimage);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        String stringExtra= intent.getStringExtra("image");
        Uri uri = Uri.parse(stringExtra);
        showImage.setImageURI(uri);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        showImage.setImageURI((Uri) messageEvent.getMessage().get("image"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
