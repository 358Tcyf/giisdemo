package simple.project.giisdemo.mvp.view.login;

import simple.project.giisdemo.base.BaseView;

/**
 * @author Created by ys
 * @date at 2019/1/8 15:27
 * @describe
 */
public interface SignUpView extends BaseView {

    void signUpProgress();

    void toLogin(); //跳转到登入界面

    void showErrorMsg(String errorMsg);
}
