package simple.project.giisdemo.fragment.main.setting;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.mvp.presenter.main.SettingPushPresenter;
import simple.project.giisdemo.mvp.view.main.SettingPushView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackName;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackNameAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initRightTextButton;
import static simple.project.giisdemo.helper.utils.QMUIUtil.successTipDialog;

/**
 * @author Created by ys
 * @date at 2019/1/10 1:56
 * @describe
 */
public class SettingPushFragment extends BaseFragment<SettingPushPresenter> implements SettingPushView {

    @BindView(R.id.groupList_1)
    GroupListView groupListPushSetting;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;


    @Override
    protected SettingPushPresenter createPresenter() {
        return new SettingPushPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_grouplist, null);
        ButterKnife.bind(this, view);
        initTopBar();
        getPresenter().initGroupListView(groupListPushSetting);
        return view;
    }

    private void initTopBar() {
        initBackNameAndTitle(mTopBar, getBaseFragmentActivity(), R.string.back, R.string.set_push);
        initRightTextButton(mTopBar, getBaseFragmentActivity(), R.string.save, R.id.save)
                .setOnClickListener(v -> {
                    getPresenter().saveSetting();
                    successTipDialog(getBaseFragmentActivity(), "保存成功");
                });
    }
}
