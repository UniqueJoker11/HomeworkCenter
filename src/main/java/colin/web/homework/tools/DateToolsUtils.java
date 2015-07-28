package colin.web.homework.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ASUS on 2015/7/12.
 */
public class DateToolsUtils {
    /**
     * 以YYYY-MM-DD hh-mm-ss的形式返回当前的具体时间
     *
     * @return
     */
    public static String getTodayCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 以YYYY-MM-DD的形式返回当前的日期
     *
     * @return
     */
    public static String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    public static String getSpecificDate(String timePattern) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(timePattern);
        return dateFormat.format(calendar.getTime());
    }

    public static void main(String[] args) {
        DateToolsUtils tools = new DateToolsUtils();
        System.out.println(tools.getTodayCurrentTime());

    }
}
