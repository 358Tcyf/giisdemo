package simple.project.giisdemo.mvp.view.login;

import simple.project.giisdemo.base.BaseView;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:57
 * @describe
 */
public interface LoginView extends BaseView {

    void loginProgress();

    void toMain();//跳转到主页面

    void toSignUp();//跳转到注册页面

    void showErrorMsg(String errorMsg);

    void setAccount(String phone);

    void setPassword(String passwd);

}
