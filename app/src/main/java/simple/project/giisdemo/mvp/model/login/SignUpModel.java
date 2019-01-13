package simple.project.giisdemo.mvp.model.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.HttpFeedBackUtil;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.constant.GlobalField;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.PORT;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PWD;

/**
 * @author Created by ys
 * @date at 2019/1/8 15:30
 * @describe
 */
public class SignUpModel extends BaseModel {
    @Override
    public void init() {

    }

    public void signUp(String name, String phone, String password, OnHttpCallBack<RetResult> callBack) {
        Log.d(DEBUG, "Model: name is " + name + " phone is " + phone + " password is " + password);

        RetrofitUtils.newInstance(GlobalField.URL + PORT + "/")
                .create(HttpContract.class)
                .register(name, phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RetResult<UserBean> retResult) {
                        HttpFeedBackUtil.handleRetResult(retResult, callBack);
                        if (retResult.getCode() == RetResult.RetCode.SUCCESS.code) {
                            //TODO 把这个人的账号密码存到登陆界面
                            UserBean userBean = JSON.parseObject(JSON.toJSONString(retResult.getData()), UserBean.class);
                            SPUtils.put(getContext(), USER_PHONE, phone);
                            SPUtils.put(getContext(), USER_PWD, password);
                            Log.d(DEBUG, "goodjob");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpFeedBackUtil.handleException(e, callBack);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
