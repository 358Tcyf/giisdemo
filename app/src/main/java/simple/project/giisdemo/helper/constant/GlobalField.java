package simple.project.giisdemo.helper.constant;

import android.app.Activity;
import android.app.Application;

import simple.project.giisdemo.R;

/**
 * @author Created by ys
 * @date at 2019/1/8 1:19
 * @describe
 */
public class GlobalField extends Application {
    private static final String BUGDOGGY = "http://47.102.41.26";
    private static final String LOCALHOST1 = "http://192.168.0.133";
    private static final String WIFI1 = "http://192.168.43.204";
    public static final String URL = BUGDOGGY;
    public static final String PORT = ":7171";
    public static final String USER = "/user";

    public static final String DEVICE_CID = "cid";
    public static final String USER_PHONE = "phone";
    public static final String USER_NAME = "name";
    public static final String USER_ROLE = "role";
    public static final String USER_UID = "uid";
    public static final String USER_TAGS = "tags";
    public static final String USER_PWD = "password";

    public static final String SYS_LANG = "language";


    public static final String DEBUG = "DEBUG";

    /*
     * 设置页关于应用菜单
     * */
    public static final int CHECK_UPDATE = 0;
    public static final int CALLBACK = 1;

    /*
     * 个人信息页菜单
     * */
    public static final int TAKE_PIC = 0;
    public static final int CHOOSE_PIC = 1;
    public static final int VIEW_PIC = 2;
    public static final int CANCEL = 3;


    /*
     * 推送设置
     * */
    public static final String[] cycles = new String[]{"每天", "每周", "每月"};
    public static final String[] weekSet = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    public static final String[] dateSet = new String[]{"每月1号", "每月10号", "每月15号", "每月20号"};


}
