package simple.project.giisdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import simple.project.giisdemo.R;
import simple.project.giisdemo.bean.UserBean;
import simple.project.giisdemo.testThing.Status;

/**
 * @author Created by ys
 * @date at 2019/1/20 16:57
 * @describe
 */
public class PeopleAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {
    public PeopleAdapter(ArrayList<UserBean> data) {
        super(R.layout.item_people, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        switch (helper.getLayoutPosition() %
                2) {
            case 0:
                helper.setImageResource(R.id.user_pic, R.mipmap.user_pic);
                break;
            case 1:
                helper.setImageResource(R.id.user_pic, R.mipmap.user_pic_blue);
                break;
        }
        helper.setText(R.id.user_name, item.getName());
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setText(R.id.user_branch, "管理部门");
                break;
            case 1:
                helper.setText(R.id.user_branch, "生产部门");
                break;
            case 2:
                helper.setText(R.id.user_branch, "销售部门");
                break;
        }

    }


}
