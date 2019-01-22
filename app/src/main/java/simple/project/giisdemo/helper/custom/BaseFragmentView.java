package simple.project.giisdemo.helper.custom;

import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> mActivity.onBackPressed());
    }

    /*
     * 添加返回按钮和返回视图名称
     * */
    public static void initBackName(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        LinearLayout backView = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.back_bar, null);
        TextView lastName = backView.findViewById(R.id.last_name);
        lastName.setText(stringResId);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTopBar.addLeftView(backView, R.id.back, layoutParams);
        backView.setOnClickListener(v -> mActivity.onBackPressed());
    }

    /*
     * 添加搜索按钮和功能
     * */
    public static SearchView initSearchBar(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity) {
        SearchView searchView = (SearchView) LayoutInflater.from(mActivity).inflate(R.layout.search_bar, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTopBar.addRightView(searchView, R.id.search, layoutParams);
        return searchView;
    }

    /*
     * 添加标题
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
     * 添加返回按钮、返回视图名称和标题
     * */

    public static void initBackNameAndTitle(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int backSringResId, int titleStringResId) {
        LinearLayout backView = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.back_bar, null);
        TextView lastName = backView.findViewById(R.id.last_name);
        lastName.setText(backSringResId);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTopBar.addLeftView(backView, R.id.back, layoutParams);
        backView.setOnClickListener(v -> mActivity.onBackPressed());
        mTopBar.setTitle(titleStringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }
    /*
     * 添加返回按钮和标题
     * Activity
     * */

    public static void initBackAndTitleActivity(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.addLeftBackImageButton().setOnClickListener(v -> {
            mActivity.onBackPressed();
        });
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
    }
    /*
     * 添加右侧文字按钮
     * */

    public static Button initRightTextButton(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId, int viewId) {
        Button button = mTopBar.addRightTextButton(stringResId, viewId);
        button.setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
        return button;
    }

    /*
     * 添加标题和返回按钮
     * */
    public static View initTitleAndBack(QMUITopBarLayout mTopBar, QMUIFragmentActivity mActivity, int stringResId) {
        mTopBar.setTitle(stringResId).setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
        return mTopBar.addRightImageButton(R.drawable.ic_search, R.id.search);
    }
}
