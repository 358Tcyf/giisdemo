package simple.project.giisdemo.helper.utils;

import simple.project.giisdemo.R;

import static simple.project.giisdemo.helper.constant.GlobalField.CARD_FIREY;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_HOPE;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_LIGHT;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_SAKURA;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_SAND;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_STORM;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_THUNDER;
import static simple.project.giisdemo.helper.constant.GlobalField.CARD_WOOD;
import static simple.project.giisdemo.helper.constant.GlobalField.DEFAULT;
import static simple.project.giisdemo.helper.constant.GlobalField.INDIGO;
import static simple.project.giisdemo.helper.constant.GlobalField.LIGHT_BLUE;
import static simple.project.giisdemo.helper.constant.GlobalField.TEAL;

/**
 * @author Created by ys
 * @date at 2019/1/15 22:25
 * @describe
 */
public class ThemeUtils {
    public static int themeToStyle(int theme) {
        switch (theme) {
            case DEFAULT:
                return R.style.AppThemeTeal;
            case TEAL:
                return R.style.AppThemeTeal;
            case LIGHT_BLUE:
                return R.style.AppThemeLightBlue;
            case INDIGO:
                return R.style.AppThemeIndigo;
            case CARD_SAKURA:
                return R.style.CARD_SAKURA;
            case CARD_HOPE:
                return R.style.CARD_HOPE;
            case CARD_STORM:
                return R.style.CARD_STORM;
            case CARD_WOOD:
                return R.style.CARD_WOOD;
            case CARD_LIGHT:
                return R.style.CARD_LIGHT;
            case CARD_THUNDER:
                return R.style.CARD_THUNDER;
            case CARD_SAND:
                return R.style.CARD_SAND;
            case CARD_FIREY:
                return R.style.CARD_FIREY;
            default:
                return R.style.AppThemeDefault;

        }
    }
}
