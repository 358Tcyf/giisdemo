package simple.project.giisdemo.mvp.model.main;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.data.entity.PushSetting;
import simple.project.giisdemo.helper.constant.GlobalField;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PWD;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_TAGS;
import static simple.project.giisdemo.helper.constant.HttpConstant.PORT;
import static simple.project.giisdemo.helper.constant.HttpConstant.URL;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:01
 * @describe
 */
public class SettingModel extends BaseModel {
    private DatabaseHelper helper;

    @Override
    public void init() {
        helper = new DatabaseHelper(getContext());

        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .downloadSetting((String) SPUtils.get(getContext(), USER_PHONE, ""))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RetResult retResult) {
                        PushSetting setting = JSON.parseObject(JSON.toJSONString(retResult.getData()), PushSetting.class);
                        helper.downloadPushSetting((String) SPUtils.get(getContext(), USER_PHONE, ""), setting);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public UserBean getUserName() {
        UserBean user = new UserBean();
        user.setName((String) SPUtils.get(getContext(), GlobalField.USER_NAME, "张三"));
        user.setUid((String) SPUtils.get(getContext(), GlobalField.USER_UID, ""));
        user.setPhone((String) SPUtils.get(getContext(), GlobalField.USER_PHONE, ""));
        return user;
    }

    public void toLogout() {
        SPUtils.put(getContext(), USER_PHONE, "");
        SPUtils.put(getContext(), USER_PWD, "");
        SPUtils.put(getContext(), USER_NAME, "");
        SPUtils.put(getContext(), USER_UID, "");
        SPUtils.put(getContext(), USER_TAGS, "");
    }


}
