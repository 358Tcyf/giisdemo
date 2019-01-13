package simple.project.giisdemo.helper.utils;

import java.util.Map;

/**
 * @author Created by ys
 * @date at 2019/1/14 0:36
 * @describe
 */
public class MessageEvent {
    private Map<String, Object> message;

    public MessageEvent(Map<String, Object> message) {
        this.message = message;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}
