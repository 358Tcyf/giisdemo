package simple.project.giisdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import simple.project.giisdemo.R;
import simple.project.giisdemo.bean.TagBean;
import simple.project.giisdemo.helper.server.DemoIntentService;
import simple.project.giisdemo.helper.server.DemoPushService;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_TAGS;

public class GetuiActivity extends QMUIFragmentActivity {


    private static final String MAIN = "GetuiActivity";
    @BindView(R.id.hello)
    TextView hello;

    @Override
    protected int getContextViewId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getui);
        ButterKnife.bind(this);
//        DialogUtil.showTipDialog(this, QMUITipDialog.Builder.ICON_TYPE_FAIL, "error", true);

        initPushManager();

    }

    private void initPushManager() {
        hello.setText("hello! " + (String) SPUtils.get(this, USER_NAME, "world"));
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        hello.append("\nbind clientid = " + PushManager.getInstance().getClientid(this));
        String alias = (String) SPUtils.get(this, USER_UID, "noalias");
        if (alias.length() > 0) {
            PushManager.getInstance().bindAlias(GetuiActivity.this, alias);
            hello.append("\nbind alias = " + alias);
        }

        String savedTags = (String) SPUtils.get(this, USER_TAGS, "");
        List<TagBean> tagBeans = JSON.parseArray(savedTags, TagBean.class);
        Tag[] tagParam = new Tag[tagBeans.size()];
        for (int i = 0; i < tagBeans.size(); i++) {
            Tag t = new Tag();
            t.setName(tagBeans.get(i).getName());
            tagParam[i] = t;
        }
        int i = PushManager.getInstance().setTag(this, tagParam, System.currentTimeMillis() + "");
        String text = "设置标签失败,未知异常";
        switch (i) {
            case PushConsts.SETTAG_SUCCESS:
                text = "设置标签成功";
                hello.append("\nbind tags = ");
                for (Tag t : tagParam) {
                    hello.append(t.getName() + " ");
                }
                break;
            case PushConsts.SETTAG_ERROR_COUNT:
                text = "设置标签失败, tag数量过大, 最大不能超过200个";
                break;
            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = "设置标签失败, 频率过快, 两次间隔应大于1s";
                break;
            case PushConsts.SETTAG_ERROR_REPEAT:
                text = "设置标签失败, 标签重复";
                break;
            case PushConsts.SETTAG_ERROR_UNBIND:
                text = "设置标签失败, 服务未初始化成功";
                break;
            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = "设置标签失败, 未知异常";
                break;
            case PushConsts.SETTAG_ERROR_NULL:
                text = "设置标签失败, tag 为空";
                break;
            case PushConsts.SETTAG_NOTONLINE:
                text = "还未登陆成功";
                break;
            case PushConsts.SETTAG_IN_BLACKLIST:
                text = "该应用已经在黑名单中,请联系售后支持!";
                break;
            case PushConsts.SETTAG_NUM_EXCEED:
                text = "已存 tag 超过限制";
                break;
            default:
                break;
        }
        Log.d(MAIN, "result: " + text);
    }

}

