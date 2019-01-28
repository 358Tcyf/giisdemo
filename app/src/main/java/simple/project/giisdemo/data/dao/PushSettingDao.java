package simple.project.giisdemo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import simple.project.giisdemo.data.entity.Push;
import simple.project.giisdemo.data.entity.PushSetting;

/**
 * @author Created by ys
 * @date at 2019/1/23 18:27
 */

@Dao
public interface PushSettingDao {


    @Query("select * from pushsetting_table where userPhone=:userPhone limit 1")
    PushSetting findByPhone(String userPhone);

    @Insert
    void insert(PushSetting... entities);

    @Delete
    void delete(PushSetting entity);

    @Update
    void update(PushSetting entity);
}
