package simple.project.giisdemo.helper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import simple.project.giisdemo.activity.MainActivity;

/**
 * @author Created by ys
 * @date at 2019/1/23 17:03
 * @describe
 */
public class ActivityUtil {
    public static void toMainActivity(Activity mActivity, int flag) {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra("flag", flag);
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mActivity.finish();
    }
}