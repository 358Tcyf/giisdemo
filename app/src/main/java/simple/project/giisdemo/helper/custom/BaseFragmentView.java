package simple.project.giisdemo.helper.custom;

import android.content.Intent;
import android.widget.Button;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.MainActivity;

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
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.onBackPressed());
    }

    /*
     * 添加和标题
     * */
    public static void initTitle(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }

    /*
     * 添加返回按钮和标题
     * */
    public static void initBackAndTitle(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.onBackPressed());
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }

    /*
     * 添加返回按钮和标题
     * Activity
     * */
    public static void initBackAndTitleActivity(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> {
//            Intent intent = new Intent(mActivity, MainActivity.class);
//            intent.putExtra("flag", 3);
//            mActivity.startActivity(intent);
//            mActivity.finish();
            mActivity.onBackPressed();
        });
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }

    /*
     * 添加返回按钮和标题
     * */
    public static void initBackAndTitle2(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.finish());
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }

    /*
     * 添加右侧文字按钮
     * */
    public static Button initRightTextButtoninitial(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId, int viewId) {
        Button button = mTopBar.addRightTextButton(stringResId, viewId);
        button.setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
        return button;
    }

}
