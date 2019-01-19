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
    private static final String WIFI2 = "http://10.10.10.106";
    private static final String WIFI3 = "http://192.158.1.30";
    private static final String WIFI4 = "http://10.10.10.107";
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

    public static final int UPDATE_ALL = 0;
    public static final int UPDATE_PHONE = 1;
    public static final int UPDATE_PHONE_NEW = 2;
    public static final int UPDATE_PASSWORD = 3;
    public static final int UPDATE_PASSWORD_NEW = 4;

    /*
     * 推送设置
     * */
    public static final String[] cycles = new String[]{"每天", "每周", "每月"};
    public static final String[] weekSet = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    public static final String[] dateSet = new String[]{"每月1号", "每月10号", "每月15号", "每月20号"};


    /*系统设置*/
    public static final String[] languages = new String[]{"跟随系统", "简体中文", "繁体中文", "英语"};

    public static final String[] themes = new String[]{"默认", "TEAL", "LIGHT_BLUE", "INDIGO"
            , "少女粉", "基佬紫", "胖次蓝", "草苗绿", "咸蛋黄", "姨妈红"};

    public static final int DEFAULT = 0;
    public static final int TEAL = 1;
    public static final int LIGHT_BLUE = 2;
    public static final int INDIGO = 3;
    public static final int CARD_SAKURA = 4;
    public static final int CARD_HOPE = 5;
    public static final int CARD_STORM = 6;
    public static final int CARD_WOOD = -1;
    public static final int CARD_LIGHT = 7;
    public static final int CARD_THUNDER = 8;
    public static final int CARD_SAND = -2;
    public static final int CARD_FIREY = 9;


}
