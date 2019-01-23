package simple.project.giisdemo.data.bean;

/**
 * @author Created by Simple
 * @date at 2019/1/4 13:22
 * @describe
 */
public class TagBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String toString() {
        return "TagBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
