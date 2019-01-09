package simple.project.giisdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.utils.EditTextUtil;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.SignUpPresenter;
import simple.project.giisdemo.mvp.view.SignUpView;

import static android.text.TextUtils.isEmpty;

/**
 * @author Created by ys
 * @date at 2019/1/8 15:26
 * @describe
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
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_sign_up, null);
        unbinder = ButterKnife.bind(this, view);
        inputName.setOnFocusChangeListener(this);
        inputPhone.setOnFocusChangeListener(this);
        newPasswd.setOnFocusChangeListener(this);
        repeatPasswd.setOnFocusChangeListener(this);
        return view;
    }

    private boolean hidden = true;

    @OnClick({R.id.signup_btn, R.id.login, R.id.nepasswd_icon, R.id.repasswd_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signup_btn:
                if (validInput()) {
                    getPresenter().signUp(inputName.getText().toString(),
                            inputPhone.getText().toString(),
                            newPasswd.getText().toString());
                }
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
            ToastUtil.showShort(getBaseFragmentActivity(), "name is empty");
            return false;
        } else if (isEmpty(inputPhone.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "phone is empty");
            return false;
        } else if (isEmpty(newPasswd.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "password is empty");
            return false;
        } else if (isEmpty(repeatPasswd.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "please input password again");
            return false;
        } else if (!newPasswd.getText().toString().equals(repeatPasswd.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "password is different");
            return false;
        }
        return true;
    }

    @Override
    public void toLogin() {
        startFragment(new LoginFragment());

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
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showShort(getBaseFragmentActivity(), errorMsg);
    }

    @Override
    public void onDestroy() {
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onDestroy();
    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        EditTextUtil.onFocusChange(getBaseFragmentActivity(), hidden, view, hasFocus);
    }

}
