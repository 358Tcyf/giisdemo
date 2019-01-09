package simple.project.giisdemo.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog.CheckBoxMessageDialogBuilder;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

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
    @BindView(R.id.groupList_info)
    GroupListView groupListInfo;
    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, view);
        getPresenter().setUserName();
        return view;
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
                CheckBoxMessageDialogBuilder logoutDailog = new CheckBoxMessageDialogBuilder(getBaseFragmentActivity());
                logoutDailog.setTitle("退出后是否删除账号信息?")
                        .setMessage("删除账号信息")
                        .setChecked(true)
                        .addAction("取消", (dialog, index) -> dialog.dismiss())
                        .addAction("退出", (dialog, index) -> {
                            getPresenter().toLogout(logoutDailog.isChecked());
                            dialog.dismiss();
                        }).show();


                break;
        }
    }
}
