package simple.project.giisdemo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.utils.DisplayUtil;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.SettingUserInfoPresenter;
import simple.project.giisdemo.mvp.view.SettingUserInfoView;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_ROLE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:15
 * @describe
 */
public class SettingUserInfoFragment extends BaseFragment<SettingUserInfoPresenter> implements SettingUserInfoView {
    @BindView(R.id.groupList_userInfo)
    GroupListView groupListUserInfo;
    @BindView(R.id.groupList_userAccount)
    GroupListView groupListUserAccount;
    private Unbinder unbinder;


    @Override
    protected SettingUserInfoPresenter createPresenter() {
        return new SettingUserInfoPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_setting_userinfo, null);
        unbinder = ButterKnife.bind(this, view);
        initMenuList();
        return view;
    }

    private void initMenuList() {
        QMUICommonListItemView userPic = groupListUserInfo.createItemView(getResources().getString(R.string.use_pic));
        userPic.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        userPic.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        QMUIRadiusImageView userPicView = new QMUIRadiusImageView(getBaseFragmentActivity());
        LayoutParams param = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        param.height = DisplayUtil.dip2px(getBaseFragmentActivity(), 80);
        param.width = DisplayUtil.dip2px(getBaseFragmentActivity(), 60);
        param.setMargins(0, 2, DisplayUtil.dip2px(getBaseFragmentActivity(), 10), 2); // Margin
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);//水平居中
        userPicView.setLayoutParams(param);
        userPicView.setOval(false);
        userPicView.setCircle(false);
        userPicView.setCornerRadius(2);
        userPicView.setImageResource(R.mipmap.user_pic_blue);
        userPic.addAccessoryCustomView(userPicView);
        QMUICommonListItemView userName = groupListUserInfo.createItemView(getResources().getString(R.string.use_name));
        userName.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userName.setDetailText((CharSequence) SPUtils.get(getBaseFragmentActivity(), USER_NAME, ""));
        QMUICommonListItemView userBranch = groupListUserInfo.createItemView(getResources().getString(R.string.use_branch));
        userBranch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userBranch.setDetailText((CharSequence) SPUtils.get(getBaseFragmentActivity(), USER_ROLE, "XX部门"));
        QMUICommonListItemView userNo = groupListUserInfo.createItemView(getResources().getString(R.string.use_no));
        userNo.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userNo.setDetailText((CharSequence) SPUtils.get(getBaseFragmentActivity(), USER_UID, ""));
        QMUICommonListItemView userPhone = groupListUserInfo.createItemView(getResources().getString(R.string.use_phone));
        userPhone.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        userPhone.setDetailText((CharSequence) SPUtils.get(getBaseFragmentActivity(), USER_PHONE, ""));
        QMUICommonListItemView userPasswd = groupListUserInfo.createItemView(getResources().getString(R.string.use_passwd));
        userPasswd.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        View.OnClickListener onClickListener = v -> {
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                ToastUtil.showShort(getBaseFragmentActivity(), text + " is Clicked");
                switch ((String) text) {
                    case "相片":
                        break;
                    case "手机":
                        break;
                    case "密码":
                        break;
                }
            }
        };

        GroupListView.newSection(getBaseFragmentActivity())
                .setTitle(getResources().getString(R.string.info_section_1))
                .addItemView(userPic, onClickListener)
                .addItemView(userName, onClickListener)
                .addItemView(userBranch, onClickListener)
                .addItemView(userNo, onClickListener)
                .addTo(groupListUserInfo);

        GroupListView.newSection(getBaseFragmentActivity())
                .setTitle(getResources().getString(R.string.info_section_2))
                .addItemView(userPhone, onClickListener)
                .addItemView(userPasswd, onClickListener)
                .addTo(groupListUserAccount);


    }


}
