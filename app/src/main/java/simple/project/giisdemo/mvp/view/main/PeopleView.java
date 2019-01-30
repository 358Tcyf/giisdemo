package simple.project.giisdemo.mvp.view.main;

import java.util.ArrayList;
import java.util.Map;

import simple.project.giisdemo.base.BaseView;
import simple.project.giisdemo.data.bean.UserBean;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public interface PeopleView extends BaseView {

    void toSearch();

    void setUserList(Map<String, Object> result);
}
