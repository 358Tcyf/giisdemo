package simple.project.giisdemo.fragment.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.andrognito.flashbar.Flashbar;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import simple.project.giisdemo.R;
import simple.project.giisdemo.activity.MainActivity;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.helper.utils.EditTextUtil;
import simple.project.giisdemo.mvp.presenter.login.LoginPresenter;
import simple.project.giisdemo.mvp.view.login.LoginView;

import static android.text.TextUtils.isEmpty;
import static simple.project.giisdemo.helper.utils.ActivityUtil.toMainActivity;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.errorBar;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.exitActivity;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.loginError;
import static simple.project.giisdemo.helper.utils.FlashBarUtil.progressBar;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:49
 * @describe 登录界面实现了：
 * 文本输入框的样式变化、
 * 登录验证的动画效果、
 * 登录中的进度条动画、登录失败提示
 */
public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView, View.OnFocusChangeListener {

    @BindView(R.id.input_user)
    EditText inputUser;
    @BindView(R.id.input_passwd)
    EditText inputPasswd;


    private Activity mActivity;
    private Flashbar flashbar;
    private boolean hidden = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getBaseFragmentActivity();
    }

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        inputUser.setOnFocusChangeListener(this);
        inputPasswd.setOnFocusChangeListener(this);
        getPresenter().setAccount();
        getPresenter().setPassword();
        return view;
    }

    @OnClick({R.id.login_btn, R.id.signup, R.id.passwd_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                loginProgress();
                break;
            case R.id.signup:
                toSignUp();
                break;
            case R.id.passwd_icon:
                hidden = EditTextUtil.setPasswordHidden(inputPasswd);
                break;
        }
    }


    @Override
    public void loginProgress() {
        if (validInput()) {
            flashbar = progressBar(mActivity, R.string.logining);
            flashbar.show();
            new Handler().postDelayed(() -> {
                getPresenter().login(inputUser.getText().toString(), inputPasswd.getText().toString());
            }, 500);
        }
    }

    @Override
    public void toMain() {
        flashbar.dismiss();
        toMainActivity(mActivity, 0);
    }

    @Override
    public void toSignUp() {
        QMUIFragment signUpFragment = new SignUpFragment();
        startFragment(signUpFragment);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        errorBar(mActivity, errorMsg);
        flashbar.dismiss();
    }

    private boolean validInput() {
        if (isEmpty(inputUser.getText().toString())) {
            loginError(mActivity, "user ID is empty");
            EditTextUtil.shakeAnimation(mActivity, inputUser);
            return false;
        } else if (isEmpty(inputPasswd.getText().toString())) {
            loginError(mActivity, "password is empty");
            EditTextUtil.shakeAnimation(mActivity, inputPasswd);
            return false;
        }
        return true;
    }

    @Override
    public void setAccount(String phone) {
        inputUser.setText(phone);
    }

    @Override
    public void setPassword(String password) {
        inputPasswd.setText(password);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    /*重写了文本输入的监听，
     * 实现了样式的切换*/
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        EditTextUtil.onFocusChange(mActivity, hidden, view, hasFocus);
    }

    /*下面3个方法 重写实现点击返回键两次退出*/
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
            exitActivity(mActivity, "再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            mActivity.finish();
            System.exit(0);
        }
    }

    /*下面的方法屏蔽了左侧手势退出*/
    @Override
    protected boolean canDragBack() {
        return false;
    }
}
