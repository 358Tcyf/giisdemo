package simple.project.giisdemo.mvp.model;

import com.alibaba.fastjson.JSON;

import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.helper.constant.GlobalField;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_ALIAS;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PWD;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_TAGS;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:01
 * @describe
 */
public class SettingModel extends BaseModel {

    @Override
    public void init() {

    }

    public UserBean getUserName() {
        UserBean user = new UserBean();
        user.setName((String) SPUtils.get(getContext(), GlobalField.USER_NAME, "张三"));
        user.setAlias((String) SPUtils.get(getContext(), GlobalField.USER_ALIAS, ""));
        user.setPhone((String) SPUtils.get(getContext(), GlobalField.USER_PHONE, ""));
        return user;
    }

    public void toLogout() {
        SPUtils.put(getContext(), USER_PHONE, "");
        SPUtils.put(getContext(), USER_PWD, "");
        SPUtils.put(getContext(), USER_NAME, "");
        SPUtils.put(getContext(), USER_ALIAS, "");
        SPUtils.put(getContext(), USER_TAGS, "");
    }


}
