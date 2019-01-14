package simple.project.giisdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.helper.custom.ZoomImageView;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;

/**
 * @author Created by ys
 * @date at 2019/1/13 22:18
 * @describe
 */
public class SplashActivity extends QMUIFragmentActivity {



    @Override
    protected int getContextViewId() {
        return R.id.splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (("").equals(SPUtils.get(SplashActivity.this, USER_PHONE, "")))
            toLogin();
        else
            toMain();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


    }

    public void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
