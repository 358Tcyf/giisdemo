package simple.project.giisdemo.helper.utils;

import android.app.Activity;
import android.os.Handler;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * @author Created by ys
 * @date at 2019/1/20 14:11
 * @describe
 */
public class QMUIUtil {

    public static void successTipDialog(Activity mActivity, String success) {
        QMUITipDialog tipDialog = new QMUITipDialog.Builder(mActivity)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(success)
                .create();
        tipDialog.show();
        new Handler().postDelayed(() -> tipDialog.dismiss(), 500);
    }
}
