package simple.project.giisdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.SettingSystemPresent;
import simple.project.giisdemo.mvp.view.SettingSystemView;

import static simple.project.giisdemo.helper.constant.GlobalField.SYS_LANG;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

/**
 * @author Created by ys
 * @date at 2019/1/13 2:39
 * @describe
 */
public class SettingSystemFragment extends BaseFragment<SettingSystemPresent> implements SettingSystemView {
    @BindView(R.id.groupList_system)
    GroupListView groupListSystem;

    @Override
    protected SettingSystemPresent createPresenter() {
        return new SettingSystemPresent();
    }

    private Unbinder unbinder;

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_system, null);
        unbinder = ButterKnife.bind(this, view);
        initMenuList();
        return view;
    }

    private void initMenuList() {
        QMUICommonListItemView systemLanguage = groupListSystem.createItemView(getResources().getString(R.string.system_language));
        systemLanguage.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        systemLanguage.setDetailText((CharSequence) SPUtils.get(getBaseFragmentActivity(), SYS_LANG, "跟随系统"));
        QMUICommonListItemView systemFontSize = groupListSystem.createItemView(getResources().getString(R.string.system_font_size));
        systemFontSize.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView systemSyncData = groupListSystem.createItemView(getResources().getString(R.string.system_sync_data));
        systemSyncData.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView systemCleanData = groupListSystem.createItemView(getResources().getString(R.string.system_clean_data));
        systemCleanData.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        View.OnClickListener onClickListener = v -> {
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                ToastUtil.showShort(getBaseFragmentActivity(), text + " is Clicked");
                switch ((String) text) {
                    case "多语言":
                        break;
                    case "同步数据":
                        break;
                    case "清理缓存":
                        break;
                }
            }
        };
        GroupListView.newSection(getBaseFragmentActivity())
                .addItemView(systemLanguage, onClickListener)
                .addItemView(systemFontSize, onClickListener)
                .addItemView(systemSyncData, onClickListener)
                .addItemView(systemCleanData, onClickListener)
                .addTo(groupListSystem);
    }

}
