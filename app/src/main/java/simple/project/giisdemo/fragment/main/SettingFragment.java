package simple.project.giisdemo.fragment.main;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog.CheckBoxMessageDialogBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.LoginActivity;
import simple.project.giisdemo.activity.SettingActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.mvp.presenter.main.SettingPresenter;
import simple.project.giisdemo.mvp.view.main.SettingView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initTitle;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingView {

    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_phone)
    TextView userPhone;
    @BindView(R.id.user_branch)
    TextView userBranch;
    @BindView(R.id.user_no)
    TextView userNo;
    @BindView(R.id.groupList_push_switch)
    GroupListView groupListInfo;
    @BindView(R.id.user_pic)
    QMUIRadiusImageView userPic;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, view);
        initTitle(mTopBar, getBaseFragmentActivity(), R.string.setting);
        EventBus.getDefault().register(this);
        getPresenter().setUserName();
        loadPic();
        getPresenter().initGroupListView(groupListInfo);
        return view;
    }

    private void loadPic() {
        Uri picUri = getUserPicPathUri(getBaseFragmentActivity());
        if (null != picUri)
            userPic.setImageURI(picUri);
        else
            userPic.setImageResource(R.mipmap.user_pic_blue);
    }


    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void setUserName(UserBean user) {
        userName.setText(user.getName());
        userPhone.setText(user.getPhone());
        userBranch.setText("XX部门");
        userNo.setText(user.getUid());

    }

    @Override
    public void toLogout() {
        //TODO 跳转到登入界面
        Intent intent = new Intent(getBaseFragmentActivity(), LoginActivity.class);
        startActivity(intent);
        getBaseFragmentActivity().finish();
    }

    @Override
    public void sysSet() {
        Intent intent = new Intent(getCurContext(), SettingActivity.class);
        startActivity(intent);
        getBaseFragmentActivity().finish();
    }


    @OnClick({R.id.user_idcard, R.id.logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_idcard:
                break;
            case R.id.logout_btn:
                logoutDialog();
                break;
        }
    }


    private void logoutDialog() {
        CheckBoxMessageDialogBuilder logoutDailog = new CheckBoxMessageDialogBuilder(getBaseFragmentActivity());
        logoutDailog.setTitle("退出后是否删除账号信息?")
                .setMessage("删除账号信息")
                .setChecked(false)
                .addAction("取消", (dialog, index) -> dialog.dismiss())
                .addAction("退出", (dialog, index) -> {
                    getPresenter().toLogout(logoutDailog.isChecked());
                    dialog.dismiss();
                }).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        if (messageEvent.getMessage().containsKey("userPic"))
            userPic.setImageURI((Uri) messageEvent.getMessage().get("userPic"));
        if (messageEvent.getMessage().containsKey("phone")) {
            userPhone.setText((String) messageEvent.getMessage().get("phone"));
            Log.d(DEBUG, (String) messageEvent.getMessage().get("phone"));

        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
