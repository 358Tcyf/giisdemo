package simple.project.giisdemo.fragment.main.setting;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.ShowImageActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.mvp.presenter.main.SettingUserInfoPresenter;
import simple.project.giisdemo.mvp.view.main.SettingUserInfoView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initRightTextButtoninitial;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.loginError;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:15
 * @describe
 */
public class SettingUserInfoFragment extends BaseFragment<SettingUserInfoPresenter> implements SettingUserInfoView {
    @BindView(R.id.groupList_1)
    GroupListView groupListUserInfo;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;

    @Override
    protected SettingUserInfoPresenter createPresenter() {
        return new SettingUserInfoPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_grouplist, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initTopBar();
        getPresenter().initGroupListView(groupListUserInfo);
        return view;
    }

    private void initTopBar() {
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.set_info);
        initRightTextButtoninitial(mTopBar, getBaseFragmentActivity(), R.string.save, R.id.save)
                .setOnClickListener(v -> {
                    getPresenter().savePic();
                });
    }


    @Override
    public void toFinish() {
        popBackStack();
    }

    @Override
    public void showImage() {
        Uri picUri = getUserPicPathUri(getBaseFragmentActivity());
        Intent intent = new Intent(getBaseFragmentActivity(), ShowImageActivity.class);
        intent.putExtra("image", picUri.toString());
        startActivity(intent);
    }

    @Override
    public void updatePhone() {
        startFragment(new UpdateUserInfoFragment(1));
    }

    @Override
    public void updatePasswd() {
        startFragment(new UpdateUserInfoFragment(3));
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        loginError(getBaseFragmentActivity(), errorMsg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {

        if (messageEvent.getMessage().containsKey("phone"))
            getPresenter().showPhone((String) messageEvent.getMessage().get("phone"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
