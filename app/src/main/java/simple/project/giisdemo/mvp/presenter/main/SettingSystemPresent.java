package simple.project.giisdemo.mvp.presenter.main;

import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.mvp.model.main.SettingSystemModel;
import simple.project.giisdemo.mvp.view.main.SettingSystemView;

import static simple.project.giisdemo.helper.constant.GlobalField.DEFAULT;
import static simple.project.giisdemo.helper.constant.GlobalField.INDIGO;
import static simple.project.giisdemo.helper.constant.GlobalField.LIGHT_BLUE;
import static simple.project.giisdemo.helper.constant.GlobalField.SYS_LANG;
import static simple.project.giisdemo.helper.constant.GlobalField.TEAL;
import static simple.project.giisdemo.helper.constant.GlobalField.languages;
import static simple.project.giisdemo.helper.constant.GlobalField.themes;

/**
 * @author Created by ys
 * @date at 2019/1/13 2:40
 * @describe
 */
public class SettingSystemPresent extends BasePresenter<SettingSystemView, SettingSystemModel> {
    public void initGroupListView(GroupListView groupListSystem) {
        QMUICommonListItemView systemLanguage = groupListSystem.createItemView(getView().getCurContext().getResources().getString(R.string.system_language));
        systemLanguage.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        systemLanguage.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), SYS_LANG, "跟随系统"));
        QMUICommonListItemView systemTheme = groupListSystem.createItemView(getView().getCurContext().getResources().getString(R.string.system_theme));
        systemTheme.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        systemTheme.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), SYS_LANG, "默认"));
        QMUICommonListItemView systemFontSize = groupListSystem.createItemView(getView().getCurContext().getResources().getString(R.string.system_font_size));
        systemFontSize.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView systemSyncData = groupListSystem.createItemView(getView().getCurContext().getResources().getString(R.string.system_sync_data));
        systemSyncData.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView systemCleanData = groupListSystem.createItemView(getView().getCurContext().getResources().getString(R.string.system_clean_data));
        systemCleanData.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        View.OnClickListener onClickListener = v -> {
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                String[] items;
                switch ((String) text) {
                    case "多语言":
                        items = languages;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                                dialog.dismiss()).show();
                        break;
                    case "颜色主题":
                        items = themes;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                        {
                           getView().setTheme(which);
                            dialog.dismiss();
                        }).show();
                        break;
                    case "同步数据":
                        break;
                    case "清理缓存":
                        break;
                }
            }
        };
        GroupListView.newSection(getView().getCurContext())
                .addItemView(systemLanguage, onClickListener)
                .addItemView(systemTheme, onClickListener)
                .addItemView(systemFontSize, onClickListener)
                .addItemView(systemSyncData, onClickListener)
                .addItemView(systemCleanData, onClickListener)
                .addTo(groupListSystem);
    }

}
