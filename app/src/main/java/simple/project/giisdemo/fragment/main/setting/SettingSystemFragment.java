package simple.project.giisdemo.fragment.main.setting;

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
import simple.project.giisdemo.mvp.presenter.main.SettingSystemPresent;
import simple.project.giisdemo.mvp.view.main.SettingSystemView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEFAULT;
import static simple.project.giisdemo.helper.constant.GlobalField.INDIGO;
import static simple.project.giisdemo.helper.constant.GlobalField.LIGHT_BLUE;
import static simple.project.giisdemo.helper.constant.GlobalField.TEAL;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;

/**
 * @author Created by ys
 * @date at 2019/1/13 2:39
 * @describe
 */
public class SettingSystemFragment extends BaseFragment<SettingSystemPresent> implements SettingSystemView {
    @BindView(R.id.groupList_1)
    GroupListView groupListSystem;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;

    @Override
    protected SettingSystemPresent createPresenter() {
        return new SettingSystemPresent();
    }

    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_grouplist, null);
        unbinder = ButterKnife.bind(this, view);
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.set_system);
        getPresenter().initGroupListView(groupListSystem);
        return view;
    }


    @Override
    public void setTheme(int mTheme) {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("theme", mTheme);
        EventBus.getDefault().post(new MessageEvent(message));
    }
}
