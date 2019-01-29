package simple.project.giisdemo.mvp.view.main.setting;

import simple.project.giisdemo.base.BaseView;

/**
 * @author Created by ys
 * @date at 2019/1/17 16:58
 * @describe
 */
public interface UpdateUserInfoView extends BaseView {

    boolean validate();

    void showErrorMsg(String errorMsg);

    void updateInfo(String phone);

    void toBack();
}
