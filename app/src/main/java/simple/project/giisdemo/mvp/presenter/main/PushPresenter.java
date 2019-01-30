package simple.project.giisdemo.mvp.presenter.main;

import java.util.ArrayList;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.data.DatabaseHelper;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.mvp.model.main.PushModel;
import simple.project.giisdemo.mvp.view.main.PushView;

import static simple.project.giisdemo.helper.constant.DateConstant.format1;
import static simple.project.giisdemo.helper.utils.DateUtil.strToLong;

/**
 * @author Created by ys
 * @date at 2019/1/8 19:00
 * @describe
 */
public class PushPresenter extends BasePresenter<PushView, PushModel> {

    public ArrayList<Push> setDataList() {
        getModel().init();
        DatabaseHelper helper = new DatabaseHelper(getView().getCurContext());
        String[] titles = {"销售报告", "生产报告"};
        long[] time = {strToLong("2018-12-30 20:00:00", format1)
                , strToLong("2019-01-20 20:00:00", format1)};
        ArrayList<Push> list = new ArrayList<>();
        for (int pos = 0; pos < titles.length * 3; pos++) {
            Push item = new Push();
            item.setTitle(titles[pos / 3]);
            item.setCreateTime(time[pos / 3]);
            if (helper.getAllPush().size() < 3)
                helper.insertPush(item);
            list.add(item);
        }
        return list;
    }

    public void getCount() {
        getView().setCount(getModel().getCount());
    }

    public void upSync() {
    }

    public void downSync() {
    }
}
