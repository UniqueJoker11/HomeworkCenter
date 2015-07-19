package colin.web.homework.tools;

import org.apache.log4j.Logger;

/**
 * Created by ASUS on 2015/7/11.
 */
public class HomeworkLogOperate {
    public static Logger getCurrentLogger(Class z){
        return Logger.getLogger(z);
    }
}
