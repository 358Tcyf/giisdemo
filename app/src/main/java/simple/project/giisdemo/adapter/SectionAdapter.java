package simple.project.giisdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

import simple.project.giisdemo.R;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.data.bean.UserSection;

import static simple.project.giisdemo.helper.constant.HttpConstant.GET_USER_PIC;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;

/**
 * @author Created by ys
 * @date at 2019/1/30 20:17
 * @describe
 */
public class SectionAdapter extends BaseSectionQuickAdapter<UserSection, BaseViewHolder> {

    private Context context;

    public SectionAdapter(Context context, int layoutResId, int sectionHeadResId, List<UserSection> data) {
        super(layoutResId, sectionHeadResId, data);
        this.context = context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, UserSection item) {
        helper.setText(R.id.section_name, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserSection item) {
        UserBean userBean = item.t;
        switch (helper.getLayoutPosition() %
                2) {
            case 0:
                helper.setImageResource(R.id.user_pic, R.mipmap.user_pic);
                break;
            case 1:
                helper.setImageResource(R.id.user_pic, R.mipmap.user_pic_blue);
                break;
        }
        QMUIRadiusImageView qmuiRadiusImageView = helper.getView(R.id.user_pic);
        Uri picUri = getUserPicPathUri(context, userBean.getUid());
        if (null != picUri)
            qmuiRadiusImageView.setImageURI(picUri);
        else
            qmuiRadiusImageView.setImageResource(R.mipmap.user_pic_blue);
//        直接加载网络uri
//        qmuiRadiusImageView.setImageURI(Uri.parse(GET_USER_PIC + userBean.getUid()));
        helper.setText(R.id.user_name, userBean.getName());
    }
}
