package simple.project.giisdemo.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.MainActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.utils.EditTextUtil;
import simple.project.giisdemo.helper.utils.ToastUtil;
import simple.project.giisdemo.mvp.presenter.LoginPresenter;
import simple.project.giisdemo.mvp.view.LoginView;

import static android.text.TextUtils.isEmpty;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:49
 * @describe
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView, View.OnFocusChangeListener {

    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_passwd)
    EditText inputPasswd;

    private Unbinder unbinder;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_login, null);
        unbinder = ButterKnife.bind(this, view);
        inputUser.setOnFocusChangeListener(this);
        inputPasswd.setOnFocusChangeListener(this);
        getPresenter().setAccount();
        getPresenter().setPassword();
        return view;
    }


    private boolean hidden = true;

    @OnClick({R.id.login_btn, R.id.signup, R.id.passwd_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (validInput()) {
                    getPresenter().login(inputUser.getText().toString(), inputPasswd.getText().toString());
                }
                break;
            case R.id.signup:
                toSignUp();
                break;
            case R.id.passwd_icon:
                hidden = EditTextUtil.setPasswordHidden(inputPasswd);
                break;
        }
    }

    private boolean validInput() {
        if (isEmpty(inputUser.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "user ID is empty");
            EditTextUtil.shakeAnimation(getBaseFragmentActivity(), inputUser);
            return false;
        } else if (isEmpty(inputPasswd.getText().toString())) {
            ToastUtil.showShort(getBaseFragmentActivity(), "password is empty");
            EditTextUtil.shakeAnimation(getBaseFragmentActivity(), inputPasswd);
            return false;
        }
        return true;
    }

    @Override
    public void toMain() {
        //TODO 跳转到主页面
        Intent intent = new Intent(getBaseFragmentActivity(), MainActivity.class);
        startActivity(intent);
        getBaseFragmentActivity().finish();
    }

    @Override
    public void toSignUp() {
        startFragment(new SignUpFragment());
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showShort(getBaseFragmentActivity(), errorMsg);
    }

    @Override
    public void setAccount(String phone) {
        inputUser.setText(phone);
    }

    @Override
    public void setPasswd(String passwd) {
        inputPasswd.setText(passwd);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        EditTextUtil.onFocusChange(getBaseFragmentActivity(), hidden, view, hasFocus);
    }



    //退出时的时间
    private long mExitTime;

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getView()).setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
                exit();
                return true;
            }
            return false;
        });
    }

    //退出方法
    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getBaseFragmentActivity(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            getBaseFragmentActivity().finish();
            System.exit(0);
        }
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
