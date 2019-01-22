package simple.project.giisdemo.helper.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.choota.dev.ctimeago.TimeAgo;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Created by ys
 * @date at 2019/1/22 20:03
 * @describe
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {


    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String dataToStr(Context mContext, Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        TimeAgo timeAgo = new TimeAgo().locale(mContext).with(sdf);
        return timeAgo.getTimeAgo(date);
    }

    public static Date strToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pst = new ParsePosition(0);
        return sdf.parse(str, pst);
    }
}
