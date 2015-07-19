package colin.web.homework.tools;

import java.util.UUID;

/**
 * Created by ASUS on 2015/7/12.
 */
public class StringToolsUtils {
    public static String getCommonUUID(){
        return UUID.randomUUID().toString();
    }
}