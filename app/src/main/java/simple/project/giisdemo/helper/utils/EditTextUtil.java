package simple.project.giisdemo.helper.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import simple.project.giisdemo.R;

/**
 * @author Created by Simple
 * @date at 2019/1/3 20:25
 * @describe
 */
public class EditTextUtil {


    public static void onFocusChange(Context context, View view, boolean hasFocus) {
        setTextColor(context,(EditText) view, hasFocus);
        View parentView = (View) view.getParent();
        switch (view.getId()) {
            case R.id.input_user:
                ImageView userIcon = parentView.findViewById(R.id.user_icon);
                if (hasFocus) {
                    userIcon.setImageResource(R.mipmap.user_red);
                } else {
                    userIcon.setImageResource(R.mipmap.user_white);
                }
                break;
            case R.id.input_passwd:
                ImageView passwdIcon = parentView.findViewById(R.id.passwd_icon);
                if (hasFocus) {
                    passwdIcon.setImageResource(R.mipmap.key_red);
                } else {
                    passwdIcon.setImageResource(R.mipmap.key_white);
                }
                break;
            case R.id.input_name:
                ImageView nameIcon = parentView.findViewById(R.id.name_icon);
                if (hasFocus) {
                    nameIcon.setImageResource(R.mipmap.user_red);
                } else {
                    nameIcon.setImageResource(R.mipmap.user_white);
                }
                break;
            case R.id.input_phone:
                ImageView phoneIcon = parentView.findViewById(R.id.phone_icon);
                if (hasFocus) {
                    phoneIcon.setImageResource(R.mipmap.phone_red);
                } else {
                    phoneIcon.setImageResource(R.mipmap.phone_white);
                }
                break;
            case R.id.new_passwd:
                ImageView nepasswdIcon = parentView.findViewById(R.id.nepasswd_icon);
                if (hasFocus) {
                    nepasswdIcon.setImageResource(R.mipmap.lock_red);
                } else {
                    nepasswdIcon.setImageResource(R.mipmap.lock);
                }
                break;
            case R.id.repeat_passwd:
                ImageView repasswdIcon = parentView.findViewById(R.id.repasswd_icon);
                if (hasFocus) {
                    repasswdIcon.setImageResource(R.mipmap.lock_red);
                } else {
                    repasswdIcon.setImageResource(R.mipmap.lock);
                }
                break;
            default:
        }

    }


    private static void setTextColor(Context context, EditText editText, boolean hasFocus) {
        if (hasFocus) {
            editText.setTextColor(context.getResources().getColor(R.color.editRed));
            editText.setHintTextColor(context.getResources().getColor(R.color.editRed));
        } else {
            editText.setTextColor(context.getResources().getColor(R.color.editWhite));
            editText.setHintTextColor(context.getResources().getColor(R.color.editWhite));
        }
    }
}
