package simple.project.giisdemo.mvp.model.main;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.HttpFeedBackUtil;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.HttpConstant.PORT;
import static simple.project.giisdemo.helper.constant.HttpConstant.URL;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:01
 * @describe
 */
public class PeopleModel extends BaseModel {
    @Override
    public void init() {

    }



    public void downloadUserList(OnHttpCallBack<RetResult> callBack) {
        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .loadList((String) SPUtils.get(getContext(), USER_PHONE, ""))
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
