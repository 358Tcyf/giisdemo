package simple.project.giisdemo.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.fragment.LoginFragment;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:47
 * @describe
 */
public class LoginActivity extends QMUIFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.login;
    }

    private BaseFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), mFragment, mFragment.getClass().getSimpleName())
                    .addToBackStack(mFragment.getClass().getSimpleName())
                    .commit();
        }
    }


    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出方法
    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //用户退出处理
            finish();
            System.exit(0);
        }
    }

}
