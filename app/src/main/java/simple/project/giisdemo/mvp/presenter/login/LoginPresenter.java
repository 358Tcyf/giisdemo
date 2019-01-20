package simple.project.giisdemo.mvp.presenter.login;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.mvp.model.login.LoginModel;
import simple.project.giisdemo.mvp.view.login.LoginView;

import static simple.project.giisdemo.helper.constant.GlobalField.GET_USER_PIC;
import static simple.project.giisdemo.helper.utils.FileUtil.saveImageToGallery;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:50
 * @describe
 */
public class LoginPresenter extends BasePresenter<LoginView, LoginModel> {

    /*用户登录，获取到账号信息*/
    public void login(String phone, String password) {
        getModel().login(phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
//                getView().setUserPic(phone);
                /*获取到账号信息之后，加载用户相片*/

                String uri = GET_USER_PIC + phone;
                saveImageToGallery(getView().getCurContext(), uri);
                getView().toMain();
            }

            @Override
            public void onFailed(String errorMsg) {
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
