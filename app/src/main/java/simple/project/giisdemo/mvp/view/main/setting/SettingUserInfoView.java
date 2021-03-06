package simple.project.giisdemo.mvp.view.main.setting;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import simple.project.giisdemo.base.BaseView;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:14
 * @describe
 */
public interface SettingUserInfoView extends BaseView {
    void toFinish();

    void showImage();

    void updatePhone();

    void updatePassword();

    void showErrorMsg(String errorMsg);
}
