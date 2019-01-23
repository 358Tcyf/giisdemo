package simple.project.giisdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.util.QMUIResHelper;

import java.util.ArrayList;
import java.util.Date;

import simple.project.giisdemo.R;
import simple.project.giisdemo.data.entity.Push;

import static simple.project.giisdemo.helper.constant.DateConstant.format1;
import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.utils.DateUtil.dataToStr;

/**
 * @author Created by ys
 * @date at 2019/1/20 16:57
 * @describe
 */
public class PushAdapter extends BaseQuickAdapter<Push, BaseViewHolder> {

    private Context mContext;

    public PushAdapter(Activity mActivity, ArrayList<Push> data) {
        super(R.layout.item_push_daily, data);
        this.mContext = mActivity;
    }

    @Override
    protected void convert(BaseViewHolder helper, Push item) {
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
        helper.setText(R.id.push_title, item.getTitle());
        helper.setText(R.id.push_text, R.string.detail);
        String date = dataToStr(mContext, new Date(item.getCreateTime() * 1000), format1);
        helper.setText(R.id.push_date, date);

    }


}
