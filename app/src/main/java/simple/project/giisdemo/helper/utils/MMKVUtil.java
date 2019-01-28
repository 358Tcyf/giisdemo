package simple.project.giisdemo.helper.utils;

import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @author Created by ys
 * @date at 2019/1/29 0:35
 * @describe
 */
public class MMKVUtil {

    public static void put(String key, Object object) {
        MMKV kv = MMKV.defaultMMKV();
        if (object instanceof String)
            kv.encode(key, (String) object);
    }
}