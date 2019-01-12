package simple.project.giisdemo.fragment;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.SettingPushPresenter;
import simple.project.giisdemo.mvp.view.SettingPushView;

/**
 * @author Created by ys
 * @date at 2019/1/10 1:56
 * @describe
 */
public class SettingPushFragment extends BaseFragment<SettingPushPresenter> implements SettingPushView {

    @BindView(R.id.topbar)
    QMUITopBarLayout topbar;
    @BindView(R.id.groupList_push_switch)
    GroupListView groupListPushSwitch;
    @BindView(R.id.groupList_push_call)
    GroupListView groupListPushCall;
    @BindView(R.id.groupList_push_cycle)
    GroupListView groupListPushCycle;
    private Unbinder unbinder;


    @Override
    protected SettingPushPresenter createPresenter() {
        return new SettingPushPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_push, null);
        unbinder = ButterKnife.bind(this, view);
        initMenuList();
        return view;
    }

    private void initMenuList() {
        QMUICommonListItemView pushSwitch = groupListPushSwitch.createItemView(getResources().getString(R.string.push_switch));
        pushSwitch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushSwitch.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) ->
                ToastUtil.showShort(getBaseFragmentActivity(), "推送开关 = " + isChecked));
        QMUICommonListItemView pushVoice = groupListPushSwitch.createItemView(getResources().getString(R.string.push_voice));
        pushVoice.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVoice.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) ->
                ToastUtil.showShort(getBaseFragmentActivity(), "声音开关 = " + isChecked));
        QMUICommonListItemView pushVibrate = groupListPushSwitch.createItemView(getResources().getString(R.string.push_vibrate));
        pushVibrate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVibrate.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) ->
                ToastUtil.showShort(getBaseFragmentActivity(), "震动开关 = " + isChecked));
        QMUICommonListItemView pushFloat = groupListPushSwitch.createItemView(getResources().getString(R.string.push_float));
        pushFloat.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushFloat.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(), "悬浮窗开关 = " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        QMUICommonListItemView pushCycle = groupListPushSwitch.createItemView(getResources().getString(R.string.push_cycle));
        QMUICommonListItemView pushDate = groupListPushSwitch.createItemView(getResources().getString(R.string.push_date));
        QMUICommonListItemView pushTime = groupListPushSwitch.createItemView(getResources().getString(R.string.push_time));


        pushCycle.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushCycle.setDetailText("每周");
        pushDate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushDate.setDetailText("周一");
        pushTime.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushTime.setDetailText("12:00");


        View.OnClickListener onClickListener = v -> {
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                ToastUtil.showShort(getBaseFragmentActivity(), text + " is Clicked");
                switch ((String) text) {
                    case "推送周期":
                        final String[] items = new String[]{"每天", "每周", "每月"};
                        final int checkedIndex = 1;
                        new QMUIDialog.CheckableDialogBuilder(getActivity()).setCheckedIndex(checkedIndex).addItems(items, new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                ToastUtil.showShort(getBaseFragmentActivity(), "你选择了 " + items[which]);
                                dialog.dismiss();
                            }
                        }).show();
                        break;
                }
            }
        };

        GroupListView.newSection(getBaseFragmentActivity())
                .setDescription(getResources().getString(R.string.push_section_1))
                .addItemView(pushSwitch, onClickListener)
                .addTo(groupListPushSwitch);

        GroupListView.newSection(getBaseFragmentActivity())
                .addItemView(pushVoice, onClickListener)
                .addItemView(pushVibrate, onClickListener)
                .addItemView(pushFloat, onClickListener)
                .addTo(groupListPushCall);
        GroupListView.newSection(getBaseFragmentActivity())
                .addItemView(pushCycle, onClickListener)
                .addItemView(pushDate, onClickListener)
                .addItemView(pushTime, onClickListener)
                .addTo(groupListPushCycle);

    }

}
