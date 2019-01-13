package simple.project.giisdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import simple.project.giisdemo.R;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

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
