package simple.project.giisdemo.mvp.presenter.main;

import java.util.Map;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.mvp.model.main.PeopleModel;
import simple.project.giisdemo.mvp.view.main.PeopleView;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PeoplePresenter extends BasePresenter<PeopleView, PeopleModel> {
    private Map<String, Object> result;

    public void loadUserList() {
        getModel().downloadUserList(new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                result = (Map<String, Object>) retResult.getData();

                getView().setUserList(result);
            }

            @Override
            public void onFailed(String errorMsg) {

            }
        });
    }
}
