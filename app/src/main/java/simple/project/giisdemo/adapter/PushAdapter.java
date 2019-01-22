package simple.project.giisdemo.adapter;

import android.app.Activity;
import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.util.QMUIResHelper;

import java.util.ArrayList;

import simple.project.giisdemo.R;
import simple.project.giisdemo.testThing.Status;

/**
 * @author Created by ys
 * @date at 2019/1/20 16:57
 * @describe
 */
public class PushAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    Context mContext;

    public PushAdapter(Activity mActivity, ArrayList<Status> data) {
        super(R.layout.item_push_daily, data);
        this.mContext = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() %
                2) {
            case 0:
                helper.setBackgroundColor(R.id.top, QMUIResHelper.getAttrColor(mContext, R.attr.colorPrimaryLight));
                helper.setImageResource(R.id.push_pic, R.drawable.ic_push_item1);
                break;
            case 1:
                helper.setBackgroundColor(R.id.top, QMUIResHelper.getAttrColor(mContext, R.attr.colorPrimaryDark));
                helper.setImageResource(R.id.push_pic, R.drawable.ic_push_item2);
                break;
        }
        helper.setText(R.id.push_title, item.getUserName());
        helper.setText(R.id.push_text, item.getText());
        helper.setText(R.id.push_date, item.getCreatedAt());

    }


}
