package simple.project.giisdemo.bean;

import java.util.List;


/**
 * @author Created by Simple
 * @date at 2019/1/3 21:48
 * @describe
 */
public class UserBean {
    private String phone;
    private String name;
    private String email;
    private String password;
    private String uid;
    private List<TagBean> care;

    public UserBean() {
    }

    public UserBean(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<TagBean> getCare() {
        return care;
    }

    public void setCare(List<TagBean> care) {
        this.care = care;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uid='" + uid + '\'' +
                ", care=" + care +
                '}';
    }

}
