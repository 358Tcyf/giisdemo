package simple.project.giisdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.igexin.sdk.PushManager;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.fragment.login.LoginFragment;
import simple.project.giisdemo.fragment.main.MainFragment;
import simple.project.giisdemo.helper.server.DemoIntentService;
import simple.project.giisdemo.helper.server.DemoPushService;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.DEVICE_CID;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment mFragment = new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), mFragment, mFragment.getClass().getSimpleName())
                    .addToBackStack(mFragment.getClass().getSimpleName())
                    .commit();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        initPushManager();
    }

    private void initPushManager() {
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        String cid = PushManager.getInstance().getClientid(this);
        Log.d(DEBUG, cid);
        SPUtils.put(this, DEVICE_CID, "device's cid is " + cid);

    }

}
