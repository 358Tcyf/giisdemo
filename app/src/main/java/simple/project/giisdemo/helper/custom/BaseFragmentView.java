package simple.project.giisdemo.helper.custom;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import simple.project.giisdemo.R;

/**
 * @author Created by ys
 * @date at 2019/1/13 20:25
 * @describe
 */
public class BaseFragmentView {
    /*
     * 添加返回按钮
     * */
    public static void initBack(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.popBackStack());
    }

    /*
     * 添加返回按钮和标题
     * */
    public static void initBackAndTitle(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.popBackStack());
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.material_colorText_Icon, null));
    }

    /*
     * 添加右侧文字按钮
     * */
    public static Button initRightTextButtoninitial(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId, int viewId) {
        Button button = mTopBar.addRightTextButton(stringResId, viewId);
        button.setTextColor(mActivity.getResources().getColor(R.color.material_colorText_Icon, null));
        return button;
    }

}
