package simple.project.giisdemo.helper.utils;

import android.app.Activity;
import android.os.Handler;
import android.util.TypedValue;

import com.andrognito.flashbar.Flashbar;

import simple.project.giisdemo.R;

/**
 * @author Created by ys
 * @date at 2019/1/14 15:18
 * @describe
 */
public class FlashBarUtil {

    public static void loginError(Activity mActivity, String msg) {
        TypedValue value = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimaryLight, value, true);
        Flashbar flashbar = new Flashbar.Builder(mActivity)
                .gravity(Flashbar.Gravity.BOTTOM)
                .message(msg)
                .messageColorRes(R.color.colorPrimaryText)
                .backgroundColorRes(value.resourceId)
                .build();
        flashbar.show();
        new Handler().postDelayed(() -> flashbar.dismiss(), 1000);

    }


    public static void validateError(Activity mActivity, String msg) {
        TypedValue value = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimaryLight, value, true);
        Flashbar flashbar = new Flashbar.Builder(mActivity)
                .gravity(Flashbar.Gravity.BOTTOM)
                .message(msg)
                .messageColorRes(R.color.colorPrimaryText)
                .backgroundColorRes(value.resourceId)
                .build();
        flashbar.show();
        new Handler().postDelayed(() -> flashbar.dismiss(), 1000);

    }
    public static void exitActivity(Activity mActivity, String msg) {
        TypedValue value = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorPrimaryLight, value, true);
        Flashbar flashbar = new Flashbar.Builder(mActivity)
                .gravity(Flashbar.Gravity.BOTTOM)
                .message(msg)
                .messageColorRes(R.color.colorPrimaryText)
                .backgroundColorRes(value.resourceId)
                .build();
        flashbar.show();
        new Handler().postDelayed(() -> flashbar.dismiss(), 1000);

    }
}
