package simple.project.giisdemo.mvp.view.main;

import simple.project.giisdemo.base.BaseView;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public interface PushView extends BaseView {

    void toSearch();

    void setCount(int count);
}
