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
    private String passwd;
    private String alias;
    private List<TagBean> tags;

    public UserBean() {
    }

    public UserBean(String phone, String passwd) {
        this.phone = phone;
        this.passwd = passwd;
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<TagBean> getTags() {
        return tags;
    }

    public void setTags(List<TagBean> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", alias='" + alias + '\'' +
                ", tags=" + tags +
                '}';
    }
}
