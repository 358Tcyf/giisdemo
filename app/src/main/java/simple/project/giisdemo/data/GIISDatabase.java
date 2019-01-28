package simple.project.giisdemo.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import simple.project.giisdemo.data.dao.PushDao;
import simple.project.giisdemo.data.dao.PushSettingDao;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.data.entity.PushSetting;

/**
 * @author Created by ys
 * @date at 2019/1/23 19:01
 * @describe
 */
@Database(entities = {Push.class, PushSetting.class}, version = 2, exportSchema = false)
public abstract class GIISDatabase extends RoomDatabase {
    public abstract PushDao pushDao();

    public abstract PushSettingDao pushSettingDao();

}
