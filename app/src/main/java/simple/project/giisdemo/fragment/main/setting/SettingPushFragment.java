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

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;

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
    private Unbinder unbinder;


    @Override
    protected SettingPushPresenter createPresenter() {
        return new SettingPushPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_grouplist, null);
        unbinder = ButterKnife.bind(this, view);
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.set_push);
        getPresenter().initGroupListView(groupListPushSetting);
        return view;
    }

}
