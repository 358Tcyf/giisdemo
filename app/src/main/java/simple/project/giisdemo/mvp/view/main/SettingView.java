package simple.project.giisdemo.mvp.view.main;

import simple.project.giisdemo.base.BaseView;
import simple.project.giisdemo.data.bean.UserBean;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public interface SettingView extends BaseView {
    void setUserName(UserBean user);

    void toLogout();//退出登录

    void sysSet();

}
