package simple.project.giisdemo.mvp.model.main;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.HttpFeedBackUtil;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.utils.SPUtils;

import static android.text.TextUtils.isEmpty;
import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PWD;
import static simple.project.giisdemo.helper.constant.HttpConstant.PORT;
import static simple.project.giisdemo.helper.constant.HttpConstant.URL;

/**
 * @author Created by ys
 * @date at 2019/1/17 16:58
 * @describe
 */
public class UpdateUserInfoModel extends BaseModel {
    @Override
    public void init() {

    }

    public String getOldPhone() {
        return (String) SPUtils.get(getContext(), USER_PHONE, "");
    }


    public String getOldPassword() {
        return (String) SPUtils.get(getContext(), USER_PWD, "");
    }

    public void updateInfo(String oldPhone, String newPhone, String password, OnHttpCallBack<RetResult> callBack) {
        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .updateInfo(oldPhone, newPhone, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RetResult retResult) {
                        HttpFeedBackUtil.handleRetResult(retResult, callBack);
                        if (retResult.getCode() == RetResult.RetCode.SUCCESS.code) {
                            //TODO 把这个人的数据存入本地数据库
                            Log.d(DEBUG, "UpdateUserInfoModel: newPhone is " + newPhone + ";password is " + password);
                            if (!isEmpty(newPhone)) {
                                SPUtils.put(getContext(), USER_PHONE, newPhone);
                            }
                            if (!isEmpty(password)) {
                                SPUtils.put(getContext(), USER_PWD, password);
                            }
                            Log.d(DEBUG, "GOOD JOB");
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
