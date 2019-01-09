package simple.project.giisdemo.helper.utils;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import simple.project.giisdemo.R;

/**
 * @author Created by Simple
 * @date at 2019/1/3 20:25
 * @describe
 */
public class EditTextUtil {


    public static void onFocusChange(Context context, Boolean hidden, View view, boolean hasFocus) {
        setTextColor(context, (EditText) view, hasFocus);
        View parentView = (View) view.getParent();
        switch (view.getId()) {
            case R.id.input_user:
                ImageView userIcon = parentView.findViewById(R.id.user_icon);
                if (hasFocus) {
                    userIcon.setImageResource(R.drawable.ic_account_green);
                } else {
                    userIcon.setImageResource(R.drawable.ic_account);
                }
                break;
            case R.id.input_passwd:
                ImageView passwdIcon = parentView.findViewById(R.id.passwd_icon);
                if (hasFocus) {
                    passwdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_green : R.drawable.ic_visibility_green);
                } else {
                    passwdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
                }
                break;
            case R.id.input_name:
                ImageView nameIcon = parentView.findViewById(R.id.name_icon);
                if (hasFocus) {
                    nameIcon.setImageResource(R.drawable.ic_account_green);
                } else {
                    nameIcon.setImageResource(R.drawable.ic_account);
                }
                break;
            case R.id.input_phone:
                ImageView phoneIcon = parentView.findViewById(R.id.phone_icon);
                if (hasFocus) {
                    phoneIcon.setImageResource(R.drawable.ic_phone_green);
                } else {
                    phoneIcon.setImageResource(R.drawable.ic_phone);
                }
                break;
            case R.id.new_passwd:
                ImageView nepasswdIcon = parentView.findViewById(R.id.nepasswd_icon);
                if (hasFocus) {
                    nepasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_green : R.drawable.ic_visibility_green);
                } else {
                    nepasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
                }
                break;
            case R.id.repeat_passwd:
                ImageView repasswdIcon = parentView.findViewById(R.id.repasswd_icon);
                if (hasFocus) {
                    repasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_green : R.drawable.ic_visibility_green);
                } else {
                    repasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
                }
                break;
            default:
        }

    }

    private static void setTextColor(Context context, EditText editText, boolean hasFocus) {
        if (hasFocus) {
            editText.setTextColor(context.getResources().getColor(R.color.material_colorAccent, null));
            editText.setHintTextColor(context.getResources().getColor(R.color.material_colorAccent, null));
        } else {
            editText.setTextColor(context.getResources().getColor(R.color.material_colorText_Icon, null));
            editText.setHintTextColor(context.getResources().getColor(R.color.material_colorText_Icon, null));
        }
    }

    public static boolean setPasswordHidden(EditText view) {
        View parentView = (View) view.getParent().getParent();
        if (view.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            setVisibleIcon(view, true);
            return true;
        } else {
            view.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            setVisibleIcon(view, false);
            return false;
        }
    }

    public static void setVisibleIcon(EditText editText, boolean hidden) {
        View parentView = (View) editText.getParent();
        switch (editText.getId()) {
            case R.id.input_passwd:
                ImageView passwdIcon = parentView.findViewById(R.id.passwd_icon);
                passwdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_green
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_green
                        : R.drawable.ic_visibility));
                break;
            case R.id.new_passwd:
                ImageView nepasswdIcon = parentView.findViewById(R.id.nepasswd_icon);
                nepasswdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_green
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_green
                        : R.drawable.ic_visibility));
                break;
            case R.id.repeat_passwd:
                ImageView repasswdIcon = parentView.findViewById(R.id.repasswd_icon);
                repasswdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_green
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_green
                        : R.drawable.ic_visibility));
                break;
            default:
        }
    }

    public static void shakeAnimation(Context context, View view) {
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
        view.startAnimation(shake);

    }
}
