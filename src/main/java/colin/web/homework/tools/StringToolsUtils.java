package colin.web.homework.tools;

import java.util.UUID;

/**
 * Created by ASUS on 2015/7/12.
 */
public class StringToolsUtils {
    public static String getCommonUUID(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            System.out.println(StringToolsUtils.getCommonUUID());
        }
    }
}