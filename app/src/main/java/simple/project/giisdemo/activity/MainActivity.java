package simple.project.giisdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BaseFragment;
import simple.project.giisdemo.fragment.main.MainFragment;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.utils.ThemeUtils.themeToStyle;

/**
 * @author Created by ys
 * @date at 2019/1/8 18:26
 * @describe
 */
public class MainActivity extends QMUIFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.main;
    }

    private static int mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTheme = (int) SPUtils.get(this, "theme", 0);
        setTheme(themeToStyle(mTheme));
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int flag = 0;
        if (null != intent.getExtras().get("flag"))
            flag = (int) intent.getExtras().get("flag");
        if (savedInstanceState == null) {
            BaseFragment fragment = new MainFragment(flag);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

}
