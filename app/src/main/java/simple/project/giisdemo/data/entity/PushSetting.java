package simple.project.giisdemo.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * @author Created by ys
 * @date at 2019/1/12 23:34
 * @describe
 */
@Entity(tableName = "pushsetting_table")
public class PushSetting {

    @PrimaryKey
    @NonNull
    private String userPhone;

    @ColumnInfo
    private boolean pushSwitch;

    @ColumnInfo
    private boolean voice;

    @ColumnInfo
    private boolean vibrate;

    @ColumnInfo
    private boolean floatWindow;

    @NonNull
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(@NonNull String userPhone) {
        this.userPhone = userPhone;
    }

    public boolean isPushSwitch() {
        return pushSwitch;
    }

    public void setPushSwitch(boolean pushSwitch) {
        this.pushSwitch = pushSwitch;
    }

    public boolean isVoice() {
        return voice;
    }

    public void setVoice(boolean voice) {
        this.voice = voice;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public boolean isFloatWindow() {
        return floatWindow;
    }

    public void setFloatWindow(boolean floatWindow) {
        this.floatWindow = floatWindow;
    }

    @Override
    public String toString() {
        return "PushSetting{" +
                "userPhone='" + userPhone + '\'' +
                ", pushSwitch=" + pushSwitch +
                ", voice=" + voice +
                ", vibrate=" + vibrate +
                ", floatWindow=" + floatWindow +
                '}';
    }
}
