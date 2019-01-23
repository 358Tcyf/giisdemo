package simple.project.giisdemo.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import simple.project.giisdemo.data.entity.Push;

/**
 * @author Created by ys
 * @date at 2019/1/23 18:27
 */

@Dao
public interface PushDao {

    @Query("select * from push_table order by createTime asc")
    List<Push> getAll();

    @Insert
    void insert(Push... entities);

    @Delete
    void delete(Push entity);

    @Delete
    void deleteAll(List<Push> entities);

    @Update
    void update(Push entity);
}
