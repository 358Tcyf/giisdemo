package simple.project.giisdemo.mvp.model.main.setting;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.data.entity.PushSetting;
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
 * @date at 2019/1/10 1:57
 * @describe
 */
public class SettingPushModel extends BaseModel {
    private DatabaseHelper helper;

    @Override
    public void init() {
        helper = new DatabaseHelper(getContext());
    }


    public void save(PushSetting setting) {
        helper = new DatabaseHelper(getContext());
        helper.updatePushSetting(setting);
    }

    public void updateSetting(PushSetting setting, OnHttpCallBack<RetResult> callBack) {
        Log.d(DEBUG, setting.toString());
        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .updateSetting(setting.getUserPhone(), setting.isPushSwitch(), setting.isVoice(), setting.isVibrate(), setting.isFloatWindow())
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
                            helper = new DatabaseHelper(getContext());
                            helper.updatePushSetting(setting);
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
