package simple.project.giisdemo.bean;

import java.util.Date;

/**
 * @author Created by ys
 * @date at 2019/1/12 23:35
 * @describe
 */
public class PushMessageBean {

    private String title;
    private String content;
    private Date createTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
