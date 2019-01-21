package simple.project.giisdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import simple.project.giisdemo.R;
import simple.project.giisdemo.testThing.Status;

/**
 * @author Created by ys
 * @date at 2019/1/20 16:57
 * @describe
 */
public class SampleAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public SampleAdapter(ArrayList<Status> data) {
        super(R.layout.item_push_daily, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
//        switch (helper.getLayoutPosition() %
//                3) {
//            case 0:
//                helper.setImageResource(R.id.img, R.mipmap.animation_img1);
//                break;
//            case 1:
//                helper.setImageResource(R.id.img, R.mipmap.animation_img2);
//                break;
//            case 2:
//                helper.setImageResource(R.id.img, R.mipmap.animation_img3);
//                break;
//        }
//        helper.setText(R.id.tweetName, item.getUserName());
//        helper.setText(R.id.tweetText, item.getText());
//        helper.setText(R.id.tweetDate, item.getCreatedAt());

    }


}
