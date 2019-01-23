package simple.project.giisdemo.data.entity;

import android.arch.persistence.room.PrimaryKey;

/**
 * @author Created by ys
 * @date at 2019/1/23 18:30
 */
public class BaseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
