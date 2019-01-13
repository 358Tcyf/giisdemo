package simple.project.giisdemo.fragment.main.setting;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.mvp.presenter.main.SettingUserInfoPresenter;
import simple.project.giisdemo.mvp.view.main.SettingUserInfoView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initRightTextButtoninitial;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:15
 * @describe
 */
public class SettingUserInfoFragment extends BaseFragment<SettingUserInfoPresenter> implements SettingUserInfoView {
    @BindView(R.id.groupList_userInfo)
    GroupListView groupListUserInfo;
    @BindView(R.id.groupList_userAccount)
    GroupListView groupListUserAccount;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;

    @Override
    protected SettingUserInfoPresenter createPresenter() {
        return new SettingUserInfoPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_userinfo, null);
        unbinder = ButterKnife.bind(this, view);
        initTopBar();
        getPresenter().initGroupListView(groupListUserInfo, groupListUserAccount);
        return view;
    }

    private void initTopBar() {
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.set_info);
        initRightTextButtoninitial(mTopBar, getBaseFragmentActivity(), R.string.save, R.id.save)
                .setOnClickListener(v -> {
                    getPresenter().save();
                });
    }


    @Override
    public void toFinish() {
        popBackStack();
    }

}
