package simple.project.giisdemo.mvp.presenter.login;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.mvp.model.login.SignUpModel;
import simple.project.giisdemo.mvp.view.login.SignUpView;

/**
 * @author Created by ys
 * @date at 2019/1/8 15:27
 * @describe
 */
public class SignUpPresenter extends BasePresenter<SignUpView, SignUpModel> {
    public void signUp(String name, String phone, String password) {
        getModel().signUp(name, phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {

                //TODO 存入成功信息,跳转到登陆界面
                getView().toLogin();
            }

            @Override
            public void onFailed(String errorMsg) {

                //TODO 存入失败信息
                getView().showErrorMsg(errorMsg);
            }
        });
    }
}
