package simple.project.giisdemo.helper.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Created by Simple
 * @date at 2019/1/3 22:00
 * @describe
 */
public class ToastUtil {


    /**
     * 弹出短时间提示
     *
     * @param context 上下文
     * @param msg     提示的信息
     */
    public static void showShort(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }


    /**
     * 弹出长时间提示
     *
     * @param context 上下文
     * @param msg     提示的信息
     */
    public static void showLong(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

    }


}
