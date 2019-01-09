package simple.project.giisdemo.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog.CheckBoxMessageDialogBuilder;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.LoginActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.SettingPresenter;
import simple.project.giisdemo.mvp.view.SettingView;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingView {


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
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, view);
        getPresenter().setUserName();
        initMenuList();
        return view;
    }


    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    private final int CHECK_UPDATE = 0;
    private final int CALLBACK = 1;

    private void initMenuList() {
        QMUICommonListItemView setInfo = groupListInfo.createItemView(getResources().getString(R.string.set_info));
        QMUICommonListItemView setPush = groupListInfo.createItemView(getResources().getString(R.string.set_push));
        QMUICommonListItemView setSystem = groupListInfo.createItemView(getResources().getString(R.string.set_system));
        QMUICommonListItemView appAbout = groupListInfo.createItemView(getResources().getString(R.string.app_about));

        setInfo.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        setPush.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        setSystem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        GroupListView.newSection(getBaseFragmentActivity())
                .addItemView(setInfo, null)
                .addItemView(setPush, v -> {
                    startFragment(new SettingPushFragment());
                })
                .addItemView(setSystem, null)
                .addItemView(appAbout, v -> {
                    new QMUIBottomSheet.BottomListSheetBuilder(getActivity())
                            .addItem(getResources().getString(R.string.check_update))
                            .addItem(getResources().getString(R.string.callback))
                            .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                                dialog.dismiss();
                                switch (position) {
                                    case CHECK_UPDATE:
                                        ToastUtil.showShort(getBaseFragmentActivity(), getResources().getString(R.string.check_update));
                                        break;
                                    case CALLBACK:
                                        ToastUtil.showShort(getBaseFragmentActivity(), getResources().getString(R.string.callback));
                                        break;
                                    default:
                                }
                            })
                            .build()
                            .show();
                })
                .addTo(groupListInfo);
    }

    @Override
    public void setUserName(UserBean user) {
        userName.setText(user.getName());
        userPhone.setText(user.getPhone());
        userBranch.setText("XX部门");
        userNo.setText(user.getAlias());

    }

    @Override
    public void toLogout() {
        //TODO 跳转到登入界面
        Intent intent = new Intent(getBaseFragmentActivity(), LoginActivity.class);
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

}
