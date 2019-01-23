package simple.project.giisdemo.fragment.login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.andrognito.flashbar.Flashbar;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.utils.EditTextUtil;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.login.SignUpPresenter;
import simple.project.giisdemo.mvp.view.login.SignUpView;

import static android.text.TextUtils.isEmpty;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.errorBar;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.loginError;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.progressBar;

/**
 * @author Created by ys
 * @date at 2019/1/8 15:26
 * @describe 注册界面
 */
public class SignUpFragment extends BaseFragment<SignUpPresenter> implements SignUpView, View.OnFocusChangeListener {

    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.new_passwd)
    EditText newPasswd;
    @BindView(R.id.repeat_passwd)
    EditText repeatPasswd;

    private Activity mActivity;
    private Flashbar flashbar;
    private boolean hidden = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_sign_up, null);
        ButterKnife.bind(this, view);
        mActivity = getBaseFragmentActivity();
        inputName.setOnFocusChangeListener(this);
        inputPhone.setOnFocusChangeListener(this);
        newPasswd.setOnFocusChangeListener(this);
        repeatPasswd.setOnFocusChangeListener(this);
        return view;
    }


    @OnClick({R.id.signup_btn, R.id.login, R.id.nepasswd_icon, R.id.repasswd_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signup_btn:
                signUpProgress();
                break;
            case R.id.login:
                toLogin();
                break;
            case R.id.nepasswd_icon:
                hidden = EditTextUtil.setPasswordHidden(newPasswd);
                break;
            case R.id.repasswd_icon:
                hidden = EditTextUtil.setPasswordHidden(repeatPasswd);
                break;
        }
    }

    private boolean validInput() {
        if (isEmpty(inputName.getText().toString())) {
            errorBar(mActivity, "name is empty");
            EditTextUtil.shakeAnimation(mActivity, inputName);
            return false;
        } else if (isEmpty(inputPhone.getText().toString())) {
            errorBar(mActivity, "phone is empty");
            EditTextUtil.shakeAnimation(mActivity, inputPhone);
            return false;
        } else if (isEmpty(newPasswd.getText().toString())) {
            errorBar(mActivity, "password is empty");
            EditTextUtil.shakeAnimation(mActivity, newPasswd);
            return false;
        } else if (isEmpty(repeatPasswd.getText().toString())) {
            errorBar(mActivity, "please input password again");
            EditTextUtil.shakeAnimation(mActivity, repeatPasswd);
            return false;
        } else if (!newPasswd.getText().toString().equals(repeatPasswd.getText().toString())) {
            errorBar(mActivity, "password is different");
            EditTextUtil.shakeAnimation(mActivity, newPasswd);
            EditTextUtil.shakeAnimation(mActivity, repeatPasswd);
            return false;
        }
        return true;
    }

    @Override
    public void signUpProgress() {
        if (validInput()) {
            flashbar = progressBar(mActivity, R.string.logining);
            flashbar.show();
            new Handler().postDelayed(() -> {
                getPresenter().signUp(inputName.getText().toString(),
                        inputPhone.getText().toString(),
                        newPasswd.getText().toString()
                );
            }, 500);
        }
    }

    @Override
    public void toLogin() {
        startFragment(new LoginFragment());
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        errorBar(mActivity, errorMsg);
    }

    @Override
    protected SignUpPresenter createPresenter() {
        return new SignUpPresenter();
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        EditTextUtil.onFocusChange(getBaseFragmentActivity(), hidden, view, hasFocus);
    }

    @Override
    public void onDestroy() {
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onDestroy();
    }
}
