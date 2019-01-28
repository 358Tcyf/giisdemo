package simple.project.giisdemo.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import simple.project.giisdemo.data.dao.PushDao;
import simple.project.giisdemo.data.dao.PushSettingDao;
import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.data.entity.PushSetting;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

/**
 * @author Created by ys
 * @date at 2019/1/23 19:05
 * @describe
 */
public class DatabaseHelper {
    private Context context;
    private GIISDatabase giisDatabase;
    private PushDao pushDao;
    private PushSettingDao pushSettingDao;

    public DatabaseHelper(Context context) {
        this.context = context;
        giisDatabase = Room.databaseBuilder(context,
                GIISDatabase.class, "giis_database")
                .addCallback(new RoomDatabase.Callback() {
                    //第一次创建数据库时调用，但是在创建所有表之后调用的
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    //当数据库被打开时调用
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .allowMainThreadQueries()//允许在主线程查询数据
                .addMigrations()//迁移数据库使用
                .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                .build();
        pushDao = giisDatabase.pushDao();
        pushSettingDao = giisDatabase.pushSettingDao();
    }

    public void insertPush(Push push) {
        pushDao.insert(push);
    }

    public ArrayList<Push> getAllPush() {
        List<Push> list = pushDao.getAll();
        return (ArrayList<Push>) list;
    }

    public void insertPushSetting(String phone) {
        if (pushSettingDao.findByPhone(phone) == null) {
            PushSetting setting = new PushSetting(phone);
            pushSettingDao.insert(setting);

        }
    }

    public void updatePushSetting(PushSetting setting) {
        pushSettingDao.update(setting);
    }

    public PushSetting getPushSetting(String phone) {
        return pushSettingDao.findByPhone(phone);
    }

}
