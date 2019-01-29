package simple.project.giisdemo.mvp.presenter.main;

import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.fragment.main.setting.SettingPushFragment;
import simple.project.giisdemo.fragment.main.setting.SettingUserInfoFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.mvp.model.main.SettingModel;
import simple.project.giisdemo.mvp.view.main.SettingView;

import static simple.project.giisdemo.helper.constant.GlobalField.CALLBACK;
import static simple.project.giisdemo.helper.constant.GlobalField.CHECK_UPDATE;
import static simple.project.giisdemo.helper.constant.HttpConstant.GET_USER_PIC;
import static simple.project.giisdemo.helper.utils.FileUtil.downloadApk;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class SettingPresenter extends BasePresenter<SettingView, SettingModel> {

    public void setUserName() {
        getView().setUserName(getModel().getUserName());
    }


    public void toLogout(boolean isCheck) {
        if (isCheck)
            getModel().toLogout();
        getView().toLogout();

    }

    public void initGroupListView(GroupListView groupListInfo) {
        getModel().init();
        QMUICommonListItemView setInfo = groupListInfo.createItemView(getView().getCurContext().getResources().getString(R.string.set_info));
        QMUICommonListItemView setPush = groupListInfo.createItemView(getView().getCurContext().getResources().getString(R.string.set_push));
        QMUICommonListItemView setSystem = groupListInfo.createItemView(getView().getCurContext().getResources().getString(R.string.set_system));
        QMUICommonListItemView appAbout = groupListInfo.createItemView(getView().getCurContext().getResources().getString(R.string.app_about));

        setInfo.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        setPush.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        setSystem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        GroupListView.newSection(getView().getCurContext())
                .addItemView(setInfo, onClickListener)
                .addItemView(setPush, onClickListener)
                .addItemView(setSystem, onClickListener)
                .addItemView(appAbout, onClickListener)
                .addTo(groupListInfo);
    }

    View.OnClickListener onClickListener = v -> {
        if (v instanceof QMUICommonListItemView) {
            CharSequence text = ((QMUICommonListItemView) v).getText();
            switch ((String) text) {
                case "信息设置":
                    getView().getCurContext().startFragment(new SettingUserInfoFragment());
                    break;
                case "推送设置":
                    getView().getCurContext().startFragment(new SettingPushFragment());
                    break;
                case "系统设置":
                    getView().sysSet();
                    break;
                case "关于应用":
                    new QMUIBottomSheet.BottomListSheetBuilder(getView().getCurContext())
                            .addItem(getView().getCurContext().getResources().getString(R.string.check_update))
                            .addItem(getView().getCurContext().getResources().getString(R.string.callback))
                            .addItem(getView().getCurContext().getResources().getString(R.string.cancel))
                            .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                                dialog.dismiss();
                                switch (position) {
                                    case CHECK_UPDATE:

                                        downloadApk(getView().getCurContext());
                                        break;
                                    case CALLBACK:
                                        break;
                                    default:
                                }
                            })
                            .build()
                            .show();
                    break;
            }
        }
    };


}
