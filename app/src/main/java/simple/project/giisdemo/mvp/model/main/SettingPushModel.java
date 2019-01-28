package simple.project.giisdemo.mvp.model.main;

import android.os.Handler;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.entity.PushSetting;
import simple.project.giisdemo.helper.utils.DialogUtil;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.utils.QMUIUtil.loadingTipDialog;
import static simple.project.giisdemo.helper.utils.QMUIUtil.successTipDialog;

/**
 * @author Created by ys
 * @date at 2019/1/10 1:57
 * @describe
 */
public class SettingPushModel extends BaseModel {
    private DatabaseHelper helper;

    @Override
    public void init() {
        helper = new DatabaseHelper(getContext());
        helper.insertPushSetting((String) SPUtils.get(getContext(), USER_PHONE, ""));
    }

    public void save(PushSetting setting) {
        helper = new DatabaseHelper(getContext());
        helper.updatePushSetting(setting);
    }
}
