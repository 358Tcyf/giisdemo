package simple.project.giisdemo.fragment.main.setting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.mvp.presenter.main.UpdateUserInfoPresenter;
import simple.project.giisdemo.mvp.view.main.UpdateUserInfoView;

import static simple.project.giisdemo.helper.constant.GlobalField.UPDATE_PASSWORD;
import static simple.project.giisdemo.helper.constant.GlobalField.UPDATE_PASSWORD_NEW;
import static simple.project.giisdemo.helper.constant.GlobalField.UPDATE_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.UPDATE_PHONE_NEW;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initBackAndTitle;
import static simple.project.giisdemo.helper.custom.BaseFragmentView.initRightTextButtoninitial;
import static simple.project.giisdemo.helper.utils.EditTextUtil.focusEditText;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.validateError;

/**
 * @author Created by ys
 * @date at 2019/1/17 16:58
 * @describe
 */
public class UpdateUserInfoFragment extends BaseFragment<UpdateUserInfoPresenter> implements UpdateUserInfoView {


    @BindView(R.id.topbar)
    QMUITopBarLayout mTopBar;
    @BindView(R.id.update_method1)
    RelativeLayout updateMethod1;
    @BindView(R.id.update_method2)
    RelativeLayout updateMethod2;
    @BindView(R.id.update_method3)
    RelativeLayout updateMethod3;
    @BindView(R.id.update_method4)
    RelativeLayout updateMethod4;
    @BindView(R.id.input_old_phone)
    MaterialEditText inputOldPhone;
    @BindView(R.id.input_new_phone)
    MaterialEditText inputNewPhone;
    @BindView(R.id.input_old_password)
    MaterialEditText inputOldPassword;
    @BindView(R.id.input_new_password)
    MaterialEditText inputNewPassword;
    private Unbinder unbinder;
    private int updateMethod;

    public UpdateUserInfoFragment() {
    }

    @SuppressLint("ValidFragment")
    public UpdateUserInfoFragment(int updateMethod) {
        this.updateMethod = updateMethod;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected UpdateUserInfoPresenter createPresenter() {
        return new UpdateUserInfoPresenter();
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_update_info, null);
        unbinder = ButterKnife.bind(this, view);
        initTopBar();
        initEditText();
        return view;
    }


    private void initTopBar() {
        initBackAndTitle(mTopBar, getBaseFragmentActivity(), R.string.update_info);
        switch (updateMethod) {
            case UPDATE_PHONE:
                focusEditText(getBaseFragmentActivity(),inputOldPhone);
            case UPDATE_PASSWORD:
                focusEditText(getBaseFragmentActivity(),inputNewPhone);
                initRightTextButtoninitial(mTopBar, getBaseFragmentActivity(), R.string.next, R.id.next)
                        .setOnClickListener(v -> {
                            if (validate())
                                startFragmentAndDestroyCurrent(new UpdateUserInfoFragment(updateMethod + 1));
                        });
                break;
            case UPDATE_PHONE_NEW:
                focusEditText(getBaseFragmentActivity(),inputOldPassword);
            case UPDATE_PASSWORD_NEW:
                focusEditText(getBaseFragmentActivity(),inputNewPassword);
                initRightTextButtoninitial(mTopBar, getBaseFragmentActivity(), R.string.save, R.id.save)
                        .setOnClickListener(v -> {
                            switch (updateMethod) {
                                case UPDATE_PHONE_NEW:
                                    getPresenter().updateInfo(inputNewPhone.getText().toString(), "");
                                    break;
                                case UPDATE_PASSWORD_NEW:
                                    getPresenter().updateInfo("", inputNewPassword.getText().toString());
                                    break;
                            }
                        });
                break;
            default:
        }

    }

    private void initEditText() {
        switch (updateMethod) {
            case UPDATE_PHONE:
                updateMethod1.setVisibility(View.VISIBLE);
                break;
            case UPDATE_PHONE_NEW:
                updateMethod2.setVisibility(View.VISIBLE);
                break;
            case UPDATE_PASSWORD:
                updateMethod3.setVisibility(View.VISIBLE);
                break;
            case UPDATE_PASSWORD_NEW:
                updateMethod4.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    @Override
    public void onDestroy() {
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onDestroy();
    }

    @Override
    public boolean validate() {
        switch (updateMethod) {
            case UPDATE_PHONE:
                return getPresenter().validateOldPhone(inputOldPhone.getText().toString());
            case UPDATE_PHONE_NEW:
                break;
            case UPDATE_PASSWORD:
                return getPresenter().validateOldPassword(inputOldPassword.getText().toString());
            case UPDATE_PASSWORD_NEW:
                break;
            default:
        }
        return false;
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        validateError(getBaseFragmentActivity(), errorMsg);
    }

    @Override
    public void updateInfo(String phone) {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("phone", phone);
        EventBus.getDefault().post(new MessageEvent(message));
    }

    @Override
    public void toBack() {
        popBackStack();
    }


}
