package simple.project.giisdemo.mvp.presenter;

import android.util.Log;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.mvp.model.LoginModel;
import simple.project.giisdemo.mvp.view.LoginView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:50
 * @describe
 */
public class LoginPresenter extends BasePresenter<LoginView, LoginModel> {

    public void login(String phone, String password) {
        getModel().login(phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                Log.d(DEBUG, "SUCCESS");
                //TODO 存入成功信息，跳转到主界面
                getView().toMain();
            }

            @Override
            public void onFailed(String errorMsg) {
                Log.d(DEBUG, "FAILED");
                //TODO 存入失败信息
                getView().showErrorMsg(errorMsg);
            }
        });
    }

    public void setAccount() {
        getView().setAccount(getModel().getAccount());
    }

    public void setPassword() {
        getView().setPasswd(getModel().getPassword());
    }

}
