package simple.project.giisdemo.mvp.presenter.main.setting;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.qingmei2.rximagepicker.core.RxImagePicker;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.utils.DisplayUtil;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.helper.utils.MyImagePicker;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.mvp.model.main.setting.SettingUserInfoModel;
import simple.project.giisdemo.mvp.view.main.setting.SettingUserInfoView;

import static simple.project.giisdemo.helper.constant.GlobalField.CHOOSE_PIC;
import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.TAKE_PIC;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_ROLE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;
import static simple.project.giisdemo.helper.constant.GlobalField.VIEW_PIC;
import static simple.project.giisdemo.helper.utils.FileUtil.getUserPicPathUri;
import static simple.project.giisdemo.helper.utils.QMUIUtil.successTipDialog;

/**
 * @author Created by ys
 * @date at 2019/1/13 0:14
 * @describe
 */
public class SettingUserInfoPresenter extends BasePresenter<SettingUserInfoView, SettingUserInfoModel> {

    QMUICommonListItemView userPhone;

    public void initGroupListView(GroupListView groupListUserInfo) {
        QMUICommonListItemView userPic = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_pic));
        userPic.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        userPic.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

        userPicView = new QMUIRadiusImageView(getView().getCurContext());
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param.height = DisplayUtil.dip2px(getView().getCurContext(), 80);
        param.width = DisplayUtil.dip2px(getView().getCurContext(), 60);
        param.setMargins(0, 2, DisplayUtil.dip2px(getView().getCurContext(), 2), 2); // Margin
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);//水平居中
        userPicView.setLayoutParams(param);
        userPicView.setOval(false);
        userPicView.setCircle(false);
        userPicView.setCornerRadius(2);
        Uri picUri = getUserPicPathUri(getView().getCurContext());
        if (null != picUri)
            userPicView.setImageURI(picUri);
        else
            userPicView.setImageResource(R.mipmap.user_pic_blue);
        userPic.addAccessoryCustomView(userPicView);

        QMUICommonListItemView userName = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_name));
        userName.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userName.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), USER_NAME, ""));

        QMUICommonListItemView userBranch = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_branch));
        userBranch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userBranch.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), USER_ROLE, "XX部门"));

        QMUICommonListItemView userNo = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_no));
        userNo.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        userNo.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), USER_UID, ""));

        userPhone = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_phone));
        userPhone.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        userPhone.setDetailText((CharSequence) SPUtils.get(getView().getCurContext(), USER_PHONE, ""));

        QMUICommonListItemView userPasswd = groupListUserInfo.createItemView(getView().getCurContext().getResources().getString(R.string.use_passwd));
        userPasswd.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        GroupListView.newSection(getView().getCurContext())
                .setTitle(getView().getCurContext().getResources().getString(R.string.info_section_1))
                .addItemView(userPic, onClickListener)
                .addItemView(userName, onClickListener)
                .addItemView(userBranch, onClickListener)
                .addItemView(userNo, onClickListener)
                .addTo(groupListUserInfo);

        GroupListView.newSection(getView().getCurContext())
                .setTitle(getView().getCurContext().getResources().getString(R.string.info_section_2))
                .addItemView(userPhone, onClickListener)
                .addItemView(userPasswd, onClickListener)
                .addTo(groupListUserInfo);
    }

    private QMUIRadiusImageView userPicView;


    @SuppressLint("CheckResult")
    public void takePhoto(View view) {
        RxImagePicker.INSTANCE
                .create(MyImagePicker.class)
                .openCamera(getView().getCurContext())
                .subscribe(result -> {
                    uri = result.getUri();
                    setPic(view);
                });
    }

    @SuppressLint("CheckResult")
    public void choosePhoto(View view) {
        RxImagePicker.INSTANCE
                .create(MyImagePicker.class)
                .openGallery(getView().getCurContext())
                .subscribe(result -> {
                    uri = result.getUri();
                    setPic(view);
                });


    }

    private Uri uri;

    public void setPic(View view) {
        Glide.with(getView().getCurContext())
                .load(uri)
                .into((ImageView) view);
    }

    public void uploadPic(Uri uri) {
        if (uri != null)
            getModel().uploadHeadImage(uri, new OnHttpCallBack<RetResult>() {
                @Override
                public void onSuccess(RetResult retResult) {
                    successTipDialog(getView().getCurContext(), retResult.getMsg());
                    getView().toFinish();
                }

                @Override
                public void onFailed(String errorMsg) {
                    getView().showErrorMsg(errorMsg);
                    getView().toFinish();
                }
            });
    }

    public void savePic() {
        if (uri != null) {
            getModel().savePic(uri);
            uploadPic(uri);
        }
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("userPic", uri);
        EventBus.getDefault().post(new MessageEvent(message));

    }

    public void showPhone(String phone) {
        userPhone.setDetailText( phone);

    }

    private View.OnClickListener onClickListener = v -> {
        if (v instanceof QMUICommonListItemView) {
            CharSequence text = ((QMUICommonListItemView) v).getText();
            switch ((String) text) {
                case "相片":
                    new QMUIBottomSheet.BottomListSheetBuilder(getView().getCurContext())
                            .addItem(getView().getCurContext().getResources().getString(R.string.take_pic))
                            .addItem(getView().getCurContext().getResources().getString(R.string.choose_pic))
                            .addItem(getView().getCurContext().getResources().getString(R.string.view_pic))
                            .addItem(getView().getCurContext().getResources().getString(R.string.cancel))
                            .setOnSheetItemClickListener((dialog, itemView, position, tag) -> {
                                dialog.dismiss();
                                switch (position) {
                                    case TAKE_PIC:
                                        takePhoto(userPicView);
                                        break;
                                    case CHOOSE_PIC:
                                        choosePhoto(userPicView);
                                        break;
                                    case VIEW_PIC:
                                        getView().showImage();
                                        break;
                                    default:
                                }
                            })
                            .build()
                            .show();
                    break;
                case "手机":
                    getView().updatePhone();
                    break;
                case "修改密码":
                    getView().updatePassword();
                    break;
            }
        }
    };
}
