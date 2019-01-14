package simple.project.giisdemo.helper.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.andrognito.flashbar.Flashbar;

import simple.project.giisdemo.R;

/**
 * @author Created by ys
 * @date at 2019/1/14 15:18
 * @describe
 */
public class FlashBarUtil {
    public static void loginError(Activity mActivity, String msg) {
        Flashbar flashbar = new Flashbar.Builder(mActivity)
                .gravity(Flashbar.Gravity.BOTTOM)
                .message(msg)
                .messageColorRes(R.color.material_colorPrimaryText)
                .backgroundColorRes(R.color.material_colorPrimaryLight)
                .build();
        flashbar.show();
        new Handler().postDelayed(() -> flashbar.dismiss(), 1000);

    }
}
