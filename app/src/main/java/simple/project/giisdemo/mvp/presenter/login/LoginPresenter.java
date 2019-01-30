package simple.project.giisdemo.mvp.presenter.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.mvp.model.login.LoginModel;
import simple.project.giisdemo.mvp.view.login.LoginView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.HttpConstant.GET_USER_PIC;
import static simple.project.giisdemo.helper.utils.FileUtil.saveImageToGallery;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:50
 */
public class LoginPresenter extends BasePresenter<LoginView, LoginModel> {

    /*用户登录，获取到账号信息*/
    public void login(String phone, String password) {
        getModel().login(phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                /*获取到账号信息之后，加载用户相片*/
                UserBean userBean = JSON.parseObject(JSON.toJSONString(retResult.getData()), UserBean.class);
                Log.d(DEBUG, userBean.getUid());
                String uri = GET_USER_PIC + userBean.getUid();
                saveImageToGallery(getView().getCurContext(), uri, userBean.getUid());
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
        getView().setPassword(getModel().getPassword());
    }

}
