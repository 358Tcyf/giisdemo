package simple.project.giisdemo.fragment.main.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.ShowImageActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.mvp.presenter.main.SettingUserInfoPresenter;
import simple.project.giisdemo.mvp.view.main.SettingUserInfoView;

import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initRightTextButtoninitial;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;
import static simple.project.giisdemo.helper.utils.SBUtil.bitmapToString;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:15
 * @describe
 */
public class SettingUserInfoFragment extends BaseFragment<SettingUserInfoPresenter> implements SettingUserInfoView {
    @BindView(R.id.groupList_1)
    GroupListView groupListUserInfo;
    @BindView(R.id.groupList_2)
    GroupListView groupListUserAccount;
    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    private Unbinder unbinder;

    @Override
    protected SettingUserInfoPresenter createPresenter() {
        return new SettingUserInfoPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_grouplist, null);
        unbinder = ButterKnife.bind(this, view);
        initTopBar();
        getPresenter().initGroupListView(groupListUserInfo, groupListUserAccount);
        return view;
    }

    private void initTopBar() {
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.set_info);
        initRightTextButtoninitial(mTopBar, getBaseFragmentActivity(), R.string.save, R.id.save)
                .setOnClickListener(v -> {
                    getPresenter().save();
                });
    }


    @Override
    public void toFinish() {
        popBackStack();
    }

    @Override
    public void showImage() {
        Uri picUri = getUserPicPathUri(getBaseFragmentActivity());
        Intent intent = new Intent(getBaseFragmentActivity(), ShowImageActivity.class);
        intent.putExtra("image", picUri.toString());
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("image", picUri);
        EventBus.getDefault().post(new MessageEvent(message));
        startActivity(intent);
    }

}
