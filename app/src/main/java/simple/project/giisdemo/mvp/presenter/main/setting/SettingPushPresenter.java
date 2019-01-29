package simple.project.giisdemo.mvp.presenter.main.setting;

import android.app.TimePickerDialog;
import android.os.Handler;
import android.view.View;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.entity.PushSetting;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.utils.DialogUtil;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.mvp.model.main.setting.SettingPushModel;
import simple.project.giisdemo.mvp.view.main.setting.SettingPushView;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.cycles;
import static simple.project.giisdemo.helper.constant.GlobalField.dateSet;
import static simple.project.giisdemo.helper.constant.GlobalField.weekSet;
import static simple.project.giisdemo.helper.utils.QMUIUtil.failTipDialog;
import static simple.project.giisdemo.helper.utils.QMUIUtil.successTipDialog;

/**
 * @author Created by ys
 * @date at 2019/1/10 1:57
 * @describe
 */
public class SettingPushPresenter extends BasePresenter<SettingPushView, SettingPushModel> {

    private PushSetting setting;

    public void initGroupListView(GroupListView groupListPushSetting) {

        DatabaseHelper helper = new DatabaseHelper(getView().getCurContext());
        getModel().init();


        setting = helper.getPushSetting((String) SPUtils.get(getView().getCurContext(), USER_PHONE, ""));
        QMUICommonListItemView pushSwitch = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_switch));
        pushSwitch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushSwitch.getSwitch().setChecked(setting.isPushSwitch());
        pushSwitch.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            //TODO 推送设置
            setting.setPushSwitch(isChecked);
        });
        QMUICommonListItemView pushVoice = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_voice));
        pushVoice.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVoice.getSwitch().setChecked(setting.isVoice());
        pushVoice.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            //TODO 推送设置
            setting.setVoice(isChecked);
        });
        QMUICommonListItemView pushVibrate = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_vibrate));
        pushVibrate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVibrate.getSwitch().setChecked(setting.isVibrate());
        pushVibrate.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            //TODO 推送设置
            setting.setVibrate(isChecked);
        });
        QMUICommonListItemView pushFloat = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_float));
        pushFloat.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushFloat.getSwitch().setChecked(setting.isFloatWindow());
        pushFloat.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
            //TODO 推送设置
            setting.setFloatWindow(isChecked);
        });
        QMUICommonListItemView pushCycle = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_cycle));
        QMUICommonListItemView pushDate = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_date));
        QMUICommonListItemView pushTime = groupListPushSetting.createItemView(getView().getCurContext().getResources().getString(R.string.push_time));


        pushCycle.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushCycle.setDetailText(cycles[1]);
        pushDate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushDate.setDetailText("周一");
        pushTime.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushTime.setDetailText("12:00");

        View.OnClickListener onClickListener = v -> {
            final String[] items;
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                switch ((String) text) {
                    case "推送周期":
                        items = cycles;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                        {
                            switch (which) {
                                case 0:
                                    //TODO 隐藏推送日期
                                    break;
                                default:
                                    //TODO 显示推送日期
                            }
                            dialog.dismiss();

                        }).show();
                        break;
                    case "推送日期":
                        if (cycles[1].contentEquals(pushCycle.getDetailText()))
                            items = weekSet;
                        else
                            items = dateSet;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                                dialog.dismiss()).show();
                        break;
                    case "推送时间":
                        //时间选择器
                        OnTimeSelectListener listener = (date, v1) -> {

                        };

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getView().getCurContext(),
                                android.R.style.Theme_DeviceDefault_Light_Dialog,
                                (view, hourOfDay, minute) -> {

                                },
                                0,
                                0,
                                false);

                        timePickerDialog.show();
//                        TimePickerView timePicker = new TimePickerBuilder(getView().getCurContext(), listener)
//                                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
//                                .setSubmitColor(getView().getCurContext().getResources().getColor(R.color.colorPrimary))//确定按钮文字颜色
//                                .setCancelColor(getView().getCurContext().getResources().getColor(R.color.colorPrimaryText))//取消按钮文字颜色
//                                .isDialog(true)//是否显示为对话框样式
//                                .build();
//                        timePicker.show();

                        break;
                }
            }
        };

        GroupListView.newSection(getView().getCurContext())
                .setDescription(getView().getCurContext().getResources().getString(R.string.push_section_1))
                .addItemView(pushSwitch, onClickListener)
                .addTo(groupListPushSetting);

        GroupListView.newSection(getView().getCurContext())
                .addItemView(pushVoice, onClickListener)
                .addItemView(pushVibrate, onClickListener)
                .addItemView(pushFloat, onClickListener)
                .addTo(groupListPushSetting);
        GroupListView.newSection(getView().getCurContext())
                .addItemView(pushCycle, onClickListener)
                .addItemView(pushDate, onClickListener)
                .addItemView(pushTime, onClickListener)
                .addTo(groupListPushSetting);

    }

    public void saveSetting() {
        QMUITipDialog loading = DialogUtil.showTipDialog(getView().getCurContext(), QMUITipDialog.Builder.ICON_TYPE_LOADING, "保存中", false);
        getModel().updateSetting(setting, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                new Handler().postDelayed(() -> {
                    loading.dismiss();
                    successTipDialog(getView().getCurContext(), "保存成功");
                    getView().toBack();
                }, 1000);
            }

            @Override
            public void onFailed(String errorMsg) {
                getModel().save(setting);
                new Handler().postDelayed(() -> {
                    loading.dismiss();
                    failTipDialog(getView().getCurContext(), errorMsg);
                }, 1000);
                getView().toBack();
            }
        });
    }
}
