package simple.project.giisdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.fragment.main.MainFragment;
import simple.project.giisdemo.fragment.main.SettingFragment;
import simple.project.giisdemo.fragment.main.setting.SettingSystemFragment;
import simple.project.giisdemo.helper.utils.MessageEvent;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.GlobalField.DEFAULT;
import static simple.project.giisdemo.helper.constant.GlobalField.INDIGO;
import static simple.project.giisdemo.helper.constant.GlobalField.LIGHT_BLUE;
import static simple.project.giisdemo.helper.constant.GlobalField.TEAL;
import static simple.project.giisdemo.helper.utils.ThemeUtils.themeToStyle;

/**
 * @author Created by ys
 * @date at 2019/1/8 18:26
 * @describe
 */
public class SettingActivity extends QMUIFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.setting;
    }

    private static int mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTheme = (int) SPUtils.get(this, "theme", 0);
        setTheme(themeToStyle(mTheme));
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = new SettingSystemFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        mTheme = (int) messageEvent.getMessage().get("theme");
        Log.d(DEBUG, "theme is " + mTheme);
        SPUtils.put(this, "theme", mTheme);
        recreate();

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("flag", 3);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
