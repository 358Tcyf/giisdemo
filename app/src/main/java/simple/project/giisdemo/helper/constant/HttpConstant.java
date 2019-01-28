package simple.project.giisdemo.helper.constant;

/**
 * @author Created by ys
 * @date at 2019/1/22 20:07
 * @describe
 */
public class HttpConstant {
    private static final String BUGDOGGY = "http://47.102.41.26";
    private static final String LOCALHOST1 = "http://192.168.0.133";
    private static final String WIFI1 = "http://192.168.43.204";
    private static final String WIFI2 = "http://10.10.10.106";
    private static final String WIFI3 = "http://192.158.1.30";
    private static final String WIFI4 = "http://10.10.10.107";
    private static final String WIFI5 = "http://192.158.1.27";
    private static final String WIFI6 = "http://192.168.1.100";
    public static final String URL = BUGDOGGY;
    public static final String PORT = ":7171";
    public static final String USER = "/user";
    public static final String PIC = "/pic";
    public static final String FILE = "/file";
    public static final String APK = "/apk";
    public static final String HTTP =URL+PORT;
    public static final String GET_USER_PIC = URL + PORT + PIC + "/";
    public static final String GET_NEW_APK = URL + PORT + FILE + APK;
}
