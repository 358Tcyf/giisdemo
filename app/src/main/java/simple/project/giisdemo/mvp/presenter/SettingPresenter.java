package simple.project.giisdemo.mvp.presenter;

import android.util.Log;
import android.widget.ArrayAdapter;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.mvp.model.PushModel;
import simple.project.giisdemo.mvp.model.SettingModel;
import simple.project.giisdemo.mvp.view.PushView;
import simple.project.giisdemo.mvp.view.SettingView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class SettingPresenter extends BasePresenter<SettingView, SettingModel> {

    public void setUserName() {
        getView().setUserName(getModel().getUserName());
    }


    public void toLogout(boolean isCheck) {
        Log.d(DEBUG, "logout check is " + isCheck);
        if (isCheck)
            getModel().toLogout();
        getView().toLogout();
    }
}
