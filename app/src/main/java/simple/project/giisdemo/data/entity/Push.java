package simple.project.giisdemo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * @author Created by ys
 * @date at 2019/1/12 23:35
 */

@Entity(tableName = "push_table")
public class Push extends BaseEntity{



    private String title;

    private String content;

    private long  createTime;

    private int pushMethod;

    private int messageType;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getPushMethod() {
        return pushMethod;
    }

    public void setPushMethod(int pushMethod) {
        this.pushMethod = pushMethod;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
