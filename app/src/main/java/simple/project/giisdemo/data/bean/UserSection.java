package simple.project.giisdemo.data.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Created by ys
 * @date at 2019/1/30 20:18
 * @describe
 */
public class UserSection extends SectionEntity<UserBean> {



    public UserSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public UserSection(UserBean t) {
        super(t);
    }


}